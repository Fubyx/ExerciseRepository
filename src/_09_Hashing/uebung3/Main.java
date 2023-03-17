package _09_Hashing.uebung3;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int bestM = 1;
        int bestA = 1;
        int lowestCollisions = Integer.MAX_VALUE;
        for(int i = 3327; i < 4096; ++i){
            for(int j = 1; j < i; ++j){
                File f = new File("src/listHashing.txt");
                BufferedReader b = new BufferedReader(new FileReader(f));
                ArrayList<Integer> positions = new ArrayList<>();
                int collisions = 0;
                while(true){
                    try {
                        String s = b.readLine();
                        int currentValue = hash(s.toCharArray(), i, j);
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
                if(collisions < lowestCollisions){
                    bestM = i;
                    bestA = j;
                    lowestCollisions = collisions;
                }
            }
            System.out.println(i - 3327 + " done");
        }
        System.out.println("BestM: "+ bestM + "BestA: "+ bestA);
    }

    private static int hash (char[] s , int m, int a){
        int h=0;
        for (int i=0; i < s.length-1; i++)
            h = (a*h + s[i]) % m;
        return h ;
    }
}
