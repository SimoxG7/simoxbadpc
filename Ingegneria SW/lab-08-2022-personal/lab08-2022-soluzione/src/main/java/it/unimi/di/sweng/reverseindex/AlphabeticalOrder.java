package it.unimi.di.sweng.reverseindex;

import java.util.Map;
import java.util.Set;

public class AlphabeticalOrder implements OrderStrategy {
  @Override
  public int compare(Map.Entry<String, Set<Integer>> o1, Map.Entry<String, Set<Integer>> o2) {
    return o1.getKey().compareTo(o2.getKey());
  }
}
