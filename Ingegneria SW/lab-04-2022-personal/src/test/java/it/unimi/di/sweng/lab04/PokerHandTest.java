package it.unimi.di.sweng.lab04;


import static org.assertj.core.api.Assertions.*;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


@Timeout(2)
public class PokerHandTest {



  @Test
  public void noInputOrEmptyInputTest() {
    PokerHand p = new PokerHand("");
    assertThat(p.toString()).isEqualTo("");
  }

  @Test
  public void pokerTest() {

    Deck d = new Deck();
    for (int i = 0; i<52; i++)
      d.draw();
    d.push(Card.get(Rank.FOUR, Suit.CLUBS));
    d.push(Card.get(Rank.FOUR, Suit.SPADES));
    d.push(Card.get(Rank.FOUR, Suit.DIAMONDS));
    d.push(Card.get(Rank.FOUR, Suit.HEARTS));
    d.push(Card.get(Rank.FIVE, Suit.HEARTS));
    PokerHand p = new PokerHand(5, d);
    assertThat(p.getRank()).isEqualTo(HandRank.FOUR_OF_A_KIND);
  }

  @Test
  public void flushTest() {

    Deck d = new Deck();
    for (int i = 0; i<52; i++)
      d.draw();
    d.push(Card.get(Rank.FOUR, Suit.CLUBS));
    d.push(Card.get(Rank.FIVE, Suit.CLUBS));
    d.push(Card.get(Rank.SIX, Suit.CLUBS));
    d.push(Card.get(Rank.SEVEN, Suit.CLUBS));
    d.push(Card.get(Rank.EIGHT, Suit.CLUBS));
    PokerHand p = new PokerHand(5, d);
    assertThat(p.getRank()).isEqualTo(HandRank.FLUSH);
  }
}
