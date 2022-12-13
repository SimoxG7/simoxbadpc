import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Solution2 {
    public static void main(String[] args) {
    Path path = Paths.get("input.txt");
    int points = 0;    
    List<String> lst1 = new ArrayList<>();
    lst1.add("A"); //r
    lst1.add("B"); //p
    lst1.add("C"); //s

    List<String> lst2 = new ArrayList<>();
    lst2.add("X"); //r
    lst2.add("Y"); //p 
    lst2.add("Z"); //s

    boolean win1 = false;
    boolean win2 = false;
    boolean draw = false;

    try {
      Scanner scanner = new Scanner(path);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine(); 
        String[] vals = line.split(" ");

        int index1 = lst1.indexOf(vals[0]);
        int outcome = lst2.indexOf(vals[1]);
        int chosen = 0;

        if (outcome == 0) {
          if (index1 > 0) chosen = index1-1;
          else chosen = 2;
          win1 = true;
        } else if (outcome == 1) {
          chosen = index1;
          draw = true;
        } else {
          if (index1 < 2) chosen = index1+1;
          else index1 = 0;
          win2 = true;
        }
        

        //System.out.println(points);
        points += chosen + 1;
        if (win1) points += 0;
        else if (draw) points += 3;  
        else if (win2) points += 6;
        //System.out.println(points);

        win1 = false;
        win2 = false;
        draw = false;
      }
      scanner.close();
      System.out.println(points);
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    } 
  }
}
