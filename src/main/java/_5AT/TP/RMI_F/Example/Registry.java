package _5AT.TP.RMI_F.Example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Registry {
    public static void main(String[] args) throws RemoteException, InterruptedException {
        LocateRegistry.createRegistry(1099);
        while(true)
            Thread.sleep(1000000000);
    }
}
