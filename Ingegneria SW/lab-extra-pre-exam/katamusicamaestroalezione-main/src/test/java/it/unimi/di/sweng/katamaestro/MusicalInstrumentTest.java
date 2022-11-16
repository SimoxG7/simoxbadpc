package it.unimi.di.sweng.katamaestro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;


@Timeout(2)
public class MusicalInstrumentTest {

    @Test
    public void testTrumpet() {
        MusicalInstrument horn = new Trumpet();
        assertThat(horn.play()).isEqualTo("pepepe");
    }
}
