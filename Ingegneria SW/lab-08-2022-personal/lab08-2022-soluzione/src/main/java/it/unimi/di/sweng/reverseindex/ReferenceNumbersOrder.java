package it.unimi.di.sweng.reverseindex;

import java.util.Map;
import java.util.Set;

public class ReferenceNumbersOrder implements OrderStrategy {
  @Override
  public int compare(Map.Entry<String, Set<Integer>> o1, Map.Entry<String, Set<Integer>> o2) {
    if (o2.getValue().size() == o1.getValue().size() )
      return o1.getKey().compareTo(o2.getKey());
    return Integer.compare(o2.getValue().size(), o1.getValue().size());
  }
}
