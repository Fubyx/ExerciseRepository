package _10_Collections.uebung1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class Main {
    public static void main(String[] args) {
        final int MAX = 8;
        final int MIN = 5;

        System.out.println("gib text ein!");
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();
        string = string.toLowerCase();
        String[] parts = string.split("[, ?.@!;:]+");
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        TreeMap<String, Integer> treeMapReverseSortOrder = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.compareTo(o2) < 0) {
                    return 1;
                } else if (o1.compareTo(o2) > 0) {
                    return -1;
                }
                return 0;
            }
        });

        for (String part : parts) {
            if (part.length() < MIN || part.length() > MAX) {
                continue;
            }
            if (treeMap.containsKey(part)) {
                treeMap.replace(part, treeMap.get(part) + 1);
                treeMapReverseSortOrder.replace(part, treeMapReverseSortOrder.get(part) + 1);
            } else {
                treeMap.put(part, 1);
                treeMapReverseSortOrder.put(part, 1);
            }
        }
        System.out.println("Ergebnis sortiert nach Wörtern:");
        for (String i : treeMapReverseSortOrder.keySet()) {
            System.out.println(i + ": " + treeMapReverseSortOrder.get(i));
        }

        System.out.println("Ergebnis sortiert nach Anzahl:");
        ArrayList<Map.Entry> keyList = new ArrayList<>(treeMap.entrySet());
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
        System.out.println("finished");
    }
}

/*Hashmap wäre wenn man nicht sortieren müsste besser, weil diese theoretisch O(1) hat
* und nicht O(logN) wie der Baum hat
* aber wenn man sortiert, dann ist der Baum sinnvoler, weil man die Hashmap nicht selbst wirklich ändern kann
* weil ja die HashMap die Keys dementsprechend aufgebaut hat
*
* Beim Suchen meistens und sortieren meistens der Baum an SInnvollsten*/
