package it.unimi.di.sweng.lab02;

/**
 * This class represents a BowlingGame
 */
public class BowlingGame implements Bowling {

    private int[] scores;
    private int index;
    //private int[] extra;

    public BowlingGame() {
        this.scores = new int[26];
        this.index = 0;
    }

    @Override
    public void roll(int pins) {
        if (pins < 0 || pins > 10) throw new IllegalArgumentException("Invalid pins number.");

        this.scores[index] = pins;

        if (pins == 10 && index%2 == 0) index++;
        index++;

        /*
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + ",");
        }
        System.out.println();
        */
    }

    @Override
    public int score() {
        int res = 0;
        for (int i = 0; i < 22; i++) {
            res += scores[i];
        }
        for (int i = 0; i < 20; i++) {
            if (i%2 == 1 && scores[i-1] != 10) {
                if (scores[i] + scores[i-1] == 10 ) res += scores[i+1];
            } else if (i%2 == 0 && scores[i] == 10) {
                if (scores[i+2] == 10 && i < 18) {
                    res += scores[i+2] + scores[i+4];
                } else {
                    res += scores[i+2] + scores[i+3];
                }
            }
        }
        return res;
    }
}
/*
    private int score;
    private int rollCount;
    private int lastRoll;
    private int bonus;

    public BowlingGame() {
        this.score = 0;
        this.rollCount = 0;
        this.lastRoll = -1;
        this.bonus = 0;
    }

    @Override
    public void roll(int pins) {
        //fuori tiri
        if (rollCount >= 20) {
            if(bonus == 0) throw new IllegalStateException("the game is over. Unnecessary roll");
            else if (rollCount > 22) throw new IllegalStateException("the game is over. Unnecessary roll");
        }

        //calcola punteggio
        if (bonus == 0) this.score += pins;
        else { //spare
            this.score += (pins * 2);
            this.bonus--;
        }

        //verifica strike o spare
        if (rollCount % 2 != 0) {
            if (pins + lastRoll == 10) bonus = 1;
        } else if (pins == 10) {
            bonus = 2;
            rollCount++;
        }

        this.rollCount++;
        this.lastRoll = pins;

        //System.out.println(score + " " + rollCount + " " + lastRoll);
    }

    @Override
    public int score() {
        return score;
    }
}

   /* @Override
    public void roll(int pins) {
        //fuori tiri
        if (rollCount >= 20) {
            if(strike == false && spare == false) throw new IllegalStateException("the game is over. Unnecessary roll");
            else if (rollCount > 22) throw new IllegalStateException("the game is over. Unnecessary roll");
        }

        //calcola punteggio
        if (spare == false && strike == false) this.score += pins; //common
        else if (spare == true) { //spare
            this.score += (pins * 2);
            this.spare = false;
        } else { //strike
             if ((rollCount % 2 == 1) || pins == 10) {
                if (lastRoll == 10) {
                    this.score += 2 * lastRoll;
                    this.score += 10;
                } else {
                    this.score += 2 * (pins + lastRoll);
                    strike = false;
                }
            }
        }
        //verifica strike o spare
        if (rollCount % 2 != 0) {
            if (pins + lastRoll == 10) spare = true;
        } else if (pins == 10) {
            strike = true;
            rollCount++;
        }

        this.rollCount++;
        this.lastRoll = pins;

        System.out.println(score + " " + rollCount + " " + lastRoll);
    }

    @Override
    public int score() {
        return score;
    }
}
*/