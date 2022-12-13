import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
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
        int index2 = lst2.indexOf(vals[1]);

        if ((index1 == 0 && index2 == 2)) {
          win1 = true;
        } else if (index2 == 0 && index1 == 2) {
          win2 = true;
        } else if (index1 == index2) {
          draw = true;
        } else if (index1 > index2) {
          win1 = true;
        } else {
          win2 = true;
        }

        //System.out.println(points);
        points += index2 + 1;
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
