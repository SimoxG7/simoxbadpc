package it.unimi.di.sweng.lab03;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


@Timeout(2)
public class ForthInterpreterTest {


  private Interpreter interpreter;

  @BeforeEach
  public void setUp() throws Exception {
    interpreter = new ForthInterpreter();
  }

  @Test
  public void testEmptyInput() {
    interpreter.input("");
    assertThat(interpreter.toString()).isEqualTo("<- Top");
  }

  @Test
  public void firstNotEmptyInput() {
    interpreter.input("1");
    assertThat(interpreter.toString()).isEqualTo("1 <- Top");
    interpreter.input("1 2");
    assertThat(interpreter.toString()).isEqualTo("1 2 <- Top");
  }

  @Test
  public void signCheck() {
    interpreter.input("1 -2 3");
    assertThat(interpreter.toString()).isEqualTo("1 -2 3 <- Top");
    interpreter.input("1 +2 3");
    assertThat(interpreter.toString()).isEqualTo("1 2 3 <- Top");
  }

  @Test
  public void whitespaceCheck() {
    interpreter.input("1   2 \n3");
    assertThat(interpreter.toString()).isEqualTo("1 2 3 <- Top");
    interpreter.input("1\n2");
    assertThat(interpreter.toString()).isEqualTo("1 2 <- Top");
    interpreter.input("1 2");
    assertThat(interpreter.toString()).isEqualTo("1 2 <- Top");
  }

  @Test
  public void addTest() {
    interpreter.input("1 2 +");
    assertThat(interpreter.toString()).isEqualTo("3 <- Top");
    interpreter.input("1 2 + 5 +");
    assertThat(interpreter.toString()).isEqualTo("8 <- Top");
  }

  @Test
  public void illegalAdd() {

    assertThatThrownBy(() -> {
      interpreter.input("1 2+");
    }).isInstanceOf(IllegalArgumentException.class).hasMessage("Token error '2+'");

    assertThatThrownBy(() -> {
      interpreter.input("1 2 ++5 +");
    }).isInstanceOf(IllegalArgumentException.class).hasMessage("Token error '++5'");
  }

  @Test
  public void checkUnderflow() {

    assertThatThrownBy(() -> {
      interpreter.input("1 +");
    }).isInstanceOf(IllegalArgumentException.class).hasMessage("Stack Underflow");
  }

  @Test
  public void multCheck() {
    interpreter.input("1 2 *");
    assertThat(interpreter.toString()).isEqualTo("2 <- Top");
    interpreter.input("1 2 * 5 *");
    assertThat(interpreter.toString()).isEqualTo("10 <- Top");
  }

}
