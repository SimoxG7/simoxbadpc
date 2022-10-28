package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PokerTable {

    private final Map<Integer, PokerHand> hands;
    private final Deck d;

    public PokerTable(int players, Deck d) {
        //il numero di giocatori per non avere possibilità di finire il mazzo è di 5 (5 carte date, 4 possibili cambi -> 5(4+5) = 45, 6(4+5) ) 54 -> troppo!
        if (players > 5) throw new IllegalArgumentException("Too many players! Max number of players with one deck is 5.");
        d.shuffle();
        this.hands = new HashMap<>();
        for (int i = 0; i < players; i++) {
            hands.put(i, new PokerHand(5, d));
        }
        this.d = d;
    }

    public PokerHand getHand(int i) {
        if (i > hands.size()-1) throw new IllegalArgumentException("There are only " + hands.size() + " players (ranging from 0 to " + (hands.size()-1)+ ").");
        return hands.get(i);
    }

    public PokerHand change(int player, List<Card> toChange) {
        int numToChange = toChange.size();
        if (numToChange > 4) throw new IllegalArgumentException("Can't change more than 4 cards.");
        if (!toChange.equals(hands.get(player))) throw new IllegalArgumentException("Player hand in the PokerTable is different than the passed Hand.");
        PokerHand newPH = new PokerHand(numToChange, this.d);
        this.hands.put(player, newPH);
        return newPH;
    }

    public Iterator<Integer> playerIterator() {
        Iterator<Integer> it = new Iterator<Integer>() {

            //TODO
            private int current = 0;
            Map<Integer, HandRank> playerRanks = new HashMap<>();

            @Override
            public boolean hasNext() {
                return current++ < hands.size();
            }

            @Override
            public Integer next() {
                return 0;
            }
        };
        return it;
    }
}
