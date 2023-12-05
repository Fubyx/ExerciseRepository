package _5AT.TP.RMI_F.Question_Answer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallBack extends Remote {
    public void call(String answer)throws RemoteException;
}
