package it.unimi.di.sweng.briscola;

import org.jetbrains.annotations.NotNull;

// NullObject pattern
// Gioca sempre la carta in posizione 0
public class RandomStrategy implements Strategy {
    @Override
    public @NotNull Card chooseCard(@NotNull Player me, @NotNull Player other, @NotNull Suit briscola) {
        return me.iterator().next();
    }
}
