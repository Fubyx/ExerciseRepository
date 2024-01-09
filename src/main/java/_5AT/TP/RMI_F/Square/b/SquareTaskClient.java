package _5AT.TP.RMI_F.Square.b;

import _5AT.TP.RMI_F.Square.Square;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class SquareTaskClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the registry IP:");
        String ip = sc.nextLine();
        System.out.println("Enter the port:");
        int port = sc.nextInt();
        System.out.println("Enter the number to be squared:");
        long number = sc.nextLong();

        try {
            Thread.sleep(1000);
            Registry registry = LocateRegistry.getRegistry(ip, port);
            SquareTask task = new SquareTask();
            TaskRemote t_stub = (TaskRemote) registry.lookup("GenericTask");
            /*Thread t1 = new Thread(() -> {
                try {
                    double response = (double)t_stub.doTask(number, task);
                    System.out.println("Antwort vom Server t1: " + response);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    double response = (double)t_stub.doTask(number, task);
                    System.out.println("Antwort vom Server t2: " + response);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            });
            t1.start();
            t2.start();*/
            double response = (double) t_stub.doTask(number, task);
            System.out.println("Antwort vom Server: " + response);
        } catch (Exception e) {
            System.err.println("Fehler beim Client: " + e);
        }
    }
}
