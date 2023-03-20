package _10_Collections.uebung2;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> amounts = new ArrayList<>(26);
        for(int i = 0; i < 26; ++i){
            amounts.add(0);
        }

        String s = "ZzZzaAhHsebfizevfi hevfiewfviefbiwdefbviwdhsfdhibiuafiuaebdiquafuebf";
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            try {
                amounts.set(Character.getNumericValue(c) - 10, amounts.get(Character.getNumericValue(c) - 10) + 1);
                //System.out.println(Character.getNumericValue(c));
            }catch(IndexOutOfBoundsException e) {

            }
        }
        amounts.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

        for(char i = 'A'; i <= 'Z'; ++i){
            System.out.println(i + ": " + amounts.get(Character.getNumericValue(i) - 10));
        }
    }
}
