package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

// Strategia:
//     Gioco una briscola se e solo se:
//         - la carta giocata è un carico
//         - la carta giocata non è una briscola
public class BriscolaSeCaricoStrategy implements Strategy {
    private final Strategy next;

    public BriscolaSeCaricoStrategy(@NotNull Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Player me, @NotNull Player other, @NotNull Suit briscola) {
        Card c = other.playedCard();

        if (c.getSuit() == briscola || c.getRank().points() < 10)
            return next.chooseCard(me, other, briscola);

        for (Iterator<Card> it = me.iterator(); it.hasNext(); ) {
            Card mia = it.next();
            if (mia.getSuit() == briscola)
                return mia;
        }

        return next.chooseCard(me, other, briscola);
    }
}
