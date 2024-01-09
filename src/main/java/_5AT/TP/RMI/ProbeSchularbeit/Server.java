package _5AT.TP.RMI.ProbeSchularbeit;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            BibliothekInterface stub = (BibliothekInterface) UnicastRemoteObject.exportObject(new BibliothekImpl(), 0);
            registry.bind("bibliothek", stub);
            System.out.println("Server Erfolgreich gestartet!");
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }


    }
}
