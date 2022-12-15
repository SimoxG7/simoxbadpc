package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.Observer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModel implements Model {

  private final List<Observer<Double>> observers;
  private double temp;

  public TemperatureModel(){
    observers = new ArrayList<>();
  }

  @Override
  public void notifyObservers() {
    for (Observer<Double> ob : observers) {
      ob.update(this, temp);
    }
  }

  @Override
  public void addObserver(@NotNull Observer<Double> obs) {
    observers.add(obs);
  }

  @Override
  public @NotNull Double getState() {
    return temp;
  }

  @Override
  public double getTemp() {
    return temp;
  }

  @Override
  public void setTemp(final double temp) {
    if (temp != this.temp) {
      this.temp = temp;
      notifyObservers();
    }
  }

}
