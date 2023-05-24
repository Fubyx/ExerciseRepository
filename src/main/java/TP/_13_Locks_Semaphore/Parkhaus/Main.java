package TP._13_Locks_Semaphore.Parkhaus;

import java.util.concurrent.Semaphore;

/*
1 Parkhaus (kritische Region)
• 5 Parkplätze (Plätze in der kritischen Region)
• 10 Autos (Threads)
• Die Autos Fahren eine zufällige Zeit lang und versuchen dann zu parken.
• Geparkt wird ebenfalls eine zufällige Zeit lang.
• Anschließend fahren Sie wieder eine zufällige Zeit und werden zum Schluss verschrottet.
 */
public class Main {
    public static void main(String[] args) {
        Semaphore parkhaus = new Semaphore(5, true);
        Auto[] autos = new Auto[10];
        for (int i = 0; i < 10; i++) {
            autos[i] = new Auto(parkhaus, "Auto " + i);
            autos[i].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                autos[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
