package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;

public class SelettoreCartaMazzetto implements SelettoreCarta {

    private final SelettoreCarta next;
    public SelettoreCartaMazzetto(SelettoreCarta next) {
        this.next = next;
    }


    @Override
    public Card chooseCard(Partita partita, List<Card> mano, Giocatore giocatore) {
        return null;
    }
}
