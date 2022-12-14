package it.unimi.di.sweng.reverseindex;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
public class ReverseIndexTest {

    /*
    @Test
    public void readDocTest() {
        ReverseIndex reverseIndex = new ReverseIndex();
        reverseIndex.readDoc("sopra la panca\nla capra canta");
        assertThat(reverseIndex.getResult()).isEqualTo("sopra [0]\nla [0, 1]\npanca [0]\ncapra [1]\ncanta [1]");
    }
    */

    @Test
    public void testStoria1() { //con reader testiamo anche storia 9. Scanner aiuta da quel punto di vista, tratta reader senza problemi.
        String input = "Sopra la panca la capra campa\n" +
                "sotto la panca\n" +
                "la capra crepa";
        ReverseIndex index = new ReverseIndex(new StringReader(input));
        assertThat(index.toString()).isEqualTo("0: Sopra la panca la capra campa\n" +
                "1: sotto la panca\n" +
                "2: la capra crepa");
    }




}
