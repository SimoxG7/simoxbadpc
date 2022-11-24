package it.unimi.di.sweng.reverseindex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

public class InvertedIndexTest {

    @Test
    public void readDocumentsTest() {
        InvertedIndex invertedIndex = new InvertedIndex();
        invertedIndex.readDoc("sotto la panca\nla capra campa");
        assertThat(invertedIndex).containsExactlyInAnyOrder("sotto", "la", "panca", "capra", "campa");
    }

    @Test
    public void wordCountTest() {
        InvertedIndex invertedIndex = new InvertedIndex();
        invertedIndex.readDoc("sotto la\nla campa");
        String wordCount = invertedIndex.wordCount();
        assertThat(wordCount).contains("sotto [0]", "la [0, 1]", "campa [1]");
    }

    @Test
    public void alphabeticOrderTest() {
        InvertedIndex invertedIndex = new InvertedIndex();
        invertedIndex.readDoc("abbaecedario sotto\nla campa");
        IndexStrategy strat = new AlphabeticStrategy();
        invertedIndex.setIndexStrategy(strat);
        String wordCount = invertedIndex.wordCount();
        assertThat(wordCount).isEqualTo("abbaecedario [0]\ncampa [1]\nla [1]\nsotto [0]");
    }

    @Test
    public void valueOrderTest() {
        InvertedIndex invertedIndex = new InvertedIndex();
        invertedIndex.readDoc("la sotto\nla campa\n la sotto");
        IndexStrategy strat = new ValueStrategy();
        invertedIndex.setIndexStrategy(strat);
        String wordCount = invertedIndex.wordCount();
        assertThat(wordCount).isEqualTo("la [0, 1, 2]\nsotto [0, 2]\ncampa [1]");
    }

    @Test
    public void paddedIndexTest(){
        InvertedIndex invertedIndex = new InvertedIndex();
        invertedIndex.readDoc("la sotto\nla campana");
        IndexStrategy strat = new PaddedStrategy();
        invertedIndex.setIndexStrategy(strat);
        String wordCount = invertedIndex.wordCount();
        assertThat(wordCount).contains("campana [1]", "la      [0, 1]", "sotto   [0]");
    }
}
