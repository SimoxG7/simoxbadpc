import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Path path = Paths.get("input.txt");

    Tree<File> root = new Tree<File>(new File("/", 0, false));

    try {
      Scanner scanner = new Scanner(path);
      
      while(scanner.hasNextLine()) {
        String line = scanner.nextLine();

        if (line.charAt(0) == '$') {
          //command
          String splitted[] = line.split(" ");
          if (splitted[1] == "ls") {
            //handle list of files
          } else if (splitted[1] == "cd") {
            //handle directory change
          }

        } else {
          //command ouput
        }
      }

      

      scanner.close();
    } catch (IOException e) {
      System.out.println("Errore nella lettura del file.");
    }
  }
}

