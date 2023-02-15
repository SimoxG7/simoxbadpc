package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalTime;

public record Train(@NotNull String code, @NotNull String destination, @NotNull LocalTime time, @NotNull Duration delay) {
  @Override
  public String toString() {
    return code + " " + destination + " " + time + " " + delay.toMinutes();
  }
}
