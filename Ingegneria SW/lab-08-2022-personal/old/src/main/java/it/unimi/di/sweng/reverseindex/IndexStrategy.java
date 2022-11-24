package it.unimi.di.sweng.reverseindex;

import java.util.List;
import java.util.Map;

public interface IndexStrategy {
    public String indexToString(Map<String, List<Integer>> words);
}
