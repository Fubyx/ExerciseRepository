package _5AT.TP.RMI_F.Square;

import java.rmi.RemoteException;

public class SquareImpl implements Square{
    @Override
    public long square(long number) throws RemoteException {
        return (long) Math.pow(number, 2);
    }
}
