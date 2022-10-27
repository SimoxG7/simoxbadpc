package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Suit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FlushEvaluator implements ChainedHandEvaluator{

    ChainedHandEvaluator next;
    private Map<Suit, Integer> map;

    public FlushEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand ph) {
        Iterator<Card> it = ph.iterator();
        map = new HashMap<>();
        while (it.hasNext()) {
            Suit s = it.next().getSuit();
            if (map.containsKey(s)) map.put(s, map.get(s) +1);
            else map.put(s, 1);
        }
        if (map.containsValue(5)) return HandRank.FLUSH;
        return next.handEvaluator(ph);
    }
}

