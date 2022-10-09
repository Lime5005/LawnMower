import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ValidateLawn;
import service.ValidateOrientation;
import service.ValidatePosition;

public class ValidateInputTest {

    // 1, Lawn check
    @Test
    public void checkAValidLawn() {
        ValidateLawn lawnValidator = new ValidateLawn();
        boolean lawnStr1 = lawnValidator.checkLawn("5 5");
        Assertions.assertTrue(lawnStr1);
    }

    @Test
    public void checkAnInvalidLawn() {
        ValidateLawn lawnValidator = new ValidateLawn();
        boolean lawnStr2 = lawnValidator.checkLawn("0 5");
        boolean lawnStr3 = lawnValidator.checkLawn("1, 2");
        boolean lawnStr4 = lawnValidator.checkLawn("s, 2");
        Assertions.assertFalse(lawnStr2);
        Assertions.assertFalse(lawnStr3);
        Assertions.assertFalse(lawnStr4);
    }

    @Test
    public void givenLettersForLawnShouldNotBeAllowed() {
        ValidateLawn lawnValidator = new ValidateLawn();
        Assertions.assertThrows(NumberFormatException.class, () -> {
            lawnValidator.checkLawn("s s");
        });
    }

    // 2, Position check
    @Test
    public void checkAValidPosition() {
        ValidatePosition posValidator = new ValidatePosition();
        boolean positionStr1 = posValidator.checkPosition("1 2 N");
        Assertions.assertTrue(positionStr1);
    }

    @Test
    public void checkAnInValidPosition() {
        ValidatePosition posValidator = new ValidatePosition();
        boolean positionStr2 = posValidator.checkPosition("1 2 N n");
        boolean positionStr3 = posValidator.checkPosition("1 2 ");
        Assertions.assertFalse(positionStr2);
        Assertions.assertFalse(positionStr3);
    }

    @Test
    public void givenOnlyLettersShouldNotBeAllowed() {
        ValidatePosition posValidator = new ValidatePosition();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            posValidator.checkPosition("A A D");
        });
    }

    @Test
    public void givenOnlyNumbersShouldNotAllowed() {
        ValidatePosition posValidator = new ValidatePosition();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            posValidator.checkPosition("1 2 3");
        });
    }

    // 3, Orientation check
    @Test
    public void checkAValidOrientation() {
        ValidateOrientation oriValidator = new ValidateOrientation();
        boolean oriStr1 = oriValidator.checkOrientation("GAGAGAGAA");
        Assertions.assertTrue(oriStr1);
    }

    @Test
    public void checkAnInValidOrientation() {
        ValidateOrientation oriValidator = new ValidateOrientation();
        boolean oriStr2 = oriValidator.checkOrientation("GAGAG  AGAA");
        boolean oriStr3 = oriValidator.checkOrientation("GAGAQAGAA");
        boolean oriStr4 = oriValidator.checkOrientation("GAGaAAGAA");
        Assertions.assertFalse(oriStr2);
        Assertions.assertFalse(oriStr3);
        Assertions.assertFalse(oriStr4);
    }

    @Test
    public void givenNumbersForOrientationAreNotAllowed() {
        ValidateOrientation oriValidator = new ValidateOrientation();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            oriValidator.checkOrientation("12345678");
        });
    }
}
