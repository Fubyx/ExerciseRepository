package _09_Hashing.uebung4;

import javax.xml.stream.events.StartDocument;
import java.io.BufferedReader;
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
    public static void main(String[] args) {
        LinkedList<Student>[] hashMap = new LinkedList[28];
        for(int i = 0; i < hashMap.length; ++i){
            hashMap[i] = new LinkedList<>();
        }
        hashMap[0] = new LinkedList<>();
        Scanner s = new Scanner(System.in);
        String firstName;
        System.out.println("Enter a new first name or type \"cancel\" to cancel");
        while(!(firstName = s.nextLine() ).equals("cancel")){
            System.out.println("Enter the last name");
            String lastName = s.nextLine();
            System.out.println("Enter the birthday");
            String date = s.nextLine();
            System.out.println("Enter the class");
            String className = s.nextLine();
            Student student = new Student(firstName, lastName, date, className);
            hashMap[hash(student, hashMap.length)].add(student);
            System.out.println("Enter a new user or type \"cancel\" to cancel");
        }
        for (LinkedList<Student> l: hashMap) {
            System.out.println("newList");
            for (Student student:l) {
                System.out.println(student.toString());
            }
        }
    }
}
