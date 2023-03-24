package _10_Collections.uebung2;

import java.util.*;

class Main {
    public static void main(String[] args) {
        TreeMap<Character, Integer> amounts = new TreeMap<>();
        for (char i = 'a'; i <= 'z'; i++) {
            amounts.put(i, 0);
        }

        String s = "ZzZzaAhHseb123,.-59rgefvdwc87izev-fihev-aqxdwefbfie wfv3bgtrvefds45.qweAYRs-d.z.ugbinojmuk,ljmpk9,8iöo-Ä7i.hbuv,txrwew1234biw.d--fbviw.,dhsf6d-ibiuaf987.iuaebdi,quafuebf";
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); ++i) {
            char key = s.charAt(i);
            // nur buchstaben:
            if (key > 'z' || key < 'a') {
                continue;
            }
            int newAmount = amounts.get(key) + 1;
            amounts.replace(key, newAmount);
        }
        ArrayList<Map.Entry> keyList = new ArrayList<Map.Entry>(amounts.entrySet());
        Collections.sort(keyList, new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                if ((int) o1.getValue() < (int) o2.getValue()) {
                    return 1;
                } else if ((int) o1.getValue() > (int) o2.getValue()) {
                    return -1;
                }
                return 0;
            }
        });
        for (Map.Entry i : keyList) {
            System.out.println(i);
        }
        /*
        HashMap<Character, Double> sortedByAmount = new HashMap<>();
        sorted.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });
        System.out.println(counter);
        for (Integer i : sorted) {
            for (int j = 0; j < amounts.size(); ++j) {
                System.out.println(i + "___" + amounts.get(j));
                if (i.intValue() == amounts.get(j).intValue()) {
                    sortedByAmount.put((char) (j + 65), (double) amounts.get(j) / counter * 100f);
                    amounts.set(j, Integer.MAX_VALUE);
                    break;
                }
            }
        }
        //*/
        /*
        for(char i = 'A'; i <= 'Z'; ++i) {
            System.out.println(i + ": " + amounts.get(Character.getNumericValue(i) - 10) +
                    " == " + ((double) amounts.get(Character.getNumericValue(i) - 10) / (double) counter * 100f) + "%");
            //sortedByAmount.put(i, amounts.get(Character.getNumericValue(i) - 10) / (double) counter * 100f);
        }


        for (Character c : sortedByAmount.keySet()) {
            System.out.println(c + ": " + sortedByAmount.get(c));
        }//*/
    }
}
