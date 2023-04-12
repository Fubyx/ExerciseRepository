package TP.JUnitTesting.u3;

/*
	Aufwandsabschaetzung:
	---------------------------------------------
	Wie oft rufen sich die Funktionen selbst auf?
	Wie oft ruft f die anderen Funktionen auf?
	... in Abhaengigkeit vom Input?
*/

public class VerschiedeneRechnungen {

    static boolean gerade(int x){ // O(n)
        if (x == 0) return true;
        return !gerade(x-1);
    }

    static int verdopple(int x){ // O(n)
        if (x == 0) return 0;
        return 2 + verdopple(x-1);
    }

    static int halbiere(int x){
        if (x == 0) return 0;
        if (x == 1) return 0;
        return 1 + halbiere(x-2);
    }

    static int f(int a, int b){
        if (b == 0) return 0;
        if (gerade(b)) return f(verdopple(a), halbiere(b));
        return a + f(verdopple(a), halbiere(b));
    }

}