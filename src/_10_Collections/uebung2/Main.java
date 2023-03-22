package _10_Collections.uebung2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> amounts = new ArrayList<>();
        ArrayList<Integer> sorted = new ArrayList<>();
        for(int i = 0; i < 26; ++i){
            amounts.add(0);
            sorted.add(0);
        }
        int counter = 0;

        String s = "ZzZzaAhHsebfizevfi hevfiewfviefbiwdefbviwdhsfdhibiuafiuaebdiquafuebf";
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            try {
                amounts.set(Character.getNumericValue(c) - 10, amounts.get(Character.getNumericValue(c) - 10) + 1);
                ++counter;
                sorted.set(Character.getNumericValue(c) - 10, amounts.get(Character.getNumericValue(c) - 10) + 1);
            }catch(IndexOutOfBoundsException e) {

            }
        }
        HashMap<Character, Double> sortedByAmount = new HashMap<>();
        sorted.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });//*/
        System.out.println(counter);
        for (Integer i: sorted) {
            for(int j = 0; j < amounts.size(); ++j){
                System.out.println(i + "___" + amounts.get(j));
                if(i.intValue() == amounts.get(j).intValue()){
                    sortedByAmount.put((char)(j + 65), (double)amounts.get(j)/counter * 100f);
                    amounts.set(j, Integer.MAX_VALUE);
                    break;
                }
            }
        }
        /*
        for(char i = 'A'; i <= 'Z'; ++i) {
            System.out.println(i + ": " + amounts.get(Character.getNumericValue(i) - 10) +
                    " == " + ((double) amounts.get(Character.getNumericValue(i) - 10) / (double) counter * 100f) + "%");
            //sortedByAmount.put(i, amounts.get(Character.getNumericValue(i) - 10) / (double) counter * 100f);
        }//*/


        for(Character c : sortedByAmount.keySet()){
            System.out.println(c + ": "+ sortedByAmount.get(c));
        }//*/
    }
}
