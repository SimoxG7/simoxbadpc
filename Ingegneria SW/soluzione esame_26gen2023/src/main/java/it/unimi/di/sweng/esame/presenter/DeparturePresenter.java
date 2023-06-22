 package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.Main;
import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.Train;
import it.unimi.di.sweng.esame.view.DepartureView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DeparturePresenter implements Observer<List<Train>>, InputPresenter {

  private final DepartureView view;
  private final int startTrain;
  private final Model model;

  public DeparturePresenter(DepartureView view, int startTrain, Model model) {
    this.view = view;
    this.startTrain = startTrain;
    this.model = model;
    view.addHandlers(this);
  }

  @Override
  public void update(@NotNull List<Train> state) {
    int i = 0;
    state.sort(null);
    for (int j = 0; j + startTrain < state.size() && j < Main.MAX_ROW_ITEMS_IN_VIEW; j++) {
      Train train = state.get(j + startTrain);
      view.set(i++, train.toString());
    }
  }

  @Override
  public void action(String text1, String text2) {
    model.departed(text1.substring(0,9).trim());
  }
}














/*
Model model = new Model();
    model.readFile();
    DisplayView left = mock(DisplayView.class);
    DisplayView right = mock(DisplayView.class);
    NextNationView next = mock(NextNationView.class);
    ScorePresenter SUT = new ScorePresenter(next, model, left, right);

    NazionePunteggio np = new NazionePunteggio("IT", "Italia", 1000);
    SUT.action(np.code() + " " + np.fullname(), "" + np.points());
    assertThat(model.getState().toString()).contains(np.toString());
*/
