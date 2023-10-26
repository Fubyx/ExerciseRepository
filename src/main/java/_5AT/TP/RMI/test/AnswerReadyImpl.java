package _5AT.TP.RMI.test;

public class AnswerReadyImpl implements AnswerReadyLocal{
    @Override
    public void callback(String s) {
        System.out.println(s);
        System.exit(0);
    }
}
