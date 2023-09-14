package _5AT.TP.Executor.PrimeChecker;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long[] numbers = new long[]{3, 5, 77, 967, 1433, 4969, 39293, 94951,
                99971, 96931, 98773, 1000003, 10000019, 2147483647, 100000000069l,
                100000000003l};
        ThreadController t = new ThreadController(numbers);
        boolean[] isPrime = t.checkNumbers();
        for (int i = 0; i < numbers.length; ++i){
            System.out.println(numbers[i] + "\t" + isPrime[i]);
        }

    }
}
