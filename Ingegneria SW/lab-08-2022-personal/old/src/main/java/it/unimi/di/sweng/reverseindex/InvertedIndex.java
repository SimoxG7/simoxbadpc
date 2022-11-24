package it.unimi.di.sweng.reverseindex;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class InvertedIndex implements Iterable<String> {

    private IndexStrategy strategy;
    private final Map<String, List<Integer>> words = new HashMap<>();

    public void readDoc(String s)  {
        int index = 0;
        Scanner scanner = new Scanner(s);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner inner = new Scanner(line);
            while (inner.hasNext()) {
                String next = inner.next();
                if (words.containsKey(next)) {
                    words.get(next).add(index);
                }else{
                    List<Integer> nextList = new ArrayList<>();
                    nextList.add(index);
                    words.put(next, nextList);
                }
            }
            index++;
        }
    }

    public void setIndexStrategy(@NotNull IndexStrategy strategy){
        this.strategy = strategy;
    }

    @NotNull
    @Override
    public Iterator<String> iterator() {
        return Collections.unmodifiableSet(words.keySet()).iterator();
    }

    public String wordCount() {
        return strategy.indexToString(Collections.unmodifiableMap(words));
    }
}
