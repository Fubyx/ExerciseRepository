package _5AT.TP.RMI_F.Question_Answer;

import java.rmi.RemoteException;

public class RemoteObjectImpl implements RemoteObject{
    @Override
    public void answer(String question, CallBack callback) throws RemoteException {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        callback.call("The answer to your " + question + " is probably 42");
    }
}
