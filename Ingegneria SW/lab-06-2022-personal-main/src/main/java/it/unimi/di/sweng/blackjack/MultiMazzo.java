package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;

import java.util.ArrayList;
import java.util.List;


public class MultiMazzo implements DeckInterface  {
  private List<Deck> multiMazzo;
  private final int numMazzi;
  private int nextToUse;

  public MultiMazzo(int numMazzi) {
    this.numMazzi = numMazzi;
    this.nextToUse = 0;
    //definire un costruttore che crei un mazzo composto da numMazzi mescolato casualmente
    multiMazzo = new ArrayList<>();
    for (int i = 0; i < numMazzi; i++) multiMazzo.add(new Deck());
  }

  @Override
  public Card draw() {
    if (this.isEmpty()) return null;
    if (nextToUse > multiMazzo.size()) nextToUse = 0;
    if (!multiMazzo.get(nextToUse).isEmpty()) return multiMazzo.get(nextToUse++).draw();
    else return this.draw();
  }

  @Override
  public boolean isEmpty() {
    for (Deck deck : multiMazzo) {
      if (!deck.isEmpty()) return false;
    }
    return true;
  }
}
