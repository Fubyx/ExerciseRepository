package _13_Executors.Primzahl;

import java.util.concurrent.Callable;

public class PZPruefer  {
    static public boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        if (num % 2 == 0) {
            return true;
        }
        for (int i = 3; i < Math.sqrt(num); i++) {
            if (num%i == 0) {
                return false;
            }
        }
        return true;
    }
}
