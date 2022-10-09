import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ValidateLawn;

public class ValidateInputTest {

    @Test
    public void checkAValidLawn() {
        ValidateLawn validator = new ValidateLawn();
        boolean result = validator.checkLawn("5 5");
        Assertions.assertTrue(result);
    }
}
