package _5AT.TP.RMI.test;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(30000);

            Message msg = new Message("Hallo Welt!");
            Text t_stub = (Text) UnicastRemoteObject.exportObject(msg, 0);
            registry.bind("TextMessage", t_stub);

            QuadratImpl q = new QuadratImpl();
            Quadrat q_stub = (Quadrat) UnicastRemoteObject.exportObject(q, 0);
            registry.bind("Quadrat", q_stub);

            TaskImpl task = new TaskImpl();
            TaskRemote tr_stub = (TaskRemote) UnicastRemoteObject.exportObject(task, 0);
            registry.bind("GenericTask", tr_stub);

            AnswerOfEverythingImpl answerToEverything = new AnswerOfEverythingImpl();
            AnswerOfEverythingRemote a_stub = (AnswerOfEverythingRemote) UnicastRemoteObject.exportObject(answerToEverything, 0);
            registry.bind("answerToEverything", a_stub);

            System.out.println("Server bereit ...");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
/*
-Djava.security.manager -Djava.security.policy=C:\Users\david\Documents_not_on_Onedrive\coding\Java\ExerciseRepository\src\main\java\_5AT\TP\RMI\test\Server.policy

Kann ein Client mehrere Aufrufe simultan durchführen?
ja

Kann dein Server auch mehrere Clients gleichzeitig abarbeiten?
ja

Was beinhaltet das Interface java.rmi.Remote? Wieso?
 Das Interface java.rmi.Remote selbst enthält keine Methoden,
 es ist ein sogenanntes "Marker-Interface". Marker-Interfaces
 in Java enthalten keine deklarierten Methoden, sondern dienen
 dazu, eine bestimmte semantische Bedeutung oder Verhaltensweise
 für implementierende Klassen zu kennzeichnen.
 */
