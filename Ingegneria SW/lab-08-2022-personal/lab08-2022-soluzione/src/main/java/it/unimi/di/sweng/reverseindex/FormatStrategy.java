package it.unimi.di.sweng.reverseindex;

import java.util.Map;
import java.util.Set;

public interface FormatStrategy {
  void formatEntry(StringBuilder sb, Map.Entry<String, Set<Integer>> entry);
}
