package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ValidateInputTest {

    // 1, Lawn check
    @Test
    public void shouldCheckIfLawnIsValid() {
        // given & when
        boolean result = ValidateLawn.isValidLawn("5 5");

        // then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0 5", "1, 2", "s, 2"})
    public void shouldCheckIfLawnIsInvalid(String lawn) {
        // given & when & then
        Assertions.assertFalse(ValidateLawn.isValidLawn(lawn));
    }

    // 2, Position check
    @Test
    public void shouldCheckPositionIsValid() {
        // given & when
        boolean result = ValidatePosition.isValidPosition("1 2 N");

        // then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 2 N n", "1 2 "})
    public void shouldCheckPositionIsInValid(String position) {
        // given & when & then
        Assertions.assertFalse(ValidatePosition.isValidPosition(position));
    }

    // 3, Orientation check
    @Test
    public void shouldCheckOrientationIsValid() {
        // given & when
        boolean result = ValidateOrientation.isValidOrientation("GAGAGAGAA");

        // then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"GAGAG  AGAA", "GAGAQAGAA", "GAGaAAGAA"})
    public void shouldCheckOrientationIsInValid(String instruction) {
        // given & when & then
        Assertions.assertFalse(ValidateOrientation.isValidOrientation(instruction));
    }
}
