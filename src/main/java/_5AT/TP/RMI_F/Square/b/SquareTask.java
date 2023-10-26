package _5AT.TP.RMI_F.Square.b;

public class SquareTask implements Task{
    @Override
    public Object doTask(Object obj) {
        long number = (long)obj;
        return Math.pow(number, 2);
    }
}
