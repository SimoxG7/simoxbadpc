package it.unimi.di.sweng.esame.model;


import it.unimi.di.sweng.esame.Main;
import it.unimi.di.sweng.esame.presenters.Observer;
import org.jetbrains.annotations.NotNull;


import java.io.InputStream;

import java.util.*;

public class Model implements Observable<List<NazionePunteggio>> {

  @NotNull final Map<String, NazionePunteggio> nazioniPunteggio = new HashMap<>();
  @NotNull private final List<Observer<List<NazionePunteggio>>> observers = new ArrayList<>();

  public void readFile() {
    InputStream is = Main.class.getResourceAsStream("/FinalistNations");
    assert is != null;
    Scanner s = new Scanner(is);

    while (s.hasNextLine()) {
      String linea = s.nextLine();
      String[] el = linea.split(";");
      String name = el[0];
      String cod = el[1];

      //System.out.printf("cod: [%s] name: [%s]\n", cod, name);
      nazioniPunteggio.put(cod, new NazionePunteggio(cod, name, 0));
    }
  }

  @NotNull
  public List<NazionePunteggio> getState() {
    return new ArrayList<>(nazioniPunteggio.values());
  }

  @Override
  public void notifyObservers() {
    for (Observer<List<NazionePunteggio>> observer : observers) {
      observer.update(getState());
    }
  }

  @Override
  public void addObserver(@NotNull Observer<List<NazionePunteggio>> observer) {
    observers.add(observer);
  }

  public void changeVotes(String cod, String fullname, int votes) {
    //aggiungo solo se è già presente la chiave nella stringa, altrimenti la stringa non è del keyset
    if (nazioniPunteggio.containsKey(cod)) {
      if (nazioniPunteggio.get(cod).points() != votes) {
        nazioniPunteggio.put(cod, new NazionePunteggio(cod, fullname, votes));
        notifyObservers();
      }
    }
  }
}
