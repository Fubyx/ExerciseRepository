package TP.JUnitTesting.u1;

/*
	Schreibe fuer die gegbene Klasse einen JUnit-Test:
	- Uberpruefung der Sortierung selbst .. wird das uebergebene Array richtig sortiert?
	- Was passiert bzw. was erwartet man sich wenn NULL uebergeben wird?
	- Performance Test wenn man z.B. 1000000x eine Zahlenfolge sortiert
*/

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortTest {
    @Test
    @Name("The array is sorted Right")
    void sortedRightTest() {
        int[] arr = {8, 4, 9, 2, 0, 1, 7, 5, 6, 3};
        int[] expected = {0,1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expected, BubbleSort.bubbleSort(arr));
    }

    @Test
    void nullArgumentTest() {
        assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                BubbleSort.bubbleSort(null);
            }
        });
    }

    @Test
    void performanceTest() {
        long startTime = System.currentTimeMillis();
        int[] arr;
        for (int i = 0; i < 100000; i++){
            arr = new int[]{8, 4, 9, 2, 0, 1, 7, 5, 6, 3};
            BubbleSort.bubbleSort(arr);
        }
        assertTrue(System.currentTimeMillis() - startTime < 1000);
    }
}