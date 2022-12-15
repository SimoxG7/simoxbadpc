package it.unimi.di.sweng.temperature;

import static org.junit.Assert.assertEquals;

import it.unimi.di.sweng.temperature.model.Model;
import it.unimi.di.sweng.temperature.view.MyTextView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class IntegrationTest extends ApplicationTest {
  private MyTextView celsiusField;
  private MyTextView fahrenheitField;

  @Override
  public void start(Stage stage) {
    stage.setTitle("Temperature 2022");

    celsiusField = new MyTextView("Celsius");
    fahrenheitField = new MyTextView("Fahreneit");

    GridPane gridPane = new GridPane();
    gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    gridPane.add(celsiusField, 1, 0);
    gridPane.add(fahrenheitField, 1, 1);

    Model model;

    // TODO uguale a quanto fatto nel Main.start

    Scene scene = new Scene(gridPane);
    stage.setScene(scene);
    stage.show();

  }

  @Test
  public void twoTextFieldWithDifferentStrategies() {
    doubleClickOn(celsiusField);
    write("100");
    press(KeyCode.ENTER);
    release(KeyCode.ENTER);
    assertEquals("212.00", fahrenheitField.getValue());

    doubleClickOn(celsiusField);
    write("5.0");
    press(KeyCode.ENTER);
    release(KeyCode.ENTER);
    assertEquals("41.00", fahrenheitField.getValue());

    doubleClickOn(fahrenheitField);
    write("212");
    press(KeyCode.ENTER);
    release(KeyCode.ENTER);
    assertEquals("100.00", celsiusField.getValue());
  }
}
