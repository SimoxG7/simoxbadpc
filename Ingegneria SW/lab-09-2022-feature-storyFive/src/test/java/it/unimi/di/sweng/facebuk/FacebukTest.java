package it.unimi.di.sweng.facebuk;

import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class FacebukTest {

    //################ STORY 1 #############

    @Test
    public void leggiTest(){
        Facebuk fb = new Facebuk(new StringReader("Mario Roberta Luca Filippo"));
        assertThat(fb.toString()).isEqualTo("Mario [Roberta Luca Filippo]");
    }

    @Test
    public void leggiDiverseRigheTest(){
        Facebuk fb = new Facebuk(new StringReader("Mario Roberta Luca Filippo\n" +
                "Roberta Mario Luca Filippo Anna\n" +
                "Luca Mario Roberta Filippo Anna\n" +
                "Filippo Mario Roberta Luca Anna\n" +
                "Anna Roberta Luca Filippo"));
        assertThat(fb.toString()).isEqualTo("Mario [Roberta Luca Filippo]\n" +
                "Roberta [Mario Luca Filippo Anna]\n" +
                "Luca [Mario Roberta Filippo Anna]\n" +
                "Filippo [Mario Roberta Luca Anna]\n" +
                "Anna [Roberta Luca Filippo]");
    }

    //################ STORY 2 #############

    @Test
    public void listaAmiciAssociataTest(){
        Facebuk fb = new Facebuk(new StringReader("Mario Roberta Luca Filippo\n" +
                "Roberta Mario Luca Filippo Anna\n" +
                "Luca Mario Roberta Filippo Anna\n" +
                "Filippo Mario Roberta Luca Anna\n" +
                "Anna Roberta Luca Filippo"));
        assertThat(fb.amiciDi("Luca")).containsExactlyInAnyOrder("Mario","Roberta","Filippo","Anna");
    }

    @Test
    public void utenteNonEsisteTest(){
        Facebuk fb = new Facebuk(new StringReader("Mario Roberta Luca Filippo\n" +
                "Roberta Mario Luca Filippo Anna\n" +
                "Luca Mario Roberta Filippo Anna\n" +
                "Filippo Mario Roberta Luca Anna\n" +
                "Anna Roberta Luca Filippo"));
        assertThatThrownBy(() -> {fb.amiciDi("Pinuzzazzo");}).isInstanceOf(NoSuchElementException.class);
    }

    //################ STORY 3 #############

    @Test
    public void punteggiaturaMaiuscoleTest(){
        Facebuk fb = new Facebuk(new StringReader("mario,!? Roberta: Luca_Filippo\n" + "Roberta !!mario Luca'.Filippo anna"));
        assertThat(fb.toString()).isEqualTo("Mario [Roberta Luca Filippo]\n" +
                "Roberta [Mario Luca Filippo Anna]");
    }
    //################ STORY 4 #############

    @Test
    public void amiciInComune(){
        Facebuk fb = new Facebuk(new StringReader("Mario Roberta Luca\n" +
                "Roberta Mario Luca\n" +
                "Luca Roberta Mario"));

        assertThat(fb.amiciInComune()).isEqualTo(
                "(Mario Roberta) [Luca]\n" +
                        "(Mario Luca) [Roberta]\n" +
                "(Roberta Luca) [Mario]");
    }
    //################ STORY 5 #############

    @Test
    public void amiciInComuneInNumero(){
        Facebuk fb = new Facebuk(new StringReader("Mario Roberta Luca\n" +
                "Roberta Mario Luca\n" +
                "Luca Roberta Mario"));
        FormatStrategy fs = new InNumeroStrategy();
        fb.setFormatStrategy(fs);

        assertThat(fb.amiciInComune()).isEqualTo(
                "(Mario Roberta) 1\n" +
                        "(Mario Luca) 1\n" +
                        "(Roberta Luca) 1");
    }
    

}
