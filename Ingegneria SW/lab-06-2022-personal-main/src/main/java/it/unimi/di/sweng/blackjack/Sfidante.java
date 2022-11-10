package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sfidante implements GiocatoreBJ {
  final private String name;
  final private List<Card> mano = new ArrayList<>();
  private Strategia strategia;
  private Mazziere mazziere;

  public Sfidante(String name, Mazziere banco) {
    this.name = name;
    this.mazziere = banco;
  }

  public void setStrategia(Strategia strategia) {
    assert this.strategia == null : "non puoi cambiare strategia se è già settata";
    this.strategia = strategia;
  }

  @Override
  public void carteIniziali() {
    mano.add(mazziere.daiCarta());
    mano.add(mazziere.daiCarta());
  }

  @Override
  public void gioca() {
    if (this.getPunti() < 17) this.mano.add(this.mazziere.daiCarta());
    if (this.getPunti() < 17) gioca();
  }

  @Override
  public Iterator<Card> getCards() {
    return mano.iterator();
  }

  @Override
  public String getName() {
    return name;
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
