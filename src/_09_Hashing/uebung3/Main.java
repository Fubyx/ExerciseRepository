package _09_Hashing.uebung3;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        final int[] bestM = {1};
        final int[] bestA = {1};
        final int[] lowestCollisions = {Integer.MAX_VALUE};

        //ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 3327; i < 4096; ++i){
            for(int j = 1; j < i; ++j){
                int finalI = i;
                int finalJ = j;
                //Thread t = new Thread(new Runnable() {
                    //@Override
                    //public void run() {
                        File f = new File("src/listHashing.txt");
                        BufferedReader b = null;
                        try {
                            b = new BufferedReader(new FileReader(f));
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        ArrayList<Integer> positions = new ArrayList<>();
                        int collisions = 0;
                        while(true){
                            try {
                                String s = b.readLine();
                                int currentValue = hash(s.toCharArray(), finalI, finalJ);
                                boolean hit = false;
                                for(Integer val : positions){
                                    if(val == currentValue){
                                        ++collisions;
                                        hit = true;
                                        break;
                                    }
                                }
                                if(hit)continue;
                                positions.add(currentValue);
                            } catch (IOException | NullPointerException e) {
                                break;
                            }
                        }
                        if(collisions < lowestCollisions[0]){
                            bestM[0] = finalI;
                            bestA[0] = finalJ;
                            lowestCollisions[0] = collisions;
                        }
                    }
                //});
                //t.start();
                //threads.add(t);
            }
        //}

        //for (Thread t:threads) {
        //    t.join();
        //}
        System.out.println("BestM: "+ bestM[0] + "BestA: "+ bestA[0]);
    }

    private static int hash (char[] s , int m, int a){
        int h=0;
        for (int i=0; i < s.length-1; i++)
            h = (a*h + s[i]) % m;
        return h ;
    }
}
