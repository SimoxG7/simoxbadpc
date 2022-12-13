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
    List<String> stackStrings = new ArrayList<>();

    try {
      Scanner scanner = new Scanner(path);

      String line = scanner.nextLine();
      int len = line.length();
      //creating stacks
      for (int i = 0; i < (len +1)/4; i++) {
        Stack<Character> s = new Stack<>();
        lst.add(s);
      }
      stackStrings.add(line);
      
      line = scanner.nextLine();
      while (line.charAt(0) == '[') {
        stackStrings.add(line);
        line = scanner.nextLine();
      }

      for (int i = stackStrings.size()-1; i >= 0; i--) {
        String s = stackStrings.get(i);
        for (int j = 1; j < len; j +=4 ) {
          if (s.charAt(j) != ' ') lst.get(j/4).push(s.charAt(j));
        }
      }

      //System.out.println(lst.toString());

      scanner.nextLine();
        
      //System.out.println(scanner.nextLine());
      
      while (scanner.hasNextLine()) {
        line = scanner.nextLine();
        String cmd[] = line.split(" ");
        int num = Integer.parseInt(cmd[1]);
        int from = Integer.parseInt(cmd[3]);
        int to = Integer.parseInt(cmd[5]);

        Stack<Character> fromStack = lst.get(from-1);
        Stack<Character> toStack = lst.get(to-1);
        
        for (int i = 0; i < num; i++) {
          toStack.push(fromStack.pop());   
        }
      }      
      scanner.close();

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < lst.size(); i++) {
        sb.append(lst.get(i).peek());
      }

      System.out.println(lst.toString());

      System.out.println(sb.toString());
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    }
  }
}
