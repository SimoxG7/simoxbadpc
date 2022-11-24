package it.unimi.di.sweng.reverseindex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PaddedStrategy implements IndexStrategy {

    private String spaces(int len){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++)
            sb.append(" ");
        return sb.toString();

    }
    @Override
    public String indexToString(Map<String, List<Integer>> words) {
        int maxLen = 0;
        for(String s : words.keySet()) {
            if (maxLen < s.length())
                maxLen = s.length();
        }
        maxLen++;
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<>(words.keySet());
        Collections.sort(keys);
        for(String s : keys){
            sb.append(s);
            sb.append(spaces(maxLen - s.length()));
            sb.append(words.get(s).toString()).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
