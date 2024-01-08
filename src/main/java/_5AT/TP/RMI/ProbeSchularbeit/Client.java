package _5AT.TP.RMI.ProbeSchularbeit;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
1. SOAP, da es im unterschied zu RMI nicht programmiersprachenabhängig ist.
So kann man einen Server der die Daten zentral speichert erstellen,
wobei man mehrere und verschiedene Clients zur darstellung verschiedener
Daten erstellen kann, solange man sich ans Protokoll und Format hält
2. in einer WSDL datei die der Server angibt werden die namen, Übergabeparameter,
Rückgabewerte, usw. von möglichen Methoden beschrieben, die Remote ausgeführt
werden können (RPC). Bei SOAP läuft die kommunikation im XML Format (extensible markup language)
 */
public class Client {
    public static void main(String[] args) {
        try {
            Registry r = LocateRegistry.getRegistry("localhost", 1099);
            BibliothekInterface stub = (BibliothekInterface) r.lookup("bibliothek");

            if (stub.getAllBooks().length == 0) {
                System.out.println("test 1 erfolgreich");
            }
            stub.addBook("title", "author", 1234567890, "verlag");

            if (stub.getAllBooks()[0].getAutor().equals("author")) {
                System.out.println("test 2 erfolgreich");
            }

            if (stub.getBookFromISBN(123) == null) {
                System.out.println("test 3 erfolgreich");

            }
            stub.addBook(null, null, 1, null);
            if (stub.getBookFromISBN(1) == null) {
                System.out.println("test 4 erfolgreich");
                // weil es keine Bücher mit null-Werte annehmen soll!
            }
            stub.addBook("t", "a", 1234567890, "v");
            if (stub.getAllBooks().length == 1) {
                System.out.println("test 5 erfolgreich");
                // weil es kein zweites Buch mit derselben ISBN speichern soll
            }
            stub.addBook("t", "a", 1, "v");
            stub.addBook("t2", "a2", 2, "v2");
            if  (stub.getBookFromISBN(1).getAutor().equals("a")) {
                System.out.println("test 6 erfolgreich");

            }
            stub.clearBooks();
        } catch (RemoteException e) {
            System.out.println("something didn't work!");
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

    }
}
