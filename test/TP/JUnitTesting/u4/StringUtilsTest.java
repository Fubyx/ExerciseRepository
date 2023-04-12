package TP.JUnitTesting.u4;


/*
	Schreibe einen JUnit-Test für die Klasse StringUtils
	um die darin enthaltenen Methoden passend zu ueberprueften.
	Teste grundlegend die Rueckgabewerte (alle moeglichen)
	und ueberpruefe sie mit den zu erwartenden Werten.
*/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

  @Test
  public void convertToDoubleTest(){
    String s = "0.587";
    assertEquals(0.587, StringUtils.convertToDouble(s));
  }

  @Test
  public void isNullOrBlankTest(){
    assertTrue(StringUtils.isNullOrBlank(""));
    assertTrue(StringUtils.isNullOrBlank(null));
  }

  @Test
  public void getDefaultIfNullTest (){
    String s = "agv";
    assertEquals(s, StringUtils.getDefaultIfNull(s, "Test"));
    assertEquals(s, StringUtils.getDefaultIfNull(null, s));
    assertNotEquals(s, StringUtils.getDefaultIfNull("", s));
  }

  @Test
  public void concatTest(){
    assertEquals("HalloTest", StringUtils.concat("Hallo", "Test"));
    assertEquals("  ", StringUtils.concat(" ", " "));
  }
}

