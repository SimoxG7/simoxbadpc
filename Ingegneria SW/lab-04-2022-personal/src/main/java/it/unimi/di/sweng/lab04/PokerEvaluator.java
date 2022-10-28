package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PokerEvaluator implements ChainedHandEvaluator{

    ChainedHandEvaluator next;

    public PokerEvaluator (ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand ph) {
        Iterator<Card> it = ph.iterator();
        Map<Rank, Integer> map = new HashMap<>();
        while (it.hasNext()) {
            Rank r = it.next().getRank();
            if (map.containsKey(r)) map.put(r, map.get(r) +1);
            else map.put(r, 1);
        }
        if (map.containsValue(4)) return HandRank.FOUR_OF_A_KIND;
        else {
            return this.next.handEvaluator(ph);
        }
    }
}
