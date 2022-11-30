package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

// Strategia:
//     Gioco la prima carta che vale almeno 2 punti se:
//         - l'avversario non ha briscole
//         - ho almeno una carta con almeno 2 punti
public class NoBriscolaAvversarioStrategy implements Strategy {
    private final Strategy next;

    public NoBriscolaAvversarioStrategy(@NotNull Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Player me, @NotNull Player other, @NotNull Suit briscola) {
        for (Iterator<Card> it = other.iterator(); it.hasNext(); ) {
            if (it.next().getSuit() == briscola)
                return next.chooseCard(me, other, briscola);
        }

        for (Iterator<Card> it = me.iterator(); it.hasNext(); ) {
            Card c = it.next();
            if (c.getRank().points() > 0)
                return c;
        }

        return next.chooseCard(me, other, briscola);
    }
}
