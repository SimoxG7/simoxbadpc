package it.unimi.di.sweng.reverseindex;


import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


public class ExampleTest {

  @Test
  public void testStoria1() {
    String input = "Sopra la panca la capra campa\n" +
        "sotto la panca\n" +
        "la capra crepa";

    InvertedIndex index = new InvertedIndex(new StringReader(input));

    assertThat(index.toString()).isEqualTo("0: Sopra la panca la capra campa\n" +
        "1: sotto la panca\n" +
        "2: la capra crepa\n");
  }

  @Test
  public void testStoria6() {
    String input = "Sopra la panca\nla capra";

    InvertedIndex index = new InvertedIndex(new StringReader(input))
        .setFormatStrategy(new SimpleFormatterStrategy());

    index.create();

    assertThat(index.output()).isEqualTo("Sopra [0]\nla [0, 1]\npanca [0]\ncapra [1]\n");
  }

  @Test
  public void testStoria10() {
    String input = "Sopra la panca\nla capra";

    InvertedIndex index = new InvertedIndex(new StringReader(input))
        .setFormatStrategy(new KeyFormatStrategy("%-10s"));

    index.create();

    assertThat(index.output()).isEqualTo("Sopra     [0]\nla        [0, 1]\npanca     [0]\ncapra     [1]\n");
  }

  @Test
  public void testStoria4() {
    String input = "Sopra la panca\nla capra";

    InvertedIndex index = new InvertedIndex(new StringReader(input))
        .setOrderStrategy(new AlphabeticalOrder())
        .setFormatStrategy(new SimpleFormatterStrategy());

    index.create();

    assertThat(index.output()).isEqualTo("Sopra [0]\ncapra [1]\nla [0, 1]\npanca [0]\n");
  }


  @Test
  public void testStoria3() {
    String input = "Sopra la panca\nla capra";

    InvertedIndex index = new InvertedIndex(new StringReader(input))
        .setOrderStrategy(new ReferenceNumbersOrder())
        .setFormatStrategy(new SimpleFormatterStrategy());

    index.create();

    assertThat(index.output()).isEqualTo("la [0, 1]\nSopra [0]\ncapra [1]\npanca [0]\n");
  }

  @Test
  public void testStoria8_5() {
    String input = "Sopra,pippo, la !!panca\nla capra!!";

    StopWordFilterStrategy filter = new StopWordFilterStrategy()
        .addStopWords("la","pippo")
        .addStopChar(',')
        .addStopChar('!');

    InvertedIndex index = new InvertedIndex(new StringReader(input))
        .setOrderStrategy(new AlphabeticalOrder())
        .setFormatStrategy(new SimpleFormatterStrategy())
        .setFilterStrategy(filter);

    index.create();

    assertThat(index.output()).isEqualTo("Sopra [0]\ncapra [1]\npanca [0]\n");
  }
}
