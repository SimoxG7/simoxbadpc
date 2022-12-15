package it.unimi.di.sweng.temperature.presenter;

public class FahrenheitStrategy implements ScaleStrategy {
    @Override
    public double valueFromCelsius(double temperature) {
        return temperature * 1.8 + 32;
    }

    @Override
    public double valueToCelsius(double temperature) {
        return (temperature - 32) * (5.0/9.0);
    }
}
