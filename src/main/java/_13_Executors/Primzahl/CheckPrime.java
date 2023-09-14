package _13_Executors.Primzahl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CheckPrime {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public CheckPrime() {
    }
    public boolean[] isPrime(long ... number) {
        Future<Boolean>[] result = new Future[number.length];
        for (int i = 0; i < number.length; i++) {
            result[i] = executor.submit(new IsPrimeCallable(number[i]));
        }
        boolean[] results = new boolean[number.length];
        for (int i = 0; i < results.length; i++) {
            try {
                results[i] = result[i].get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return results;
    }
    public void end() {
        executor.shutdown();
    }

}
