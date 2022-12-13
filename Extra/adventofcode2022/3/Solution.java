import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Path path = Paths.get("input.txt");

    ArrayList<Character> lst = new ArrayList<>();

    try {
      Scanner scanner = new Scanner(path);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine(); 
        int len = line.length();
        String p1 = line.substring(0, len/2);
        String p2 = line.substring(len/2, len);

        for (int i = 0; i < len/2; i++) {
          if (p2.contains(String.valueOf(p1.charAt(i)))) {
            lst.add(p1.charAt(i));
            break;
          }
        }
      }

      int priority = 0;

      for (Character c : lst) {
        if (Character.isUpperCase(c)) {
          priority += 27 + c - 'A';
        } else if (Character.isLowerCase(c)) {
          priority += 1 + c - 'a';
        }
      }

      scanner.close();
      System.out.println(priority);
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    } 
  }
}
