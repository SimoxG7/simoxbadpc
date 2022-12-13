import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution2 {
  public static void main(String[] args) {
    Path path = Paths.get("input.txt");

    ArrayList<Character> lst = new ArrayList<>();

    try {
      Scanner scanner = new Scanner(path);
      while (scanner.hasNextLine()) {
        String line1 = scanner.nextLine(); 
        String line2 = scanner.nextLine();
        String line3 = scanner.nextLine();

        ArrayList<Character> temp = new ArrayList<>();

        for (int i = 0; i < line1.length(); i++) {
          if (line2.contains(String.valueOf(line1.charAt(i)))) temp.add(line1.charAt(i));
        }

        for (int i = 0; i < line3.length(); i++) {
          if (temp.contains(line3.charAt(i))) {
            lst.add(line3.charAt(i));
            break;
          }
        }

        /* 
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < line1.length(); i++) {
          char c = line1.charAt(i);
          if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
          } else {
            map.put(c, 1);
          }
        }
        for (int i = 0; i < line2.length(); i++) {
          char c = line2.charAt(i);
          if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
          } else {
            map.put(c, 1);
          }
        }
        for (int i = 0; i < line3.length(); i++) {
          char c = line3.charAt(i);
          if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
          } else {
            map.put(c, 1);
          }
        }
        

        for (Character k : map.keySet()) {
          if (map.get(k) == 3) {
            lst.add(k);
            break;
          }
        }
        */
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
