package it.unimi.di.sweng.reverseindex;

import java.util.Map;
import java.util.Set;

public class SimpleFormatterStrategy implements FormatStrategy {
  @Override
  public void formatEntry(StringBuilder sb, Map.Entry<String, Set<Integer>> entry) {
    sb.append(entry.getKey())
        .append(' ')
        .append(entry.getValue())
        .append('\n');
  }
}
