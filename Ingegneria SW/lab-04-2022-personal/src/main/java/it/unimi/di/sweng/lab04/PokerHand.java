package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

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
            String str = sc.next();
            Rank r;
            Suit s;
            if (str.length() == 2) {
                r = stringToRank(str.charAt(0) + "");
                s = stringToSuit(str.charAt(1) + "");
            } else {
                r = stringToRank(str.charAt(0) + "" + str.charAt(1));
                s = stringToSuit(str.charAt(2) + "");
            }
            Card card = Card.get(r, s);
            cs.push(card);
        }
    }

    private Suit stringToSuit(String c) {
        Map<String, Suit> vars = new HashMap<>();
        Suit[] suits = Suit.values();

        vars.put("C", suits[0]);
        vars.put("D", suits[1]);
        vars.put("H", suits[2]);
        vars.put("S", suits[3]);

        if (vars.containsKey(c)) return vars.get(c);
        else throw new IllegalArgumentException("Invalid Suit arg '" + c + "'.");
    }

    private Rank stringToRank(String c) {
        Map<String, Rank> vars = new HashMap<>();
        Rank[] ranks = Rank.values();

        for (int i = 0; i < 10; i++) {
            vars.put("" + (i+1), ranks[i]);
        }
        vars.put("J", ranks[10]);
        vars.put("Q", ranks[11]);
        vars.put("K", ranks[12]);

        if (vars.containsKey(c)) return vars.get(c);
        else throw new IllegalArgumentException("Invalid Rank arg '" + c + "'.");
    }

    public HandRank getRank() {
        return new StraigthFlushEvaluator(new PokerEvaluator(new FlushEvaluator(new HighCardEvaluator()))).handEvaluator(this);

    }

    @Override
    public java.lang.String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator t = this.iterator();
        while(t.hasNext()){
            sb.append(t.next().toString()).append(", ");
        }
        return sb.toString();
    }

    @NotNull
    @Override
    public Iterator<Card> iterator() {
        return cs.iterator();
    }

}
