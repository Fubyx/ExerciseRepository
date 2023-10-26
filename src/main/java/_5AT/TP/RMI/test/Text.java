package _5AT.TP.RMI.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Text extends Remote {
    public String getText() throws RemoteException;

}
