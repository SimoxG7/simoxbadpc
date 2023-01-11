public class Cesare {

  public static void main(String[] args) {
    
    String toChange = "hello";

    System.out.println(change('z', 1));

    char z = 'z';



    System.out.println(Character.toChars(z -1));

    /* 
    for (int i = 0; i < 26; i++) {
      String s = "";

      for (int j = 0; j < toChange.length(); j++) {
        s += change(toChange.charAt(j), i);
      }

      System.out.println(s);
    }
    */
  }

  private static char change(char c, int i) {
    if (Character.isLowerCase(c)) {
      if (Character.valueOf(c) + i > Character.valueOf('z')) return Character.valueOf(Character.valueOf(c) - 'z' + 'a' + i);
    } else {
      if (c + i > 'Z') return Character.valueOf(Character.valueOf(c) - 'Z' + 'A' + i);
    }
    return Character.valueOf(Character.valueOf(c) + i);
  }
}
