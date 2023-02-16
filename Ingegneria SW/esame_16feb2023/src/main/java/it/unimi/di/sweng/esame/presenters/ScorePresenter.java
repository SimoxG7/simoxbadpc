package it.unimi.di.sweng.esame.presenters;

import it.unimi.di.sweng.esame.Main;
import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.NazionePunteggio;
import it.unimi.di.sweng.esame.views.DisplayView;
import it.unimi.di.sweng.esame.views.NextNationView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ScorePresenter implements Observer<List<NazionePunteggio>>, Presenter {

  //probably don't even need to keep it as a field
  @NotNull private final NextNationView nextNationView;
  @NotNull private final Model model;
  @NotNull private final DisplayView leftDisplayView;
  @NotNull private final DisplayView rightDisplayView;

  public ScorePresenter(@NotNull NextNationView nextNationView, @NotNull Model model, @NotNull DisplayView leftDisplayView, @NotNull DisplayView rightDisplayView) {
    this.nextNationView = nextNationView;
    this.model = model;
    this.leftDisplayView = leftDisplayView;
    this.rightDisplayView = rightDisplayView;
    nextNationView.addHandlers(this);
  }

  @Override
  public void update(@NotNull List<NazionePunteggio> state) {
    int i = 0;
    //state è unmodifiable, creo una copia modificabile
    List<NazionePunteggio> newState = new ArrayList<>(state);
    //faccio il sorting "naturale" per il tipo NazionePunteggio
    newState.sort(null);
    //first Main.PANEL_SIZE nations
    for (int j = 0; j < state.size() && j < Main.PANEL_SIZE; j++) {
      NazionePunteggio np = state.get(j);
      leftDisplayView.set(i++, np.toString());
    }

    //last Main.PANEL_SIZE nations
    i = 0;
    for (int j = Main.PANEL_SIZE; j < state.size() && j < 2*Main.PANEL_SIZE; j++) {
      NazionePunteggio np = state.get(j);
      rightDisplayView.set(i++, np.toString());
    }
  }

  @Override
  public void action(String text1, String text2) {
    //text1 should be "cod fullname"
    String[] splitted = text1.split(" ");
    model.changeVotes(splitted[0], splitted[1], Integer.parseInt(text2));
  }
}
