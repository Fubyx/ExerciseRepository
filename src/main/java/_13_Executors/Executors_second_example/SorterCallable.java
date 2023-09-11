package _13_Executors.Executors_second_example;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class SorterCallable implements Callable {

    byte[] b;

    public SorterCallable(byte[] b) {
        this.b = b;
    }

    @Override
    public Object call() throws Exception {
        Arrays.sort(b);
        return b;
    }
}
