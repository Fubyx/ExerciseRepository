package _5AT.TP.RMI.test;

import java.rmi.RemoteException;

public class Message implements Text{
    private String txt;
    public Message(String t) {
        txt = t;
    }
    @Override
    public String getText() throws RemoteException {
        return txt;
    }
}
