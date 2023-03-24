package TP.JUnitTesting.u0;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class CalcTest {
    @Test
    void addictionTest() {
        Calc c = new Calc();
        assertEquals(10, c.addition(6, 4));

    }

    @Test
    void addictionTestException() {
        Calc c = new Calc();
        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                c.addition(Integer.MAX_VALUE, 1);
            }
        });
    }
}