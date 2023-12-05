package _5AT.TP.RMI_F.Question_Answer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObject extends Remote {
    public void answer(String question, CallBack callback)throws RemoteException;
}
