package it.unimi.di.sweng.reverseindex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReverseIndex {

    private class WordOccurrence {

        private final String word;
        private List<Integer> list;
        public WordOccurrence(String word, List<Integer> list) {
            this.word = word;
            this.list = List.copyOf(list);
        }

        public void addOccurrence(int index) {
            list.add(index);
        }

    }

    private List<WordOccurrence> words = new ArrayList<>();

    public void readDoc(String s) {
        Scanner scanner = new Scanner(s);
        int index = 0;
        while (scanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(scanner.nextLine());
            while (lineScanner.hasNext()) {
                String word = lineScanner.next();
                boolean contains = false;
                for (WordOccurrence wo : words) {
                    if (wo.word.equals(word)) contains = false;
                }
                if (contains) {
                    words.get(words.indexOf(word)).addOccurrence(index);
                } else {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(index);
                    words.add(new WordOccurrence(word, List.copyOf(newList)));
                }
            }
            index++;
        }
    }

    public String getResult() {
        StringBuilder sb = new StringBuilder();
        while ()
    }
}
