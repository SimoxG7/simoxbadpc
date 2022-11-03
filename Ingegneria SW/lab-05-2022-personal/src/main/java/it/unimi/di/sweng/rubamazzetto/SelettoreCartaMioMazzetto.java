package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;

public class SelettoreCartaMioMazzetto implements SelettoreCarta {

    private final SelettoreCarta next;

    public SelettoreCartaMioMazzetto(SelettoreCarta next) { this.next = next; }


    @Override
    public Card chooseCard(Partita partita, List<Card> mano, Giocatore giocatore) {
        Tavolo tavolo = partita.getTavolo();
        for (Card card : mano) {
            if (!tavolo.inMostra(card) && (giocatore.getMazzettoTop() == card.getRank())) {
                return card;
            }
        }
        return next.chooseCard(partita, mano, giocatore);
    }
}
