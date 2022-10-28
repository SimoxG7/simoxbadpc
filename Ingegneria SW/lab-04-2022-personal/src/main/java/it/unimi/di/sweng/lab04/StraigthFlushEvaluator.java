package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;

import java.util.*;

public class StraigthFlushEvaluator implements  ChainedHandEvaluator {

    private final ChainedHandEvaluator next;


    public StraigthFlushEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand ph) {
        Map<Suit, Integer> mapSuits = new HashMap<>();
        Iterator<Card> it = ph.iterator();
        ArrayList<Rank> values = new ArrayList<>(Arrays.asList(Rank.values()));
        ArrayList<Rank> rankvalues = new ArrayList<>();
        while (it.hasNext()) {
            Card c = it.next();
            Suit s = c.getSuit();
            Rank r = c.getRank();
            if (mapSuits.containsKey(s)) mapSuits.put(s, mapSuits.get(s)+1);
            else mapSuits.put(s, 1);
            rankvalues.add(r);
        }
        if (mapSuits.containsValue(5)) {
            Collections.sort(rankvalues);
            int previndex = values.indexOf(rankvalues.get(0));
            for (int i = 1; i < rankvalues.size(); i++) {
                Rank current = rankvalues.get(i);
                int index = values.indexOf(current);
                if (index != previndex+1) {
                    return next.handEvaluator(ph);
                }
                previndex = index;
            }
            return HandRank.STRAIGHT_FLUSH;
        }
        else return next.handEvaluator(ph);
    }
}
