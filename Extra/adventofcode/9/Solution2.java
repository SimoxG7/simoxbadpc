import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Solution2 {
  public static void main(String[] args) {
    Path path = Paths.get("testinput.txt");
    boolean[][] matrix = new boolean[360][360];
    int headx = 180, heady = 180; //tailx = headx, taily = heady;
    int tailsx[] = new int[9];
    int tailsy[] = new int[9];

    for (int i = 0; i < tailsx.length; i++) {
      tailsx[i] = headx;
      tailsy[i] = heady;
    }

    // boolean[][] matrix = new boolean[6][6];
    // int headx = 5, heady = 0, tailx = headx, taily = heady;


    try {
      Scanner scanner = new Scanner(path);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        char direction = line.charAt(0);
        int tomove = Integer.parseInt(line.substring(2));

        for (int i = 0; i < tomove; i++) {
          int oldheadx = headx;
          int oldheady = heady;
          switch (direction) {
            case 'D':
              if (headx == matrix.length-1) continue;
              headx++;
              break;
            case 'U':
              if (headx == 0) continue;
              headx--;
              break;
            case 'L':
              if (heady == 0) continue;
              heady--;
              break;
            case 'R':
              if (heady == matrix.length-1) continue;
              heady++;
              break;
            default:
              System.out.println("how tf did i get here");
              break;
          }

          /* 
          for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix.length; k++) {
              System.out.print(matrix[j][k] + " ");
            }
            System.out.println();
          }
          System.out.println("-----------------");
          */

          if (!((Math.abs(headx - tailsx[0] ) <= 1) && (Math.abs(heady - tailsy[0]) <= 1))) {

            for (int j = tailsx.length -1; j > 0; j--) {
              tailsx[j] = tailsx[j-1];
              tailsy[j] = tailsy[j-1];
            }

            tailsx[0] = oldheadx;
            tailsy[0] = oldheady;
          }

          matrix[tailsx[8]][tailsy[8]] = true;
        }
      }
      scanner.close();

      int cont = 0;
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix.length; j++) {
          if (matrix[i][j]) cont++;
        }
      }
      System.out.println(cont);
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    }
  }
}
 