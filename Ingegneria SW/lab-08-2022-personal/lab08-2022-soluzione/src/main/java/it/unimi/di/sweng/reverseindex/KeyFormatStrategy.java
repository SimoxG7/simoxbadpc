package it.unimi.di.sweng.reverseindex;

import java.util.Map;
import java.util.Set;

public class KeyFormatStrategy implements FormatStrategy {

  private String format;

  public KeyFormatStrategy(String format) {
    this.format = format;
  }

  @Override
  public void formatEntry(StringBuilder sb, Map.Entry<String, Set<Integer>> entry) {


    String formattedKey = String.format(format, entry.getKey());
      sb.append(formattedKey)
          .append(entry.getValue())
          .append('\n');

  }
}
