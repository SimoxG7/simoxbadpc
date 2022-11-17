package it.unimi.di.sweng.briscola;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

public class BriscolaTest {
    Player p1;
    Player p2;

    @BeforeEach
    void setUp() {
        p1 = new Player("Carlo");
        p2 = new Player("Mattia");
    }

    @Test
    void testPlayerIterator() {
        List<Card> cards = List.of(
                Card.get(Rank.ASSO, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.BASTONI),
                Card.get(Rank.TRE, Suit.BASTONI)
        );

        for (Card c : cards) {
            p1.giveCard(c);
        }

        Iterator<Card> it = p1.iterator();

        int lunghezza = 0;

        for (Card c : cards) {
            lunghezza++;
            Card corrente = it.next();
            assertThat(corrente).isEqualTo(c);
        }

        assertThat(lunghezza).isEqualTo(cards.size());
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void testPlayerComparableNotEqual() {
        assertThat(p1.compareTo(p2)).isEqualTo(0);

        p1.addWonCardsToPersonalDeck(
                Card.get(Rank.TRE, Suit.BASTONI),
                Card.get(Rank.CINQUE, Suit.BASTONI)
        );

        p2.addWonCardsToPersonalDeck(
                Card.get(Rank.TRE, Suit.BASTONI),
                Card.get(Rank.CINQUE, Suit.BASTONI)
        );

        assertThat(p1.compareTo(p2)).isEqualTo(0);

        List<Card> cards1 = List.of(
                Card.get(Rank.ASSO, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.BASTONI)
        );

        for (int i = 0; i < cards1.size(); i += 2) {
            p1.addWonCardsToPersonalDeck(cards1.get(i), cards1.get(i+1));
        }

        assertThat(p1.compareTo(p2)).isGreaterThan(0);
    }

    @Test
    void randomStrategyTest() {
        List<Card> cards = List.of(
                Card.get(Rank.ASSO, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.BASTONI),
                Card.get(Rank.TRE, Suit.BASTONI)
        );

        for (Card c : cards) {
            p1.giveCard(c);
        }

        p1.setFirstStrategy(new RandomStrategy());

        Briscola b = mock(Briscola.class);
        when(b.getBriscola()).thenReturn(null);
        when(b.otherPlayer(p1)).thenReturn(null);

        assertThat(p1.chooseFirstCard(b)).isEqualTo(cards.get(0));
    }

    @Test
    void zeroPuntiStrategyTest() {
        List<Card> cards = List.of(
                Card.get(Rank.ASSO, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.BASTONI),
                Card.get(Rank.TRE, Suit.BASTONI)
        );

        for (Card c : cards) {
            p1.giveCard(c);
        }

        p1.setFirstStrategy(new ZeroPuntiStrategy(new RandomStrategy()));

        Briscola b = mock(Briscola.class);
        when(b.getBriscola()).thenReturn(null);
        when(b.otherPlayer(p1)).thenReturn(null);

        assertThat(p1.chooseFirstCard(b)).isEqualTo(cards.get(1));
    }

    @Test
    void noBriscolaAvversarioStrategyTest() {
        List<Card> cards1 = List.of(
                Card.get(Rank.RE, Suit.BASTONI),
                Card.get(Rank.ASSO, Suit.DENARI),
                Card.get(Rank.DUE, Suit.BASTONI)
        );

        for (Card c : cards1) {
            p1.giveCard(c);
        }

        List<Card> cards2 = List.of(
                Card.get(Rank.ASSO, Suit.SPADE),
                Card.get(Rank.DUE, Suit.SPADE),
                Card.get(Rank.TRE, Suit.DENARI)
        );

        for (Card c : cards2) {
            p2.giveCard(c);
        }

        p1.setFirstStrategy(new NoBriscolaAvversarioStrategy(new RandomStrategy()));

        Briscola b = mock(Briscola.class);
        when(b.getBriscola()).thenReturn(Suit.COPPE);
        when(b.otherPlayer(p1)).thenReturn(p2);

        assertThat(p1.chooseFirstCard(b)).isEqualTo(cards1.get(0));
    }

    @Test
    void strozzaStrategyDiversoPunteggioTest() {
        List<Card> cards1 = List.of(
                Card.get(Rank.DUE, Suit.BASTONI),
                Card.get(Rank.RE, Suit.BASTONI),
                Card.get(Rank.ASSO, Suit.DENARI)
        );

        for (Card c : cards1) {
            p1.giveCard(c);
        }

        List<Card> cards2 = List.of(
                Card.get(Rank.ASSO, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.SPADE),
                Card.get(Rank.TRE, Suit.DENARI)
        );

        for (Card c : cards2) {
            p2.giveCard(c);
        }

        p1.setFirstStrategy(new RandomStrategy());
        p2.setSecondStrategy(new StrozzaStrategy(new RandomStrategy()));

        Briscola b = mock(Briscola.class);
        when(b.getBriscola()).thenReturn(Suit.COPPE);
        when(b.otherPlayer(p1)).thenReturn(p2);
        when(b.otherPlayer(p2)).thenReturn(p1);

        p1.chooseFirstCard(b);
        assertThat(p2.chooseSecondCard(b)).isEqualTo(cards2.get(0));
    }

    @Test
    void strozzaStrategyStessoPunteggioTest() {
        List<Card> cards1 = List.of(
                Card.get(Rank.DUE, Suit.BASTONI),
                Card.get(Rank.RE, Suit.BASTONI),
                Card.get(Rank.ASSO, Suit.DENARI)
        );

        for (Card c : cards1) {
            p1.giveCard(c);
        }

        List<Card> cards2 = List.of(
                Card.get(Rank.QUATTRO, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.SPADE),
                Card.get(Rank.TRE, Suit.DENARI)
        );

        for (Card c : cards2) {
            p2.giveCard(c);
        }

        p1.setFirstStrategy(new RandomStrategy());
        p2.setSecondStrategy(new StrozzaStrategy(new RandomStrategy()));

        Briscola b = mock(Briscola.class);
        when(b.getBriscola()).thenReturn(Suit.COPPE);
        when(b.otherPlayer(p1)).thenReturn(p2);
        when(b.otherPlayer(p2)).thenReturn(p1);

        p1.chooseFirstCard(b);
        assertThat(p2.chooseSecondCard(b)).isEqualTo(cards2.get(0));
    }

    @Test
    void briscolaSeCaricoStrategyTest() {
        List<Card> cards1 = List.of(
                Card.get(Rank.ASSO, Suit.DENARI),
                Card.get(Rank.DUE, Suit.BASTONI),
                Card.get(Rank.RE, Suit.BASTONI)
        );

        for (Card c : cards1) {
            p1.giveCard(c);
        }

        List<Card> cards2 = List.of(
                Card.get(Rank.DUE, Suit.COPPE),
                Card.get(Rank.DUE, Suit.SPADE),
                Card.get(Rank.TRE, Suit.DENARI)
        );

        for (Card c : cards2) {
            p2.giveCard(c);
        }

        p1.setFirstStrategy(new RandomStrategy());
        p2.setSecondStrategy(new BriscolaSeCaricoStrategy(new RandomStrategy()));

        Briscola b = mock(Briscola.class);
        when(b.getBriscola()).thenReturn(Suit.COPPE);
        when(b.otherPlayer(p1)).thenReturn(p2);
        when(b.otherPlayer(p2)).thenReturn(p1);

        p1.chooseFirstCard(b);
        assertThat(p2.chooseSecondCard(b)).isEqualTo(cards2.get(0));
    }

    @Test
    void dueBriscoleTest() {
        List<Card> cards = List.of(
                Card.get(Rank.ASSO, Suit.COPPE),
                Card.get(Rank.TRE, Suit.DENARI),
                Card.get(Rank.RE, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.SPADE),
                Card.get(Rank.DUE, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.COPPE),
                Card.get(Rank.RE, Suit.COPPE)
        );

        p1.setFirstStrategy(new RandomStrategy());
        p2.setSecondStrategy(new RandomStrategy());

        Deck d = Deck.createEmptyDeck();
        for (Card c : cards)
            d.push(c);

        Briscola b = new Briscola(p1, p2, d);

        Card c1 = p1.chooseFirstCard(b);
        Card c2 = p2.chooseSecondCard(b);

        Player winner = b.establishTurnWinner(c1,c2);
        assertThat(winner.equals(p1)).isTrue();
    }

    @Test
    void noBriscoleStessoSuitTest() {
        List<Card> cards = List.of(
                Card.get(Rank.ASSO, Suit.DENARI),
                Card.get(Rank.TRE, Suit.DENARI),
                Card.get(Rank.RE, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.SPADE),
                Card.get(Rank.DUE, Suit.BASTONI),
                Card.get(Rank.FANTE, Suit.SPADE),
                Card.get(Rank.RE, Suit.COPPE)
        );

        p1.setFirstStrategy(new RandomStrategy());
        p2.setSecondStrategy(new RandomStrategy());

        Deck d = Deck.createEmptyDeck();
        for (Card c : cards)
            d.push(c);

        Briscola b = new Briscola(p1, p2, d);

        Card c1 = p1.chooseFirstCard(b);
        Card c2 = p2.chooseSecondCard(b);

        Player winner = b.establishTurnWinner(c1,c2);
        assertThat(winner.equals(p1)).isTrue();
    }

    @Test
    void unaBriscolaTest() {
        List<Card> cards = List.of(
                Card.get(Rank.ASSO, Suit.DENARI),
                Card.get(Rank.TRE, Suit.DENARI),
                Card.get(Rank.RE, Suit.BASTONI),
                Card.get(Rank.DUE, Suit.SPADE),
                Card.get(Rank.DUE, Suit.BASTONI),
                Card.get(Rank.FANTE, Suit.SPADE),
                Card.get(Rank.RE, Suit.DENARI)
        );

        p1.setFirstStrategy(new RandomStrategy());
        p2.setSecondStrategy(new RandomStrategy());

        Deck d = Deck.createEmptyDeck();
        for (Card c : cards)
            d.push(c);

        Briscola b = new Briscola(p1, p2, d);

        Card c1 = p1.chooseFirstCard(b);
        Card c2 = p2.chooseSecondCard(b);

        Player winner = b.establishTurnWinner(c1,c2);
        assertThat(winner.equals(p1)).isTrue();
    }
}
