package it.unimi.di.sweng.esame.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class NazionePunteggioTest {

  @ParameterizedTest
  @CsvSource({
      "c1,t1,10, c2,t2,2",
      "c1,t1,15, c2,t2,0",
      "c1,t1,10, c2,t2,2",
  })
  void compareToLess(String cod1, String name1, int punti1, String cod2, String name2, int punti2) {
    NazionePunteggio np1 = new NazionePunteggio(cod1, name1, punti1);
    NazionePunteggio np2 = new NazionePunteggio(cod2, name2, punti2);

    assertThat(np1.compareTo(np2)).isGreaterThan(0);
  }

  @ParameterizedTest
  @CsvSource({
      "c1,t1,10, c2,t2,10",
      "c1,t1,0, c2,t2,0",
  })
  void compareToEqual(String cod1, String name1, int punti1, String cod2, String name2, int punti2) {
    NazionePunteggio np1 = new NazionePunteggio(cod1, name1, punti1);
    NazionePunteggio np2 = new NazionePunteggio(cod2, name2, punti2);

    assertThat(np1.compareTo(np2)).isEqualTo(0);
  }

  @ParameterizedTest
  @CsvSource({
      "c1,t1,10, c2,t2,200",
      "c1,t1,15, c2,t2,20",
      "c1,t1,10, c2,t2,11",
  })
  void compareToGreater(String cod1, String name1, int punti1, String cod2, String name2, int punti2) {
    NazionePunteggio np1 = new NazionePunteggio(cod1, name1, punti1);
    NazionePunteggio np2 = new NazionePunteggio(cod2, name2, punti2);

    assertThat(np1.compareTo(np2)).isLessThan(0);
  }
}