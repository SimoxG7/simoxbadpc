import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Path path = Paths.get("input.txt");

    int cont = 0;

    try {
      Scanner scanner = new Scanner(path);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String all[] = line.split(",");
        String r1[] = all[0].split("-");
        int start1 = Integer.parseInt(r1[0]);
        int end1 = Integer.parseInt(r1[1]);
        String r2[] = all[1].split("-");
        int start2 = Integer.parseInt(r2[0]);
        int end2 = Integer.parseInt(r2[1]);

        if ((start1 >= start2 && end1 <= end2) || (start2 >= start1 && end2 <= end1)) {
          cont++;
        }
      }
      scanner.close();
      System.out.println(cont);
      
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    }
  }
}
