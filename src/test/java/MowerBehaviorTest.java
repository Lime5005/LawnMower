import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MowerBehaviorTest {
    @Test
    public void shouldMowAsInstructions() {
        // given
        Position position = new Position();
        Orientation orientation = Orientation.getOrientationByOri('N');
        Lawn lawn = new Lawn(2, 3);
        Mower mower = new Mower(lawn, position);
        List<Instruction> instructions = new ArrayList<>();
        mower.setInstruction(instructions);

        // when
        mower.executeInstruction();

        // then
        Assertions.assertEquals(destinationPosition, mower.getPosition());

    }
}
