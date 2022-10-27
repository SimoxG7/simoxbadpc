package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.Iterator;

public interface ChainedHandEvaluator {


    HandRank handEvaluator(Iterator<Card> it);



}
