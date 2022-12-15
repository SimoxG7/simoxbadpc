package it.unimi.di.sweng.temperature.presenter;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.model.Model;
import it.unimi.di.sweng.temperature.view.View;
import org.jetbrains.annotations.NotNull;

public class TemperaturePresenter implements Presenter {

    private final ScaleStrategy scaleStrategy;
    private final Model temperatureModel;
    private final View view;

    public TemperaturePresenter(final ScaleStrategy scaleStrategy, final Model model, final View view) {
        this.scaleStrategy = scaleStrategy;
        this.temperatureModel = model;
        this.view = view;
    }

    @Override
    public void update(@NotNull Observable<Double> subject, @NotNull Double state) {
        if(temperatureModel.equals(subject)){
            view.setValue(String.valueOf(scaleStrategy.valueFromCelsius(state)));
        }
    }

    @Override
    public void updateModel(@NotNull String text) {
        temperatureModel.setTemp(scaleStrategy.valueToCelsius(Double.parseDouble(text)));
    }
}
