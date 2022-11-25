package it.unimi.di.sweng.reverseindex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseIndex {

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
}
