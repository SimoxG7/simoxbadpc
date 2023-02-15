package it.unimi.di.sweng.esame.model;

import it.unimi.di.sweng.esame.Main;

import java.io.InputStream;
import java.util.Scanner;

public class Model {

  public void readFile() {
    InputStream is = Main.class.getResourceAsStream("/trains.csv");
    assert is != null;
    Scanner s = new Scanner(is);

    while (s.hasNextLine()) {
      String linea = s.nextLine();
      String[] el = linea.split(",");
      String cod = el[0];
      String destination = el[1];
      String depTime = el[2];
      String delay = el[3];

      //TODO sostituire la stampa con la memorizzazione all'interno del modello con una opportuna struttura dati
      System.out.printf("cod: [%s] dest: [%s] time: [%s] delay: [%s]\n", cod, destination, depTime, delay);
    }

  }
}
