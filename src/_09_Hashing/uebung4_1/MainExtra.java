package _09_Hashing.uebung4_1;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/*Im Endeffekt haben wir uns für offenes Hashing entschieden, da da eigentlich ein sehr gutes Verfahren von
* der Geschwindigkeit her ist, so lang man beim geschlossenen nicht DOppelHashing nimmt*/


class MainExtra {

    static private LinkedList<Integer> getPrimeNumbersTo(int end){
        LinkedList<Integer> primeNumbers = new LinkedList<>();
        primeNumbers.add(2);
        primeNumbers.add(3);
        for (int i = 0; i < end; i++) {
            int sqrtRt = (int) Math.sqrt(i);
            for (int j = 2; j <= sqrtRt; j++) {
                if(i % j == 0){
                    break;
                }
                if(j >= sqrtRt){
                    primeNumbers.add(i);
                }
            }
        }
        return primeNumbers;
    }

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
        LinkedList<Student>[] hashMap = new LinkedList[23];
        try {
            FileInputStream fileIn = new FileInputStream("testExtra.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hashMap = (LinkedList<Student>[])in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException e){
            for(int i = 0; i < hashMap.length; ++i){
                hashMap[i] = new LinkedList<>();
            }
        }
        for(int i = 0; i < hashMap.length; ++i){
            hashMap[i] = new LinkedList<>();
        }
        hashMap[0] = new LinkedList<>();
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
            FileOutputStream fileOut = new FileOutputStream("testExtra.ser");
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
