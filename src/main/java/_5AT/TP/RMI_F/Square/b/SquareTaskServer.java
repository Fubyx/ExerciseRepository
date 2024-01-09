package _5AT.TP.RMI_F.Square.b;

import _5AT.TP.RMI_F.Square.Square;
import _5AT.TP.RMI_F.Square.SquareImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SquareTaskServer {
    public static void main(String[] args) {
        Registry registry;
        try {
            TaskRemoteImpl impl = new TaskRemoteImpl();
            TaskRemote t_stub = (TaskRemote) UnicastRemoteObject.exportObject(impl, 0);
            //registry = LocateRegistry.getRegistry();
            registry = LocateRegistry.createRegistry(1099);
            registry.rebind("GenericTask", t_stub);
            System.out.println("Server bereit ...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
