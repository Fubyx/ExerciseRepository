package _5AT.TP.RMI_F.Square;

import _5AT.TP.RMI_F.Example.Message;
import _5AT.TP.RMI_F.Example.Text;

import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String args[]) {
        Registry registry;
        try {
            SquareImpl square = new SquareImpl();
            Square t_stub = (Square) UnicastRemoteObject.exportObject(square, 0);
            registry = LocateRegistry.getRegistry();
            registry.bind("Square", t_stub);
            System.out.println("Server bereit ...");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
