package _5AT.TP.RMI_F.Question_Answer;

import _5AT.TP.RMI_F.Square.Square;
import _5AT.TP.RMI_F.Square.SquareImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry();
            RemoteObjectImpl rem = new RemoteObjectImpl();
            registry.rebind("Answer", UnicastRemoteObject.exportObject(rem, 0));

            //Square t_stub = (Square) UnicastRemoteObject.exportObject(square, 0);
            //registry.bind("Square", t_stub);
            System.out.println("Server bereit ...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
