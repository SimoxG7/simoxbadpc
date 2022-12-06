import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
  public static void main(String[] args) {
    Path path = Paths.get("input.txt");

    try {
      Scanner scanner = new Scanner(path);
      String line = scanner.nextLine();
      int len = line.length();
      scanner.close();

      ArrayList<Character> lst = new ArrayList<>();

      for (int i = 0; i < len-4; i++) {
        
        if (
          line.charAt(i) != line.charAt(i+1) &&
          line.charAt(i) != line.charAt(i+2) &&
          line.charAt(i) != line.charAt(i+3) &&
          line.charAt(i+1) != line.charAt(i+2) &&
          line.charAt(i+1) != line.charAt(i+3) &&
          line.charAt(i+2) != line.charAt(i+3) &&
          line.charAt(i) != line.charAt(i+4) &&
          line.charAt(i+1) != line.charAt(i+4) &&
          line.charAt(i+2) != line.charAt(i+4) &&
          line.charAt(i+3) != line.charAt(i+4)
        ) {
          System.out.println(i+4);
          break;
        } 
        
        //System.out.println(i + ": " + line.charAt(i));
      }
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    }
  }
}
