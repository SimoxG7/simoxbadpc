package it.unimi.di.sweng.esame.presenters;


import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.NazionePunteggio;
import it.unimi.di.sweng.esame.views.DisplayView;
import it.unimi.di.sweng.esame.views.NextNationView;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ScorePresenterTest {

  @Test
  void updateTest() {
    DisplayView displayView = mock(DisplayView.class);
    ScorePresenter SUT = new ScorePresenter(mock(NextNationView.class), mock(Model.class), displayView, mock(DisplayView.class));

    NazionePunteggio nazionePunteggio1 = mock(NazionePunteggio.class);
    when(nazionePunteggio1.toString()).thenReturn("IT Italia 0");
    NazionePunteggio nazionePunteggio2 = mock(NazionePunteggio.class);
    when(nazionePunteggio2.toString()).thenReturn("testing is very funny");

    SUT.update(List.of(
        nazionePunteggio1,
        nazionePunteggio2
    ));

    verify(displayView).set(0, "IT Italia 0");
    verify(displayView).set(1, "testing is very funny");
  }

  @Test
  void testAction() {
    Model model = new Model();
    model.readFile();
    DisplayView left = mock(DisplayView.class);
    DisplayView right = mock(DisplayView.class);
    NextNationView next = mock(NextNationView.class);
    ScorePresenter SUT = new ScorePresenter(next, model, left, right);

    NazionePunteggio np = new NazionePunteggio("IT", "Italia", 1000);
    SUT.action(np.code() + " " + np.fullname(), "" + np.points());
    assertThat(model.getState().toString()).contains(np.toString());
  }

  @Test
  void updateTest2() {
    DisplayView displayView = mock(DisplayView.class);
    Model model = new Model();
    model.readFile();
    ScorePresenter SUT = new ScorePresenter(mock(NextNationView.class), model, displayView, mock(DisplayView.class));
    model.notifyObservers();

    verify(displayView).set(0, "Estonia 0");
  }

}