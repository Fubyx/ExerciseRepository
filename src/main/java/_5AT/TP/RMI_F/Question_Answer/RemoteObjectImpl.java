package _5AT.TP.RMI_F.Question_Answer;

import java.rmi.RemoteException;

public class RemoteObjectImpl implements RemoteObject{
    @Override
    public void answer(String question, CallBack callback) throws RemoteException {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    callback.call("The answer to your " + question + " is probably 42");
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();*/
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            callback.call("The answer to your " + question + " is probably 42");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
