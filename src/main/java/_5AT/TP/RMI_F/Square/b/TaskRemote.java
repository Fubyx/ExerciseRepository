package _5AT.TP.RMI_F.Square.b;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaskRemote extends Remote {
    public Object doTask(Object obj, Task task) throws RemoteException;
}
