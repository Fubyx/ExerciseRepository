package _5AT.TP.RMI_F.Square;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Square extends Remote {
    public long square(long number)throws RemoteException;
}
