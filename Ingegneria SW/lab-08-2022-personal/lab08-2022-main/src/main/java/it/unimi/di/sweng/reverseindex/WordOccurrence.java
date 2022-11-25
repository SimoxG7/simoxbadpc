package it.unimi.di.sweng.reverseindex;

import java.util.ArrayList;
import java.util.List;

class WordOccurrence {

    private List<Integer> list = new ArrayList<>();
    private String word;

    WordOccurrence(String word, List<Integer> list) {
        this.word = word;
        this.list = list;
    }

    void addOccurrence(int index) {
        list.add(index);
    }

    public List<Integer> getList() {
        return list;
    }

    public String getWord() {
        return word;
    }

    public List<Integer> list() {
        return List.copyOf(list);
    }

}
