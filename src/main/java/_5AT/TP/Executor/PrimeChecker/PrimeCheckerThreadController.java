package _5AT.TP.Executor.PrimeChecker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrimeCheckerThreadController {
    /*
    Schreibe eine zweite Klasse, welche einen Threadpool verwaltet und der
    man Long-Zahlen übergeben kann. Die Klasse erzeugt einen PZPruefer und
    führt jede Berechnung in einem eigenen Thread aus.
     */
    long[] numbers;
    public PrimeCheckerThreadController(long ... numbers){
        this.numbers = numbers;
    }
    public boolean[] checkNumbers() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Boolean>[] result = new Future[numbers.length];
        for(int i = 0; i < numbers.length; ++i){
            PrimeChecker p = new PrimeChecker(numbers[i]);
            result[i] = executor.submit(p);
        }
        boolean [] resBool = new boolean[numbers.length];
        for(int i = 0; i < numbers.length; ++i){
            resBool[i] = result[i].get();
        }
        executor.shutdown();
        return resBool;
    }
}
