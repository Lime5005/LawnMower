import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.MowerService;

import java.util.ArrayList;
import java.util.List;

public class MowerBehaviorTest {

    private final MowerService mowerServiceImp = new MowerService();

    @Test
    public void shouldMowAsInstructions() {
        // given
        Position position = new Position(1, 2, 'N');
        Lawn lawn = new Lawn(5, 5);
        List<Instruction> instructions = new ArrayList<>(); // GAGAGAGAA
        instructions.add(Instruction.LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.FORWARD);
        Mower mower = new Mower(lawn, position);

        // when
        mowerServiceImp.executeInstruction(mower, instructions);

        // then
        Position destinationPosition = new Position(1, 3, 'N');
        Assertions.assertEquals(destinationPosition, mower.getPosition());
    }
}
