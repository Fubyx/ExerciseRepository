package _5AT.TP.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("A1" + Thread.currentThread());

                //for (int i = 1; i > 0; ++i){
                //    System.out.println("A " + i);
                //}
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("A3" + Thread.currentThread());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println("A4" + Thread.currentThread());
            }
        };

        executor.execute(r1);
        executor.execute(r2);

        Thread.sleep(500);



        executor.execute(r1);
        executor.execute(r2);

        executor.shutdownNow();
        System.out.println("Shutdown done");
    }
}
