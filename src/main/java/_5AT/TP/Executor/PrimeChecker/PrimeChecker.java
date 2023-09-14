package _5AT.TP.Executor.PrimeChecker;

import java.util.concurrent.Callable;

public class PrimeChecker implements Callable<Boolean> {
    Long number;

    public PrimeChecker(long number){
        this.number = number;
    }

    @Override
    public Boolean call() throws Exception {
        return isPrime(number);
    }

    private boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }

        if (number <= 3) {
            return true;
        }

        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (long i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
