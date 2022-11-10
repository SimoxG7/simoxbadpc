package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public interface GiocatoreBJ {

  void carteIniziali();

  void gioca();

  Iterator<Card> getCards();

  String getName();

  default int getPunti() {
    // TODO quanto valgono le carte? Occhio agli assi!
    int tot = 0;
    Iterator<Card> iterator = this.getCards();
    while (iterator.hasNext()) {
      Card card = iterator.next();
      switch (card.getRank()) {
        case ACE:
          if (tot > 10) {
            tot++;
            break;
          } else tot +=11;
        case TEN, JACK, QUEEN, KING:
          tot+=10;
          break;
        default:
          List<Rank> values = Arrays.asList(Rank.values());
          int index = values.indexOf(card.getRank());
          tot += index +1;
      }
    }
    return tot;
  }

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
