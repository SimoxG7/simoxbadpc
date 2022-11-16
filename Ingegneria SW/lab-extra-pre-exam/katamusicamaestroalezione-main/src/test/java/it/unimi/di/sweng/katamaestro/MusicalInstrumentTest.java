package it.unimi.di.sweng.katamaestro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.assertj.core.api.Assertions.assertThat;


@Timeout(2)
public class MusicalInstrumentTest {

    @Test
    public void testTrumpet() {
        MusicalInstrument sut = new Trumpet();
        assertThat(sut.play()).isEqualTo("pepepe");
    }

    @Test
    public void testHorn() {
        MusicalInstrument sut = new Horn();
        assertThat(sut.play()).isEqualTo("papapa");
    }

    @Test
    public void testWaterGlass() {
        WaterGlass sut = new WaterGlass();
        assertThat(sut.tap()).isEqualTo("diding");
    }

    @Test
    public void testIronRod() {
        GermanPercussion sut = new IronRod();
        assertThat(sut.spiel()).isEqualTo("tatang");
    }

    @Test
    public void testWaterGlassMI() {
        MusicalInstrument sut = new WaterGlassMI();
        assertThat(sut.play()).isEqualTo("diding");
    }

    @Test
    public void testIronRodMI() {
        MusicalInstrument sut = new GermanPercussionMI(new IronRod());
        assertThat(sut.play()).isEqualTo("tatang");
    }

    @Test
    public void testOrchestra() {
        MusicalInstrument m1 = new Trumpet();
        MusicalInstrument m2 = new GermanPercussionMI(new IronRod());
        MusicalInstrument m3 = new WaterGlassMI();

        Orchestra sut = new Orchestra();
        
        sut.add(m1);
        sut.add(m2);
        sut.add(m3);

        assertThat(sut.play()).isEqualTo("pepepe\ntatang\ndiding");
    }
}
