package _13_Executors.Primzahl;

public class Main {
    public static void main(String[] args) {
        long[] arr = {967, 1433, 4969, 39293, 94951,
                99971, 96931, 98773, 1000003, 10000019, 2147483647, 100000000069L,
                100000000003L};
        CheckPrime cp = new CheckPrime();
        boolean[] result = cp.isPrime(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " " + result[i]);

        }
        cp.end();
    }
}
