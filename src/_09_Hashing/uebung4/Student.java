package _09_Hashing.uebung4;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    String firstName;
    String lastName;
    String birthDay;
    String className;

    public Student(String firstName, String lastName, String birthDay, String className) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.className = className;
    }

    @Override
    public String toString (){
        return (firstName + " " + lastName + " Geboren am: " + birthDay + " Klasse: " + className);
    }
}
