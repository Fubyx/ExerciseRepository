package _5AT.TP.RMI_F.Question_Answer;

import _5AT.TP.RMI_F.Square.Square;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the registry IP:");
        String ip = sc.nextLine();
        System.out.println("Enter the port:");
        int port = sc.nextInt();
        System.out.println("Enter the question:");
        String question = sc.nextLine();

        try {
            Registry registry = LocateRegistry.getRegistry(ip, port);
            RemoteObject rem = (RemoteObject) registry.lookup("Answer");
            rem.answer(question, (CallBack) UnicastRemoteObject.exportObject(new CallBackImpl(), 0));
        } catch (Exception e) {
            System.err.println("Fehler beim Client: " + e);
        }
    }
}
