package it.unimi.di.sweng.blackjack;

//TODO implementare almeno tre strategie di vostra invenzione per comporre il modo di giocare dei giocatori
// potranno basarsi sulle proprie carte o sulla carta iniziale del banco

public interface Strategia {
  //TODO  implementare la strategia finale che sceglie di
  // "stare" sempre, come NullObject Pattern
  boolean chiediCarta();
}

