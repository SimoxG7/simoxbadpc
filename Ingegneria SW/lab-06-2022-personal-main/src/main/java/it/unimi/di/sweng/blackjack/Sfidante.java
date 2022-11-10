package it.unimi.di.sweng.blackjack;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Sfidante implements GiocatoreBJ {
  final private String name;
  final private Mazziere banco;
  final private List<Card> mano = new ArrayList<>();
  private Strategia strategia;


  public Sfidante(String name, Mazziere banco) {
    this.name = name;
    this.banco = banco;
  }

  public void setStrategia(Strategia strategia) {
    assert this.strategia == null : "non puoi cambiare strategia se è già settata";
    this.strategia = strategia;
  }

  //TODO i vari metodi richiesti per aderire all'interfaccia GiocatoreBJ

  @Override
  public void carteIniziali() {

  }

  @Override
  public void gioca() {

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
