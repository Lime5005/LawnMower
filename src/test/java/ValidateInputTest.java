import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import service.ValidateLawn;
import service.ValidateOrientation;
import service.ValidatePosition;

public class ValidateInputTest {

    // 1, Lawn check
    @Test
    public void shouldCheckIfLawnIsValid() {
        // given
        ValidateLawn lawnValidator = new ValidateLawn();

        // when
        boolean result = lawnValidator.isValidLawn("5 5");

        // then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0 5", "1, 2", "s, 2"})
    public void shouldCheckIfLawnIsInvalid(String lawn) {
        // given
        ValidateLawn lawnValidator = new ValidateLawn();

        // when & then
        Assertions.assertFalse(lawnValidator.isValidLawn(lawn));
    }

    // 2, Position check
    @Test
    public void shouldCheckPositionIsValid() {
        // given
        ValidatePosition posValidator = new ValidatePosition();

        // when
        boolean result = posValidator.isValidPosition("1 2 N");

        // then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1 2 N n", "1 2 "})
    public void shouldCheckPositionIsInValid(String position) {
        // given
        ValidatePosition posValidator = new ValidatePosition();

        // when & then
        Assertions.assertFalse(posValidator.isValidPosition(position));
    }

    // 3, Orientation check
    @Test
    public void shouldCheckOrientationIsValid() {
        // given
        ValidateOrientation oriValidator = new ValidateOrientation();

        // when
        boolean result = oriValidator.isValidOrientation("GAGAGAGAA");

        // then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"GAGAG  AGAA", "GAGAQAGAA", "GAGaAAGAA"})
    public void shouldCheckOrientationIsInValid(String instruction) {
        // given
        ValidateOrientation oriValidator = new ValidateOrientation();

        // when & then
        Assertions.assertFalse(oriValidator.isValidOrientation(instruction));
    }
}
