package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalTime;

public record Train(@NotNull String code, @NotNull String destination, @NotNull LocalTime time, @NotNull Duration delay) implements Comparable<Train> {
  @Override
  public String toString() {
    return code + " " + destination + " " + time + " " + delay.toMinutes();
  }

  @Override
  public int compareTo(@NotNull Train train) {
    return this.time.plus(this.delay).compareTo(train.time.plus(train.delay));
  }

  @NotNull
  public Train newDelay(Duration newDelay) {
    return new Train(code, destination, time, newDelay);
  }

}
