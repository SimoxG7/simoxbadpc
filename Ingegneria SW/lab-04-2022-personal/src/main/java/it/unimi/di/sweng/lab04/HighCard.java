package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HighCard implements ChainedHandEvaluator{

    ChainedHandEvaluator next;
    private Map<Rank, Integer> map;

    @Override
    public HandRank handEvaluator(Iterator<Card> it) {
        map = new HashMap<>();
        while (it.hasNext()) {
            Rank r = it.next().getRank();
            if (map.containsKey(r)) map.put(r, map.get(r) +1);
            else map.put(r, 1);
        }
        if (map.containsValue(5)) return HandRank.FOUR_OF_A_KIND;
        return next.handEvaluator(it);
    }
}
