package _5AT.TP.RMI.test;

import java.rmi.RemoteException;

public class TaskImpl implements TaskRemote{
    @Override
    public Object doTask(Object arg, Task task) throws RemoteException {
        return task.doTask(arg);
    }
}
