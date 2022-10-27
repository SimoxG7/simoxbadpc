package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;

import java.lang.reflect.Array;
import java.util.*;

public class StraigthFlushEvaluator implements  ChainedHandEvaluator {

    private final ChainedHandEvaluator next;
    private Map<Suit, Integer> mapSuits;
    private Map<Rank, Integer> mapRanks;


    public StraigthFlushEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand ph) {
        this.mapSuits = new HashMap<>();
        this.mapRanks = new HashMap<>();
        Iterator<Card> it = ph.iterator();
        Rank[] values = Rank.values();
        while (it.hasNext()) {
            Card c = it.next();
            Suit s = c.getSuit();
            Rank r = c.getRank();
            if (mapSuits.containsKey(s)) mapSuits.put(s, mapSuits.get(s)+1);
            else mapSuits.put(s, 1);
            if (mapRanks.containsKey(r)) mapRanks.put(r, mapRanks.get(r)+1);
            else mapRanks.put(r, 1);
        }
        System.out.println(mapSuits);
        if (mapSuits.containsValue(5)) {
            int consec = 0;
            //TODO
            for (int i = 0; i < mapRanks.size()-1; i++) {
                if (mapRanks.get(values[i]) == 1 && mapRanks.get(values[i+1]) == 1) consec++;
            }
            return HandRank.STRAIGHT_FLUSH;
        }
        else return next.handEvaluator(ph);
    }
}
