package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.List;

public class SelettoreCartaMazzetto implements SelettoreCarta {

    private final SelettoreCarta next;
    public SelettoreCartaMazzetto(SelettoreCarta next) {
        this.next = next;
    }


    @Override
    public Card chooseCard(Partita partita, List<Card> mano, Giocatore giocatore) {
        for (Card card : mano) {
            Rank rank = card.getRank();
            for (Giocatore giocatoreDiverso : partita) {
                if (giocatoreDiverso == giocatore) continue;
                else if (giocatoreDiverso.getMazzettoTop() == rank) {
                    return card;
                }
            }
        }
        return next.chooseCard(partita, mano, giocatore);
    }
}
