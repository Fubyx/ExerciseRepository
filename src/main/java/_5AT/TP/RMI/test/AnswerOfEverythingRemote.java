package _5AT.TP.RMI.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AnswerOfEverythingRemote extends Remote {
    public void answer(String question, AnswerReadyLocal client_stub) throws RemoteException;

}
