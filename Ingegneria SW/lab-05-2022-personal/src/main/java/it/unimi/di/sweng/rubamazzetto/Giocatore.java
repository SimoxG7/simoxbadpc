package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.*;

public class Giocatore {

  private final String nome;
  private List<Card> mano = new ArrayList<>();
  private Rank mazzettoTop;

  private int punti;

  public Giocatore(String nome, Partita partita) {
    this.nome = nome;
    partita.addGiocatore(this);
  }

  public Rank getMazzettoTop() {
    return mazzettoTop;
  }

  public int getPunti() {
    return punti;
  }
  void azzera() {
    punti = 0;
    mazzettoTop = null;
  }

  public void daiCarta(Card carta) {
    mano.add(carta);
  }


  public void turno(Partita partita, SelettoreCarta selettore) {
    Card c = selettore.chooseCard(partita, List.copyOf(mano), this);

    Tavolo tav = partita.getTavolo();

    Map<Rank, Giocatore> rankToGiocatore = new HashMap<>();

    for (Giocatore giocatore : partita) {
      rankToGiocatore.put(giocatore.getMazzettoTop(), giocatore);
    }

    if (tav.inMostra(c)) {
      tav.prendi(c);
      mano.remove(c);
      punti += 2;
      mazzettoTop = c.getRank();
    } else if (rankToGiocatore.containsKey(c.getRank())) {
      Giocatore g = rankToGiocatore.get(c.getRank());
      int currpunti = g.getPunti();
      g.azzera();
      punti += currpunti + 1;
      mano.remove(c);
      mazzettoTop = c.getRank();
    } else {
      tav.metti(c);
      mano.remove(c);
    }
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder(nome);
    s.append(": ");
    s.append("[").append(mano.size()).append("]");
    if (punti > 0) {
      s.append("mazzetto con ");
      s.append(punti);
      s.append(" carte, cima ");
      s.append(mazzettoTop);
      s.append("; ");
    }
    for (Card card : mano) {
      s.append(card.toString());
      s.append(", ");
    }
    return s.toString();
  }

  public int numCards() {
    return mano.size();
  }
}
