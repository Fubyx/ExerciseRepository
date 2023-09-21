package _13_Executors.Primzahl;

import java.util.concurrent.Callable;

public class IsPrimeCallable implements Callable<Boolean> {
    private long num;
    public IsPrimeCallable(long num) {
        this.num = num;
    }
    @Override
    public Boolean call() throws Exception {
        return PZPruefer.isPrime(num);
    }
}
