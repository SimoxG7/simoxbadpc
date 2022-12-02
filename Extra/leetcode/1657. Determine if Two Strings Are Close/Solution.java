import java.util.Arrays;

/**
 * Solution
 */
class Solution {
  public boolean closeStrings(String word1, String word2) {

    if (word1.length() != word2.length()) return false;

    int freq1[] = new int[26];
    int freq2[] = new int[26];

    for (int i = 0; i < word1.length(); i++) {
      freq1[word1.charAt(i) - 'a']++;
      freq2[word2.charAt(i) - 'a']++;
    }

    for (int i = 0; i < 26; i++) {
      if ((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] != 0 && freq2[i] == 0))
        return false;
    }
    
    Arrays.sort(freq1);
    Arrays.sort(freq2);

    for (int i = 0; i < 26; i++) {
      if (freq1[i] != freq2[i])
        return false;
    }

    return true;
  }
}

/*
 * works, but slow af
 * class Solution {
 * 
 * private Map<Character, Integer> map1 = new HashMap<>();
 * private Map<Character, Integer> map2 = new HashMap<>();
 * 
 * public boolean closeStrings(String word1, String word2) {
 * 
 * if (word1.length() != word2.length()) return false;
 * 
 * // int emmingdistance = 0;
 * 
 * // for (int i = 0; i < word1.length(); i++) {
 * // if (word1.charAt(i) != word2.charAt(i)) emmingdistance++;
 * // }
 * 
 * for(int i = 0; i < word1.length(); i++) {
 * char c1 = word1.charAt(i);
 * char c2 = word2.charAt(i);
 * 
 * if (map1.containsKey(c1)) {
 * map1.put(c1, map1.get(c1) + 1);
 * } else {
 * map1.put(c1, 1);
 * }
 * 
 * if (map2.containsKey(c2)) {
 * map2.put(c2, map2.get(c2) + 1);
 * } else {
 * map2.put(c2, 1);
 * }
 * }
 * 
 * ArrayList<Integer> l1 = new ArrayList<>();
 * ArrayList<Character> lc1 = new ArrayList<>();
 * ArrayList<Integer> l2 = new ArrayList<>();
 * ArrayList<Character> lc2 = new ArrayList<>();
 * 
 * for (Character c : map1.keySet()) {
 * l1.add(map1.get(c));
 * lc1.add(c);
 * }
 * 
 * for (Character c : map2.keySet()) {
 * l2.add(map2.get(c));
 * lc2.add(c);
 * }
 * 
 * Collections.sort(l1);
 * Collections.sort(l2);
 * Collections.sort(lc1);
 * Collections.sort(lc2);
 * 
 * System.out.println(l1);
 * System.out.println(l2);
 * 
 * return l1.equals(l2) && lc1.equals(lc2);
 * }
 * }
 * 
 */