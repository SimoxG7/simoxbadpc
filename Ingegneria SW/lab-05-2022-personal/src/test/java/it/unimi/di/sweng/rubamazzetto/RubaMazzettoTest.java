package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Iterator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Timeout(2)
public class RubaMazzettoTest {

    @Test
    public void testTavoloIterable() {
        assertThat(Iterable.class.isAssignableFrom(Tavolo.class)).isEqualTo(true);
    }

    @Test
    public void testTavoloCarte(){
        Tavolo tav = new Tavolo();

        tav.metti(Card.get(Rank.ACE, Suit.HEARTS));
        tav.metti(Card.get(Rank.TWO, Suit.SPADES));
        tav.metti(Card.get(Rank.THREE, Suit.DIAMONDS));
        tav.metti(Card.get(Rank.FOUR, Suit.CLUBS));

        StringBuilder s = new StringBuilder();

        for (Card c : tav ) {
            s.append(c.toString());
            s.append(" ");
        }

        assertThat(s.toString()).isEqualTo("ACE of HEARTS TWO of SPADES THREE of DIAMONDS FOUR of CLUBS ");

    }

    @Test
    public void testTurno(){
        Partita partita = new Partita();

        Tavolo tav = partita.getTavolo();

        ArrayList<Card> carte_tav = new ArrayList<>();

        for (Card card : tav) {
            carte_tav.add(card);
        }

        for (Card card : carte_tav) {
            tav.prendi(card);
        }

        tav.metti(Card.get(Rank.ACE, Suit.HEARTS));
        tav.metti(Card.get(Rank.TWO, Suit.SPADES));
        tav.metti(Card.get(Rank.THREE, Suit.DIAMONDS));
        tav.metti(Card.get(Rank.FOUR, Suit.CLUBS));

        Giocatore lor = new Giocatore("Lorenzo",partita);
        Giocatore sim = new Giocatore("Simone",partita);

        lor.daiCarta(Card.get(Rank.ACE, Suit.SPADES));
        sim.daiCarta(Card.get(Rank.FIVE, Suit.DIAMONDS));

        for (Giocatore g: partita) {
            g.turno(partita, new SelettoreCartaBanale(null));
        }

        assertThat(tav.toString()).isEqualTo("4 -> TWO of SPADES, THREE of DIAMONDS, FOUR of CLUBS, FIVE of DIAMONDS, ");
        assertThat(lor.getMazzettoTop()).isEqualTo(Rank.ACE);
        assertThat(lor.getPunti()).isEqualTo(2);
        assertThat(sim.getMazzettoTop()).isEqualTo(null);
        assertThat(sim.getPunti()).isEqualTo(0);
    }

    @Test
    public void testChainBanale() {
        Partita partita = new Partita();

        Tavolo tav = partita.getTavolo();

        ArrayList<Card> carte_tav = new ArrayList<>();

        for (Card card : tav) {
            carte_tav.add(card);
        }

        for (Card card : carte_tav) {
            tav.prendi(card);
        }

        tav.metti(Card.get(Rank.ACE, Suit.HEARTS));
        tav.metti(Card.get(Rank.TWO, Suit.SPADES));
        tav.metti(Card.get(Rank.THREE, Suit.DIAMONDS));
        tav.metti(Card.get(Rank.FOUR, Suit.CLUBS));

        Giocatore lor = new Giocatore("Lorenzo",partita);
        Giocatore sim = new Giocatore("Simone",partita);

        lor.daiCarta(Card.get(Rank.ACE, Suit.SPADES));
        sim.daiCarta(Card.get(Rank.FIVE, Suit.DIAMONDS));

        for (Giocatore g: partita) {
            g.turno(partita, new SelettoreCartaBanale(null));
        }

        assertThat(tav.toString()).isEqualTo("4 -> TWO of SPADES, THREE of DIAMONDS, FOUR of CLUBS, FIVE of DIAMONDS, ");
        assertThat(lor.getMazzettoTop()).isEqualTo(Rank.ACE);
        assertThat(lor.getPunti()).isEqualTo(2);
        assertThat(sim.getMazzettoTop()).isEqualTo(null);
        assertThat(sim.getPunti()).isEqualTo(0);
    }

    @Test
    public void testChainTavolo() {
        Partita partita = new Partita();

        Tavolo tav = partita.getTavolo();

        ArrayList<Card> carte_tav = new ArrayList<>();

        for (Card card : tav) {
            carte_tav.add(card);
        }

        for (Card card : carte_tav) {
            tav.prendi(card);
        }

        tav.metti(Card.get(Rank.ACE, Suit.HEARTS));
        tav.metti(Card.get(Rank.TWO, Suit.SPADES));
        tav.metti(Card.get(Rank.THREE, Suit.DIAMONDS));
        tav.metti(Card.get(Rank.FOUR, Suit.CLUBS));

        Giocatore lor = new Giocatore("Lorenzo",partita);

        lor.daiCarta(Card.get(Rank.ACE, Suit.SPADES));

        for (Giocatore g: partita) {
            g.turno(partita, new SelettoreCartaTavolo(null));
        }

        assertThat(tav.toString()).isEqualTo("4 -> TWO of SPADES, THREE of DIAMONDS, FOUR of CLUBS, ");
        assertThat(lor.getMazzettoTop()).isEqualTo(Rank.ACE);
        assertThat(lor.getPunti()).isEqualTo(2);
    }

}
