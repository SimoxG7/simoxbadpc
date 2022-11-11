import it.unimi.di.sweng.blackjack.Mazziere;
import it.unimi.di.sweng.blackjack.Sfidante;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Mazziere banco = new Mazziere();

        List<Sfidante> sfidanti = new ArrayList<>();
        Sfidante carlo = new Sfidante("Carlo", banco);
        sfidanti.add(carlo);
        Sfidante mattia = new Sfidante("Mattia", banco);
        sfidanti.add(mattia);
        Sfidante violetta = new Sfidante("Violetta", banco);
        sfidanti.add(violetta);

        for (Sfidante sfidante : sfidanti) {
            sfidante.carteIniziali();
        }
        banco.carteIniziali();

        for (Sfidante sfidante : sfidanti) {
            sfidante.gioca();
        }
        banco.gioca();

        System.out.println(banco);
        for (Sfidante sfidante : sfidanti) {
            System.out.println(sfidante);
            if (sfidante.isSballato() || (sfidante.getPunti() < banco.getPunti() && !banco.isSballato()))
                System.out.println("Vince il banco.");
            else if (banco.isSballato() || sfidante.getPunti() > banco.getPunti())
                System.out.println("Il banco perde!!!");
            else
                System.out.println("Pareggio.");
        }
    }
}