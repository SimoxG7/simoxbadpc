package it.unimi.di.sweng.reverseindex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class ReverseIndexTest {

    @Test
    public void readDocTest() {
        ReverseIndex reverseIndex = new ReverseIndex();
        reverseIndex.readDoc("sopra la panca\nla capra canta");
        assertThat(reverseIndex.getResult()).isEqualTo("sopra [0]\nla [0, 1]\npanca [0]\ncapra [1]\ncanta [1]");
    }


}
