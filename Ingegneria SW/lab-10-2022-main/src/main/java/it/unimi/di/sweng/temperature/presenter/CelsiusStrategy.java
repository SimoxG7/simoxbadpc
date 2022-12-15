package it.unimi.di.sweng.temperature.presenter;

public class CelsiusStrategy implements ScaleStrategy {
    @Override
    public double valueFromCelsius(final double temperature) {
        return temperature;
    }

    @Override
    public double valueToCelsius(final double temperature) {
        return temperature;
    }
}
