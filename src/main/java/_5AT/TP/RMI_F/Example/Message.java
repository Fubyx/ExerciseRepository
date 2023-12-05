package _5AT.TP.RMI_F.Example;

import java.rmi.RemoteException;

public class Message implements Text {
    private String txt;
    public Message(String t) {
        txt = t;
    }
    public String getText() throws RemoteException {
        return txt;
    }
}
