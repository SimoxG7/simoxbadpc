import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<Integer> findSubstring(String s, String[] words) {
    if (s.isEmpty() || words.length == 0) {
      return new ArrayList<>();
    }
    List<Integer> indexes = new ArrayList<>();
    Map<String, Integer> wordCounts = new HashMap<>();
    for (String word : words) {
      wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
    }
    Map<String, Integer> currentCounts = new HashMap<>();
    int wordLength = words[0].length();
    int totalLength = wordLength * words.length;

    for (int i = 0; i < s.length() - totalLength + 1; i++) {
      currentCounts.clear();
      for (int j = 0; j < words.length; j++) {
        String word = s.substring(i + j * wordLength, i + (j + 1) * wordLength);
        currentCounts.put(word, currentCounts.getOrDefault(word, 0) + 1);
      }
      if (currentCounts.equals(wordCounts)) {
        indexes.add(i);
      }
    }
    return indexes;
  }
}