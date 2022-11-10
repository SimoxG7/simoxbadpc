package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Mazziere implements GiocatoreBJ {

    private final List<Card> mano;
    private final MultiMazzo multiMazzo;


    public Mazziere(int numPlayers) {
        mano = new ArrayList<>();
        multiMazzo = new MultiMazzo(numPlayers+1);
    }

    @Override
    public void carteIniziali() {
        mano.add(multiMazzo.draw());
        mano.add(multiMazzo.draw());
    }

    @Override
    public void gioca() {
        //TODO
    }

    @Override
    public Iterator<Card> getCards() {
        return mano.iterator();
    }

    @Override
    public String getName() {
        return "Mazziere";
    }

    @Override
    public int getPunti() {
        return GiocatoreBJ.super.getPunti();
    }

    @Override
    public boolean isSballato() {
        return this.getPunti() > 21;
    }

    @Override
    public String asString() {
        return GiocatoreBJ.super.asString();
    }

}
