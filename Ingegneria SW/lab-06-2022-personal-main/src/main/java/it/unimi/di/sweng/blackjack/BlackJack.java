package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class BlackJack {
  public static int cardValue(Card c) {
    return Math.min(c.getRank().ordinal() + 1, 10);
  }

}
