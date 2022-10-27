package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.CardStack;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Scanner;

public class PokerHand implements Iterable<Card>{

    private CardStack cs;

    public PokerHand(int nCards, Deck d){
        cs = new CardStack();
        for (int n = 0; n < nCards; n++){
            if (!d.isEmpty())
                cs.push(d.draw());
            else throw new IllegalStateException("Empty Deck");
        }
    }

    public PokerHand(String cards){
        cs = new CardStack();
        Scanner sc = new Scanner(cards);
        while (sc.hasNext()){
            cs.push(Card.get(sc.next()));
        }
    }

    public HandRank getRank(){

        PokerEvaluator p = new PokerEvaluator();
        FlushHand f = new FlushHand();
        HighCard c = new HighCard();
        p.next=f;
        f.next = c;

        return p.handEvaluator(this.iterator());
    }


    @Override
    public java.lang.String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator t = this.iterator();
        while(t.hasNext()){
            sb.append(t.next().toString());
        }
        return sb.toString();
    }

    @NotNull
    @Override
    public Iterator<Card> iterator() {
        return cs.iterator();
    }
}
