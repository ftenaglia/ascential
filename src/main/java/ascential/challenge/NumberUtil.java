package ascential.challenge;

public class NumberUtil {

  public static boolean isInt(String string) {

    if (string == null || string.isEmpty()) {
      return false;
    }
    int i = 0;
    if (string.charAt(0) == '-') {
      if (string.length() == 1) {
        return false;
      }
      i = 1;
    }
    for (; i < string.length(); i++) {
      char c = string.charAt(i);
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }
}
