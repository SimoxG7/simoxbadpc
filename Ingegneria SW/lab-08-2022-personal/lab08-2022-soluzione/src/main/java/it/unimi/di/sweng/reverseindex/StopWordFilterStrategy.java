package it.unimi.di.sweng.reverseindex;

import java.util.Iterator;
import java.util.Scanner;

public class StopWordFilterStrategy implements FilterStrategy, Iterator<String> {
  private Scanner scanner;
  private StringBuilder stopWords = new StringBuilder();
  private StringBuilder stopChars = new StringBuilder();

  public StopWordFilterStrategy addStopWords(String... word) {
    for (String s : word) {
      stopWords.append('|').append(s);
    }
    return this;
  }

  public StopWordFilterStrategy addStopChar(char ch) {
    stopChars.append(ch);
    return this;
  }
  @Override
  public Iterator<String> getScanner(String s) {
    scanner = new Scanner(s.replaceAll("["+stopChars+"]", " " ));
    return this;
  }

  @Override
  public boolean hasNext() { //mi dice se c'è nello stream un token valido
    while (scanner.hasNext(stopWords.toString()))
      scanner.next();
    return scanner.hasNext();
  }

  @Override
  public String next() { // mi prende il prossimo token valido
    hasNext();
    return scanner.next();
  }
}
