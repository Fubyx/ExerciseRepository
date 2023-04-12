package _09_Hashing.uebung4;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

 class Main {

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

        int amountOfStudents = 0;
        try {
            FileInputStream fileIn = new FileInputStream("test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hashMap = (LinkedList<Student>[])in.readObject();
            in.close();
            fileIn.close();
            for(LinkedList<Student> l : hashMap){
                amountOfStudents += l.size();
            }
        }catch(IOException | ClassNotFoundException e){
            for(int i = 0; i < hashMap.length; ++i){
                hashMap[i] = new LinkedList<>();
            }
        }
        Scanner s = new Scanner(System.in);

        System.out.println("Enter \"add\",\"remove\", \"showStudents\", \"showLoadFactor\" or \"cancel\"");
        String operation;
        while(!(operation = s.nextLine()).equals("cancel")){
            if(operation.equals("add") || operation.equals("remove")){
                System.out.println("Enter the first name");
                String firstName = s.nextLine();
                System.out.println("Enter the last name");
                String lastName = s.nextLine();
                if(operation.equals("add")){
                    System.out.println("Enter the birthday");
                    String date = s.nextLine();
                    System.out.println("Enter the class");
                    String className = s.nextLine();

                    Student student = new Student(firstName, lastName, date, className);
                    amountOfStudents++;
                    if((double)(amountOfStudents/hashMap.length) > 0.75){
                        hashMap = reorganizeHashMap(hashMap);
                    }
                    hashMap[hash(student, hashMap.length)].add(student);
                }else{
                    Student student = new Student(firstName, lastName, "", "");
                    amountOfStudents--;
                    Iterator<Student> it = hashMap[hash(student, hashMap.length)].iterator();
                    while(it.hasNext()){
                        Student current = it.next();
                        if(current.firstName.equals(student.firstName) && current.lastName.equals(student.lastName)){
                            hashMap[hash(student, hashMap.length)].remove(current);
                        }
                    }
                }
            }else if(operation.equals("showStudents")){
                for (LinkedList<Student> l: hashMap) {
                    for (Student student:l) {
                        System.out.println(student.toString());
                    }
                }
            }else if(operation.equals("showLoadFactor")){
                System.out.println((double)amountOfStudents/hashMap.length * 100 + "%");
            }
            System.out.println("Enter \"add\",\"remove\", \"showStudents\", \"showLoadFactor\" or \"cancel\"");
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
