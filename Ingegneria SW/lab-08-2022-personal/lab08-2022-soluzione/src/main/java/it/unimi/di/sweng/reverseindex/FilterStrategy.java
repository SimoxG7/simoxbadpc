package it.unimi.di.sweng.reverseindex;

import java.util.Iterator;

public interface FilterStrategy {
  Iterator<String> getScanner(String s);
}
