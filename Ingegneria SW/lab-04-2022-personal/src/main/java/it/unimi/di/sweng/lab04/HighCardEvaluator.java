package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HighCardEvaluator implements ChainedHandEvaluator{

    public HighCardEvaluator () {}
    @Override
    public HandRank handEvaluator(PokerHand ph) {
        return HandRank.HIGH_CARD;
    }
}
