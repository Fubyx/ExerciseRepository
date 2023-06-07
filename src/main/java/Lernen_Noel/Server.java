package Lernen_Noel;

import java.io.IOException;

public class Server implements Comparable, TestInt {
    public String testString;
    public static void main(String[] args) throws IOException {
        Server test = new Server();
        test.testString = "Lisa";

        Server server2 = new Server();
        server2.testString = "lISA";

        System.out.println(test.compareTo(server2));

        Server serverA = new Server();
        Server serverB = serverA;
        serverA.testString = "Simon";

        System.out.println(serverA.equals(serverB));
        System.out.println(serverB.testString);
        serverB.testString = "Lisa";
        System.out.println(serverA.testString);


    }
    @Override
    public int compareTo(Object o) {
        try {
            Server server = (Server) o;
            if (server.testString.equals("Paul"))
                return -1;
            if (server.testString.equals("Noel"))
                return -25;

            if (server.testString.equals(this.testString))
                return 1;
            if (server.testString.equalsIgnoreCase(testString))
                return 2;
            return 0;
        }catch(ClassCastException classCastException){
            return 55;
        }
    }
    @Override
    public void testMethod(){
        System.out.println("Tests");
    }
    @Override
    public boolean equals(Object object){
        return testString.equalsIgnoreCase(((Server)object).testString);
    }
}
