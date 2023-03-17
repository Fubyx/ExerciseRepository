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
            sum += firstName.charAt(i) % 30;
        }
        for (int i = 0; i < firstName.length(); i++) {
            sum += lastName.charAt(i) % 30;
        }

        return sum % m;
    }
    public static void main(String[] args) {
        LinkedList<Integer>  l = new LinkedList<>();
        l.push(3);
        l.push(5);
        System.out.println(l);

        LinkedList<Student>[] hashMap = new LinkedList[28];
        Scanner s = new Scanner(System.in);
        System.out.println(s.nextLine());

    }
}
