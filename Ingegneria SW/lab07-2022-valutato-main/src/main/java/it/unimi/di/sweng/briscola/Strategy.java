package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

public interface Strategy {
  @NotNull
  Card chooseCard(@NotNull Player me, @NotNull Player other, @NotNull Suit briscola);
}
