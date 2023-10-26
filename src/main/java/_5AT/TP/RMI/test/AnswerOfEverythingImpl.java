package _5AT.TP.RMI.test;

import java.rmi.RemoteException;

public class AnswerOfEverythingImpl implements AnswerOfEverythingRemote {
    @Override
    public void answer(String question, AnswerReadyLocal client_stub) throws RemoteException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    client_stub.callback("Die Antwort auf deine Frage \"" + question + "\" ist wahrscheinlich 42");
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
