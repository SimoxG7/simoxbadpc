package it.unimi.di.sweng.esame.presenter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.model.Train;
import it.unimi.di.sweng.esame.view.DepartureView;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

class DeparturePresenterTest {

  @Test
  void updateTest() {
    DepartureView view = mock(DepartureView.class);
    DeparturePresenter SUT = new DeparturePresenter(view, 0, mock(Model.class));

    Train train1 = mock(Train.class);
    when(train1.toString()).thenReturn("Cod1 Milano 14:12 10");
    Train train2 = mock(Train.class);
    when(train2.toString()).thenReturn("Da stampare nella vista");

    SUT.update(List.of(
        //new Train("Cod1", "Milano", LocalTime.of(14, 12), Duration.ofMinutes(10)),
        //new Train("Cod2", "Bologna", LocalTime.of(16, 12), Duration.ofMinutes(10))
        train1,
        train2
    ));

    verify(view).set(0, "Cod1 Milano 14:12 10");
    verify(view).set(1, "Da stampare nella vista");
  }
}