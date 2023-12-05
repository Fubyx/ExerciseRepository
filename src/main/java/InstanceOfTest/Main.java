package InstanceOfTest;

public class Main {
    public static void main(String[] args) {
        Sub sub = new Sub();
        if (sub instanceof Super){
            System.out.println("True");
        }
    }
}
