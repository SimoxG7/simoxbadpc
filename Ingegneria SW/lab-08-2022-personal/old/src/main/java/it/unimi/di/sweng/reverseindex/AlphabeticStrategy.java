package it.unimi.di.sweng.reverseindex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AlphabeticStrategy implements IndexStrategy{
    @Override
    public String indexToString(Map<String, List<Integer>> words) {
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<>(words.keySet());
        Collections.sort(keys);
        for(String s : keys){
            sb.append(s).append(" ").append(words.get(s).toString()).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
