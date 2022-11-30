package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

// Strategia
//     Strozziamo la carta in banco se e solo se:
//         - la carta in banco non è una briscola
//         - la carta in banco vale meno punti di una carta che si ha in mano
//           oppure valgono entrambe 0 ma ho rank maggiore
public class StrozzaStrategy implements Strategy {
    private final Strategy next;

    public StrozzaStrategy(@NotNull Strategy next) {
        this.next = next;
    }

    @Override
    public @NotNull Card chooseCard(@NotNull Player me, @NotNull Player other, @NotNull Suit briscola) {
        Card c = other.playedCard();

        if (c.getSuit() == briscola)
            return next.chooseCard(me, other, briscola);

        for (Iterator<Card> it = me.iterator(); it.hasNext(); ) {
            Card mia = it.next();
            if (mia.getSuit() == c.getSuit()) {
                int point = mia.getRank().points() - c.getRank().points();
                if (point > 0 || (point == 0 && (mia.getRank().ordinal() > c.getRank().ordinal()))) {
                    return mia;
                }
            }
        }

        return next.chooseCard(me, other, briscola);
    }
}
