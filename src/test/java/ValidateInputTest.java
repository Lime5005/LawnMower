import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

    @Test
    public void shouldCheckIfLawnIsInvalid() {
        // given
        ValidateLawn lawnValidator = new ValidateLawn();

        // when
        boolean result1 = lawnValidator.isValidLawn("0 5");
        boolean result2 = lawnValidator.isValidLawn("1, 2");
        boolean result3 = lawnValidator.isValidLawn("s, 2");

        // then
        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
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

    @Test
    public void shouldCheckPositionIsInValid() {
        // given
        ValidatePosition posValidator = new ValidatePosition();

        // when
        boolean result1 = posValidator.isValidPosition("1 2 N n");
        boolean result2 = posValidator.isValidPosition("1 2 ");

        // then
        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
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

    @Test
    public void shouldCheckOrientationIsInValid() {
        // given
        ValidateOrientation oriValidator = new ValidateOrientation();

        // when
        boolean result1 = oriValidator.isValidOrientation("GAGAG  AGAA");
        boolean result2 = oriValidator.isValidOrientation("GAGAQAGAA");
        boolean result3 = oriValidator.isValidOrientation("GAGaAAGAA");

        // then
        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
    }
}
