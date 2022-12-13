import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution2 {
  public static void main(String[] args) {
    Path path = Paths.get("input.txt");

    try {
      Scanner scanner = new Scanner(path);
      String line = scanner.nextLine();
      int len = line.length();
      scanner.close();

      ArrayList<Character> lst = new ArrayList<>();

      for (int i = 14; i < len; i++) {
        
        lst = new ArrayList<>();
        for (int j = i-14; j < i; j++) {
          lst.add(line.charAt(j));
        }

        Collections.sort(lst);

        boolean same = false;
        for (int j = 0; j < 13; j++) {
          if (lst.get(j) == lst.get(j+1)) same = true;
        }
        
        if (!same) {
          System.out.println(i);
          break;
        } 
        
      }
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    }
  }
}
