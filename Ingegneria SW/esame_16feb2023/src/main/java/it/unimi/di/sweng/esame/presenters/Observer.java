package it.unimi.di.sweng.esame.presenters;

import org.jetbrains.annotations.NotNull;

public interface Observer<T> {
  void update(@NotNull T state);
}
