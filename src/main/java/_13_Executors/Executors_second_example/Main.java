package _13_Executors.Executors_second_example;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        byte[] b = new byte[4000000];
        new Random().nextBytes(b);

        Callable<byte[]> c = new SorterCallable(b);
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<byte[]> result = executor.submit(c);
        byte[] bs = new byte[0];
        try {
            bs = result.get();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%d, %d, %d%n", bs[0], bs[1], bs[bs.length-1]);
        executor.shutdown();
    }
}
