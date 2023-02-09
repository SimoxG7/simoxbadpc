import java.util.HashSet;

class Solution {
  public long distinctNames(String[] ideas) {
    // Group idea by their initials.
    HashSet<String>[] initialGroup = new HashSet[26];
    for (int i = 0; i < 26; ++i) {
      initialGroup[i] = new HashSet<>();
    }
    for (String idea : ideas) {
      initialGroup[idea.charAt(0) - 'a'].add(idea.substring(1));
    }

    // Calculate number of valid names from every pair of groups.
    long answer = 0;
    for (int i = 0; i < 25; ++i) {
      for (int j = i + 1; j < 26; ++j) {
        // Get the number of mutual suffixes.
        long numOfMutual = 0;
        for (String ideaA : initialGroup[i]) {
          if (initialGroup[j].contains(ideaA)) {
            numOfMutual++;
          }
        }

        // Valid names are only from distinct suffixes in both groups.
        // Since we can swap a with b and swap b with a to create two valid names,
        // multiple answer by 2.
        answer += 2 * (initialGroup[i].size() - numOfMutual) * (initialGroup[j].size() - numOfMutual);
      }
    }

    return answer;
  }
}

// import java.util.ArrayList;
// import java.util.List;

// class Solution {
// public long distinctNames(String[] ideas) {
// long res = 0;
// int n = ideas.length;
// List<String>[] dividedByInitials = new ArrayList[26];
// for (int i = 0; i < 26; i++) {
// dividedByInitials[i] = new ArrayList<>();
// }
// for (int i = 0; i < n; i++) {
// dividedByInitials[ideas[i].charAt(0) - 'a'].add(ideas[i]);
// }

// for (int i = 0; i < 26; i++) {
// for (int j = 0; j < 26; j++) {
// if (i == j) continue;
// for (int k = 0; k < dividedByInitials[i].size(); k++) {
// for (int l = 0; l < dividedByInitials[j].size(); l++) {
// String str1 = dividedByInitials[j].get(l).charAt(0) +
// dividedByInitials[i].get(k).substring(1);
// String str2 = dividedByInitials[i].get(k).charAt(0) +
// dividedByInitials[j].get(l).substring(1);
// if (!(dividedByInitials[i].contains(str2) ||
// dividedByInitials[j].contains(str1))) res++;
// }
// }
// //System.out.println("Str1: " + str1 + "; contained: " +
// ideasList.contains(str1));
// //System.out.println("Str2: " + str2 + "; contained: " +
// ideasList.contains(str2));
// }
// }
// return res;
// }
// }