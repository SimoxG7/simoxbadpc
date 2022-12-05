import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
  public static void main(String[] args) {
    Path path = Paths.get("input.txt");
    List<Stack<Character>> lst = new ArrayList<>();

    try {
      Scanner scanner = new Scanner(path);

      String line = scanner.nextLine();
      
      int linecont = 1;

      for (int i = 0; i < (line.length()+1)/4; i++) {
        Stack<Character> s = new Stack<>();
        lst.add(s);
      }

      for (int i = 1;i < line.length(); i += 4) {
        if (line.charAt(i) != ' ') lst.get(i/4).push(line.charAt(i));
      }

      while (scanner.hasNextLine()) {
        //do stuff here
        line = scanner.nextLine();
        if (linecont < 9) {
          for (int i = 1;i < line.length(); i += 4) {
            if (line.charAt(i) != ' ') lst.get(i/4).push(line.charAt(i));
          }
        } else if (linecont > 10) {
          //parse instructions here
          String cmd[] = line.split(line, linecont)
        }
        linecont++;
      }
      scanner.close();
      System.out.println(res);
      
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    }
  }
}
