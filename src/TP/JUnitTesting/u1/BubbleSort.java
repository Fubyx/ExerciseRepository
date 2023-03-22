package TP.JUnitTesting.u1;

/*
	Schreibe fuer die gegbene Klasse einen JUnit-Test:
	- Uberpruefung der Sortierung selbst .. wird das uebergebene Array richtig sortiert?
	- Was passiert bzw. was erwartet man sich wenn NULL uebergeben wird?
	- Performance Test wenn man z.B. 1000000x eine Zahlenfolge sortiert
*/

public class BubbleSort {
    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr[j-1] > arr[j]){
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        int arr[] ={3,60,35,2,45,320,5};

        System.out.println("Array Before Bubble Sort");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        bubbleSort(arr);

        System.out.println("Array After Bubble Sort");
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}