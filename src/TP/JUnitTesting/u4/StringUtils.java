package u4;

/*
	Schreibe einen JUnit-Test für die Klasse StringUtils
	um die darin enthaltenen Methoden passend zu ueberprueften.
	Teste grundlegend die Rueckgabewerte (alle moeglichen)
	und ueberpruefe sie mit den zu erwartenden Werten.
*/

public class StringUtils {


  public static Double convertToDouble(String str) {
    if (str == null) {
      return null;
    }
    return Double.valueOf(str);
  }

  public static boolean isNullOrBlank(String st) {
    return st == null || st.trim().length() == 0;
  }

  public static String getDefaultIfNull(final String st, final String defaultSt) {
    return st == null ? defaultSt : st;
  }

  public static String concat(String... sts) {
    String retVal = null;
    if (sts != null && sts.length > 0) {
      StringBuilder sb = new StringBuilder();
      for (String st : sts) {
        if (st != null) {
          sb.append(st);
        }
      }
      retVal = sb.toString();
    }
    return retVal;
  }

}

