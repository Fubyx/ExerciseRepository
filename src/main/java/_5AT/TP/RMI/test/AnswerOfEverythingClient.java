package _5AT.TP.RMI.test;

import com.sun.source.tree.WhileLoopTree;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class AnswerOfEverythingClient {
    public static void main(String[] args) {

        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Zu welchen Server verbinden? (zB 127.0.0.1)");
            String serverName = s.nextLine();
            System.out.println("Gib eine Frage ein");
            String question = s.nextLine();
            Registry registry = LocateRegistry.getRegistry(serverName, 30000);
            AnswerOfEverythingRemote stub = (AnswerOfEverythingRemote) registry.lookup("answerToEverything");


            AnswerReadyImpl answerReady = new AnswerReadyImpl();
            AnswerReadyLocal a_stub = (AnswerReadyLocal) UnicastRemoteObject.exportObject(answerReady, 0);

            stub.answer(question, a_stub);

            System.out.println("An Server gesendet.");
            System.out.println("\nWährend dem Warten kannst du etwas schreiben und es wird umgedreht");
            StringBuilder input = new StringBuilder();
            while (true) {
                input.delete(0, input.length());
                input.append(s.nextLine());
                input.reverse();
                System.out.println(input);
            }

        } catch (Exception e) {
            System.err.println("Fehler beim Client: ");
            e.printStackTrace();
        }
    }
}
