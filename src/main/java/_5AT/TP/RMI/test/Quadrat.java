package _5AT.TP.RMI.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Quadrat extends Remote {
    public long quadrat(long number) throws RemoteException;
}
