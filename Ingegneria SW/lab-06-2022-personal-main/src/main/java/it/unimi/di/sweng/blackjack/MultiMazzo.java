package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;

import java.util.ArrayList;
import java.util.List;


public class MultiMazzo implements DeckInterface  {
  private List<Deck> multiMazzo;
  private final int numMazzi;

  public MultiMazzo(int numMazzi) {
    this.numMazzi = numMazzi;
    //definire un costruttore che crei un mazzo composto da numMazzi mescolato casualmente
    multiMazzo = new ArrayList<>();
    for (int i = 0; i < 6; i++) multiMazzo.add(new Deck());
  }


  @Override
  public Card draw() {
    return null;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }
}
