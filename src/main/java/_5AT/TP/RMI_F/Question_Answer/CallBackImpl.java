package _5AT.TP.RMI_F.Question_Answer;

import java.io.Serializable;
import java.rmi.RemoteException;

public class CallBackImpl implements CallBack, Serializable {
    @Override
    public void call(String answer) throws RemoteException {
        System.out.println("The server responded with:\n" + answer);
        //System.exit(0);
    }
}
