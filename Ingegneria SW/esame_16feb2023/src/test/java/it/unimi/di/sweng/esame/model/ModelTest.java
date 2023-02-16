package it.unimi.di.sweng.esame.model;

import static org.junit.jupiter.api.Assertions.*;
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
    List<NazionePunteggio> nazioni = SUT.getState();
    assertThat(nazioni.size()).isEqualTo(16);
    assertThat(nazioni.toString()).contains("IT Italia 0");
  }

}