package it.unimi.di.sweng.esame.model;

import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ModelTest {
  @Test
  void getStateEmpty() {
    Model SUT = new Model();
    assertThat(SUT.getState()).isEmpty();
  }

  @Test
  void getStateAfterReadFile() {
    Model SUT = new Model();
    SUT.readFile();
    List<Train> trains = SUT.getState();
    assertThat(trains.size()).isEqualTo(20);
    assertThat(trains).extracting("code", "destination").contains(tuple("TN 10471", "STRADELLA"));
    assertThat(trains.toString()).contains(("TN 10471 STRADELLA 14:54 10"));
  }
}