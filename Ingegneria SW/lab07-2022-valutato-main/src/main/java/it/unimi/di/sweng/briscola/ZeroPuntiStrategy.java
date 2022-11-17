package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

// Strategia:
//     gioco la prima carta che vale 0 punti
public class ZeroPuntiStrategy implements Strategy {
    private final Strategy next;

    public ZeroPuntiStrategy(@NotNull Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Player me, @NotNull Player other, @NotNull Suit briscola) {
        for (Iterator<Card> it = me.iterator(); it.hasNext(); ) {
            Card c = it.next();
            if (c.getRank().points() == 0)
                return c;
        }

        return next.chooseCard(me, other, briscola);
    }
}
