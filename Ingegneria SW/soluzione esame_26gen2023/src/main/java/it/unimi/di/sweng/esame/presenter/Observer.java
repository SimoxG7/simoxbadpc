package it.unimi.di.sweng.esame.presenter;

import org.jetbrains.annotations.NotNull;

public interface Observer<T> {
  void update(@NotNull T state);
}
