package it.unimi.di.sweng.reverseindex;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseIndex {

    List<String> lines = new ArrayList<>();

    public ReverseIndex(StringReader input) {
        Scanner scanner = new Scanner(input);

        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            sb.append(i)
                    .append(": ")
                    .append(lines.get(i))
                    .append("\n");
        }
        sb.deleteCharAt(sb.length() -1);
        return sb.toString();
    }

    /* OLD
    private final List<WordOccurrence> words = new ArrayList<>();

    public void readDoc(String s) {
        Scanner scanner = new Scanner(s);
        int index = 0;
        while (scanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(scanner.nextLine());
            while (lineScanner.hasNext()) {
                String word = lineScanner.next();
                boolean contains = false;
                WordOccurrence wordOccurrence = null;
                for (WordOccurrence wo : words) {
                    if (wo.getWord().equals(word)) {
                        contains = true;
                        wordOccurrence = wo;
                        break;
                    }
                }
                if (contains) {
                    words.get(words.indexOf(wordOccurrence)).addOccurrence(index);
                } else {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(index);
                    words.add(new WordOccurrence(word, newList));
                }
            }
            index++;
        }
    }

    public String getResult() {
        StringBuilder sb = new StringBuilder();
        for (WordOccurrence wordOccurrence : words) {
            sb.append(wordOccurrence.getWord());
            sb.append(" ");
            sb.append(wordOccurrence.list().toString());
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    */

}
