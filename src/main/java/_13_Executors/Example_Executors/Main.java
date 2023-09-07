package _13_Executors.Example_Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello A " + Thread.currentThread());

            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello B " + Thread.currentThread());
            }
        };
        ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(r1);
        executor.execute(r2);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.execute(r1);
        executor.execute(r2);

        executor.shutdownNow();
    }


}
