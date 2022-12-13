import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Solution2 {
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

      int max = 0;

      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix.length; j++) {

          if (i == 0 || j == 0 || i == matrix.length -1 || j == matrix.length -1) continue;
  
          int right = 1;
          int left = 1;
          int up = 1;
          int down = 1;

          //check right
          for (int h = j+1; h < matrix.length-1; h++) {
            if (matrix[i][h] < matrix[i][j]) right++;
            else break; 
          }
          //check left
          for (int h = j-1; h > 0; h--) {
            if (matrix[i][h] < matrix[i][j]) left++;
            else break; 
          }
          //check up
          for (int h = i-1; h > 0; h--) {
            if (matrix[h][j] < matrix[i][j]) up++;
            else break; 
          }
          //check down
          for (int h = i+1; h < matrix.length-1; h++) {
            if (matrix[h][j] < matrix[i][j]) down++;
            else break; 
          }
          int res = down * right * up * left;
          //System.out.println("i: " + i + ", j: " + j + ", down: " + down + ", up: " + up + ", left: " + left + ", right: " + right + ", res: " + res);
          if (res > max) max = res;
        }
      }
      System.out.println(max);

    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    }
  }
}
