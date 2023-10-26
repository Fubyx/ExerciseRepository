package _5AT.TP.RMI.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaskRemote extends Remote {
    public Object doTask(Object arg, Task task) throws RemoteException;
}
