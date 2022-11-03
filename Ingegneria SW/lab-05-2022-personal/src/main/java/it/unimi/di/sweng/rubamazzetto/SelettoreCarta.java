package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.ArrayList;
import java.util.List;

public interface SelettoreCarta {
    Card chooseCard(Partita partita, List<Card> mano, Giocatore giocatore);
}
