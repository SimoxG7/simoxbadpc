package it.unimi.di.sweng.blackjack;

import org.junit.jupiter.api.Test;
import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MultiMazzoTest {

    @Test
    public void testMultiMazzoNotNull() {

        assertThat(new MultiMazzo(6)).isNotEqualTo(null);
    }

}