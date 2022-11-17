package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

public class Briscola {
  private final Deck deck;
  private final Card briscola;

  private boolean availableBriscolaCard = true;
  private final Player[] players = new Player[2];

  @NotNull
  private Player firstCardPlayer;

  public Briscola(@NotNull Player p1, @NotNull Player p2, @NotNull Deck d) {
    deck = d;

    players[0] = p1;
    players[1] = p2;
    firstCardPlayer = p1;

    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 2; j++)
        players[j].giveCard(deck.draw());

    briscola = deck.draw();
  }

  public void playTurn() {

    System.out.println("\n\n---\nricordo che briscola è " + getBriscola());

    System.out.println(firstCardPlayer);
    Card firstCard = firstCardPlayer.chooseFirstCard(this);
    System.out.println("ha scelto: " + firstCard);

    System.out.println(otherPlayer(firstCardPlayer));
    Card secondCard = otherPlayer(firstCardPlayer).chooseSecondCard(this);
    System.out.println("ha scelto: " + secondCard);

    firstCardPlayer = establishTurnWinner(firstCard, secondCard);
    System.out.println("vince questa mano: " + firstCardPlayer.getName());
    firstCardPlayer.addWonCardsToPersonalDeck(firstCard, secondCard);
  }


  // Sistemare ramo condizionale
  @NotNull
  Player establishTurnWinner(@NotNull Card first, @NotNull Card second) {
    int numBriscole = numeroBriscole(first, second);
    if (numBriscole == 2) {
      return (compareOrdinal(first, second) >= 0) ? players[0] : players[1];
    } else if (numBriscole == 0) {
      if (first.getSuit() == second.getSuit()) {
        return (compareOrdinal(first, second) >= 0) ? players[0] : players[1];
      } else {
        return players[0];
      }
    } else {
      if (first.getSuit() == briscola.getSuit()) {
        return players[0];
      } else {
        return players[1];
      }
    }
  }

  private int numeroBriscole(Card c1, Card c2) {
    int counter = 0;
    if (c1.getSuit() == briscola.getSuit()) counter++;
    if (c2.getSuit() == briscola.getSuit()) counter++;

    return counter;
  }

  private int compareOrdinal(Card c1, Card c2) {
    return c1.getRank().ordinal() - c2.getRank().ordinal();
  }

  public void giveEachPlayerOneCard() {
    firstCardPlayer.giveCard(draw());
    otherPlayer(firstCardPlayer).giveCard(draw());
  }

  @NotNull
  public Suit getBriscola() {
    return briscola.getSuit();
  }


  public boolean isBriscola(@NotNull Card c) {
    return c.getSuit() == getBriscola();
  }

  @NotNull
  private Card draw() {
    assert availableBriscolaCard;
    if (deck.isEmpty()) {
      availableBriscolaCard = false;
      return briscola;
    }
    return deck.draw();
  }

  public boolean availableCards() {
    return availableBriscolaCard;
  }

  @NotNull
  public Player establishGameWinner() {
    // TODO identifica (e restituisce) il vincitore della partita (o un NULL OBJECT adatto in caso di pareggio)
    return null;
  }

  @NotNull
  public Player otherPlayer(@NotNull Player player) {
    if (players[0] == player)
      return players[1];
    return players[0];
  }

}
