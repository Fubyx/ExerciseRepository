package _5AT.TP.RMI_F.Example;

import java.rmi.*;

public interface Text extends Remote {
    public String getText() throws RemoteException;
}