package it.unimi.di.sweng.reverseindex;

import java.io.Reader;
import java.util.*;

public class InvertedIndex {
  private List<String> lines = new ArrayList<>();
  private Map<String, Set<Integer>> index = new LinkedHashMap<>();
  private FormatStrategy formatStrategy;
  private OrderStrategy orderStrategy;
  private FilterStrategy filter;

  public InvertedIndex setOrderStrategy(OrderStrategy orderStrategy) {
    this.orderStrategy = orderStrategy;
    return this;
  }


  public InvertedIndex setFormatStrategy(FormatStrategy formatStrategy) {
    this.formatStrategy = formatStrategy;
    return this;
  }


  public InvertedIndex(Reader input) {
    Scanner scanner = new Scanner(input);
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < lines.size(); i++) {
      sb.append(i)
          .append(": ")
          .append(lines.get(i))
          .append('\n');
    }
    return sb.toString();
  }

  public void create() {
    for (int i = 0; i < lines.size(); i++) {
      Iterator<String> scanner;
      if (filter == null)
        scanner = new Scanner(lines.get(i));
      else
        scanner = filter.getScanner(lines.get(i));
      while (scanner.hasNext())
        addNewEntryDocument(i, scanner.next());
    }
  }

  private void addNewEntryDocument(int documentID, String word) {
    Set<Integer> docs = index.getOrDefault(word, new TreeSet<>());
    docs.add(documentID);
    index.putIfAbsent(word, docs);
  }

  public String output() {
    StringBuilder sb = new StringBuilder();

    List<Map.Entry<String, Set<Integer>>> orderedIndex =  new ArrayList<>(index.entrySet());

    if (orderStrategy != null)
      orderedIndex.sort(orderStrategy);

    for (Map.Entry<String, Set<Integer>> entry : orderedIndex) {
      formatStrategy.formatEntry(sb, entry);
    }
    return sb.toString();
  }

  public InvertedIndex setFilterStrategy(FilterStrategy filter) {
    this.filter = filter;
    return this;
  }
}
