package _5AT.TP.RMI.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AnswerReadyLocal extends Remote {
    public void callback(String s) throws RemoteException;
}
