package it.unimi.di.sweng.temperature.presenter;

public interface ScaleStrategy {
  double valueFromCelsius(double temperature);

  double valueToCelsius(double temperature);
}
