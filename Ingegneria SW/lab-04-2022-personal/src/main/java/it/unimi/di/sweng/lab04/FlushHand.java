package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FlushHand implements ChainedHandEvaluator{

    ChainedHandEvaluator next;
    private Map<Suit, Integer> map;

    @Override
    public HandRank handEvaluator(Iterator<Card> it) {
        map = new HashMap<>();
        while (it.hasNext()) {
            Suit s = it.next().getSuit();
            if (map.containsKey(s)) map.put(s, map.get(s) +1);
            else map.put(s, 1);
            System.out.println(s);
        }
        System.out.println(map);
        if (map.containsValue(5)) return HandRank.FLUSH;
        return next.handEvaluator(it);
    }
}

