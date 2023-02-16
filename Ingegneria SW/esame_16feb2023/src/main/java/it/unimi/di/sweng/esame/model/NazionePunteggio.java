package it.unimi.di.sweng.esame.model;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

//questa classe era un record, ma essendo un record era final e mockito non mi consentiva il mocking della classe (avrei dovuto aggiungere una riga al file build.gradle, non so se fosse permessa o meno tale aggiunta)
public class NazionePunteggio implements Comparable<NazionePunteggio> {
  private final String code;
  private final String fullname;
  private final int points;

  public NazionePunteggio(String code, String fullname, int points) {
    this.code = code;
    this.fullname = fullname;
    this.points = points;
  }

  @Override
  public String toString() {
    return fullname + " " + points;
  }

  public String code() {
    return code;
  }

  public String fullname() {
    return fullname;
  }

  public int points() {
    return points;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (NazionePunteggio) obj;
    return Objects.equals(this.code, that.code) &&
        Objects.equals(this.fullname, that.fullname) &&
        this.points == that.points;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, fullname, points);
  }

  @Override
  public int compareTo(@NotNull NazionePunteggio other) {
    int diff = Integer.compare(this.points(), other.points());
    /*
    if (diff == 0) {
      return this.fullname().compareTo(other.fullname());
    } else return diff;
    */
    return diff;
  }
}
