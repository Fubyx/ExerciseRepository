package _5AT.TP.RMI_F.Example;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String args[]) {
        Registry registry = null;
        try {
            Message msg = new Message("Hallo Welt!");
            Text t_stub = (Text) UnicastRemoteObject.exportObject(msg, 0);
            registry = LocateRegistry.getRegistry(1099);
            registry.bind("TextMessage", t_stub);
            System.out.println("Server bereit ...");
            Thread.sleep(100000);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            try {
                UnicastRemoteObject.unexportObject(registry, true);
            } catch (NoSuchObjectException e) {
                throw new RuntimeException(e);
            }
        }
    }
}