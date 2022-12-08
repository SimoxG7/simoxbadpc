import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Solution {
  public static void main(String[] args) {
    Path path = Paths.get("input.txt");
    int[][] matrix = new int[99][99];

    try {
      Scanner scanner = new Scanner(path);
      int linecounter = 0;
      
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        for (int i = 0; i < line.length(); i++) {
          matrix[linecounter][i] = Integer.parseInt(String.valueOf(line.charAt(i)));
        }
        linecounter++;
      }
      scanner.close();

      int visible = 0;

      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix.length; j++) {
          if (i == 0 || j == 0 || i == matrix.length -1 || j == matrix.length - 1) {
            visible++;
          } else {
            /* 
              if (matrix[i][h] < matrix[i][j] || matrix[h][j] < matrix[i][j]) {
                //System.out.println(i + " " + j);
                visible++;
                break;
              }
            */
            //these booleans express if is not visible
            boolean right = true;
            boolean left = true;
            boolean up = true;
            boolean down = true;

            //check right
            for (int h = j+1; h < matrix.length; h++) {
              if (matrix[i][h] >= matrix[i][j]) right = false; 
            }
            //check left
            for (int h = 0; h < j; h++) {
              if (matrix[i][h] >= matrix[i][j]) left = false; 
            }
            //check up
            for (int h = 0; h < i; h++) {
              if (matrix[h][j] >= matrix[i][j]) up = false; 
            }
            //check down
            for (int h = i+1; h < matrix.length; h++) {
              if (matrix[h][j] >= matrix[i][j]) down = false; 
            }

            if (down || right || up || left) visible++;
            
          }
        }
      }
      System.out.println(visible);

    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    }
  }
}
