package it.unimi.di.sweng.temperature.presenter;

import it.unimi.di.sweng.temperature.Observer;
import org.jetbrains.annotations.NotNull;

public interface Presenter extends Observer<Double> {
  void updateModel(@NotNull String text);
}
