import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Soluzione {
  public static void main(String[] args) {
    Path path = Paths.get("input.txt");
    List<Integer> lst = new ArrayList<>();
    int acc = 0;
    try {
      Scanner scanner = new Scanner(path);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.isEmpty()) {
          lst.add(acc);
          acc = 0;
        } else {
          int val = Integer.parseInt(line);
          acc += val;
        }
      }
      scanner.close();
      Collections.sort(lst);
      Collections.reverse(lst);
      //System.out.println(lst.get(0));
      int res = 0;
      for (int i = 0; i < 3; i++) res += lst.get(i);
      System.out.println(res);
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    } 
  }
}


/**
 * Soluzione
 */
/* v1 done quick from the bus 
public class Soluzione {
  public static void main(String[] args) {

    Path path = Paths.get("input.txt");
    int best1 = 0;
    int best2 = 0;
    int best3 = 0;
    int acc = 0;

    try {
      Scanner scanner = new Scanner(path);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        if (line.isEmpty()) {
          if (acc > best1) {
            best3 = best2;
            best2 = best1;
            best1 = acc;
          } else if (acc > best2) {
            best3 = best2;
            best2 = acc;
          } else if (acc > best3) {
            best3 = acc;
          }
          acc = 0;
        } else {
          int val = Integer.parseInt(line);
          acc += val;
        }

      }
      System.out.println(best1+best2+best3);

    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    } 
  
  }
  
}
*/