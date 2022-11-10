package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.Iterator;

public interface GiocatoreBJ {
  void carteIniziali();

  void gioca();

  Iterator<Card> getCards();

  String getName();

  default int getPunti() {
    // TODO quanto valgono le carte? Occhio agli assi!
    return 0;
  };

  default boolean isSballato() {
    return getPunti() > 21;
  }

  default String asString() {
    final StringBuilder sb = new StringBuilder(getName());
    sb.append(": ").append("[").append(getPunti()).append("] ");
    for (Iterator<Card> cards = getCards(); cards.hasNext(); ) {
      Card card = cards.next();
      sb.append(card).append(" ");
    }
    if (isSballato()) {
      sb.append("SBALLATO");
    }
    return sb.toString();
  }
}
