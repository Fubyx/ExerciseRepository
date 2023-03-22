package _09_Hashing.uebung4;

import javax.xml.stream.events.StartDocument;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static private int hash(Student student, int m){
        String firstName = student.firstName;
        String lastName = student.lastName;

        int sum = 0;
        for (int i = 0; i < firstName.length(); i++) {
            sum += firstName.charAt(i) % 31;
        }
        for (int i = 0; i < lastName.length(); i++) {
            sum += lastName.charAt(i) % 31;
        }

        return sum % m;
    }

    static private LinkedList<Student>[] reorganizeHashMap(LinkedList<Student>[] oldHashTable){
        LinkedList<Student>[] newHashMap = new LinkedList[(int) (oldHashTable.length * 1.2)];
        for (int i = 0; i < newHashMap.length; i++) {
            newHashMap[i] = new LinkedList<>();
        }
        //alle bestehenden Schueler aus der alten List in die neuen hashen
        for (int i = 0; i < oldHashTable.length; i++) {
            for (Student student: oldHashTable[i]) {
                newHashMap[hash(student, newHashMap.length)].add(student);
            }
        }
        return newHashMap;
    }

    public static void main(String[] args) {
        LinkedList<Student>[] hashMap = new LinkedList[28];
        try {
            FileInputStream fileIn = new FileInputStream("test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hashMap = (LinkedList<Student>[])in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException e){
            for(int i = 0; i < hashMap.length; ++i){
                hashMap[i] = new LinkedList<>();
            }
        }
        Scanner s = new Scanner(System.in);
        String firstName;
        System.out.println("Enter a new first name or type \"cancel\" to cancel");
        int counter = 0;
        while(!(firstName = s.nextLine() ).equals("cancel")){
            counter++;
            System.out.println("Enter the last name");
            String lastName = s.nextLine();
            System.out.println("Enter the birthday");
            String date = s.nextLine();
            System.out.println("Enter the class");
            String className = s.nextLine();
            Student student = new Student(firstName, lastName, date, className);

            if((double)(counter/hashMap.length) > 0.75){
                hashMap = reorganizeHashMap(hashMap);
            }
            hashMap[hash(student, hashMap.length)].add(student);
            System.out.println("Enter a new user or type \"cancel\" to cancel");
        }
        for (LinkedList<Student> l: hashMap) {
            System.out.println("newList");
            for (Student student:l) {
                System.out.println(student.toString());
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("test.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hashMap);
            out.close();
            fileOut.close();
        }catch(IOException e){
            for(int i = 0; i < hashMap.length; ++i){
                hashMap[i] = new LinkedList<>();
            }
        }
    }
}
