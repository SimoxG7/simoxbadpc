package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.List;

public class SelettoreCartaBanale implements SelettoreCarta {

    private final SelettoreCarta next;
    private Partita partita;
    private List<Card> mano;

    public SelettoreCartaBanale(SelettoreCarta next) {
        this.next = next;
    }

    @Override
    public Card chooseCard(Partita partita, List<Card> mano, Giocatore giocatore) {
        if (mano.isEmpty()) throw new IllegalArgumentException("La mano non può essere vuota.");
        return mano.get(0);
    }
}
