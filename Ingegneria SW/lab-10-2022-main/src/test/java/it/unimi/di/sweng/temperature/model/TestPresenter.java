package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.presenter.*;
import it.unimi.di.sweng.temperature.view.View;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestPresenter {

    @Test
    public void testTemperaturePresenterUpdateModel() {
        View view = mock(View.class);
        Model model = mock(Model.class);
        Presenter sut = new TemperaturePresenter(new CelsiusStrategy(), model, view);
        sut.updateModel("34.56");
        verify(model).setTemp(34.56);
    }

    @Test
    public void testTemperaturePresenterUpdateView(){
        View view = mock(View.class);
        Model model = new TemperatureModel();
        Presenter sut = new TemperaturePresenter(new CelsiusStrategy(), model, view);
        sut.update(model, 34.56);
        verify(view).setValue("34.56");
    }

    @Test
    public void testCtoF(){
        assertThat(new FahrenheitStrategy().valueFromCelsius(50.00)).isCloseTo(122.0, within(0.01));
    }

    @Test
    public void testFtoC(){
        assertThat(new FahrenheitStrategy().valueToCelsius(50.00)).isCloseTo(10.0, within(0.01));
    }

}
