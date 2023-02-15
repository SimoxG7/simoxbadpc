package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

public interface Observable<T> {
  @NotNull T getState();

  void notifyObservers();
}
