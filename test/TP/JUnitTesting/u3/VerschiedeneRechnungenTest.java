package TP.JUnitTesting.u3;

import TP.JUnitTesting.u0.Calc;
import org.junit.jupiter.api.function.Executable;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
/*
	Aufwandsabschaetzung:
	---------------------------------------------
	Wie oft rufen sich die Funktionen selbst auf?
	Wie oft ruft f die anderen Funktionen auf?
	... in Abhaengigkeit vom Input?
*/

public class VerschiedeneRechnungenTest {

    @Test
    void timeoutTest() {
        assertTimeout(Duration.ofMillis(1000), new Executable() {
            @Override
            public void execute() throws Throwable {
                VerschiedeneRechnungen.f(10, 5);
            }
        });
    }
}