package _5AT.TP.Executor;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class SorterCallable implements Callable<byte []> {

    byte [] b;
    public SorterCallable(byte [] b){
        this.b = b;
    }
    @Override
    public byte[] call() throws Exception {
        Arrays.sort(b);
        return b;
    }
}
