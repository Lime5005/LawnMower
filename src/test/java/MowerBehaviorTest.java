import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.MowerService;

import java.util.ArrayList;
import java.util.List;

public class MowerBehaviorTest {

    private final MowerService mowerService = new MowerService();

    @Test
    public void shouldMowAsInstructionsAndTurnLeft() {
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
        mowerService.executeInstruction(mower, instructions);

        // then
        Position destinationPosition = new Position(1, 3, 'N');
        Assertions.assertEquals(destinationPosition, mower.getPosition());
    }


    @Test
    public void shouldMowAsInstructionsAndTurnRight() {
        // given
        Position position = new Position(1, 2, 'N');
        Lawn lawn = new Lawn(5, 5);
        List<Instruction> instructions = new ArrayList<>(); // DADD
        instructions.add(Instruction.RIGHT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.RIGHT);
        instructions.add(Instruction.RIGHT);
        Mower mower = new Mower(lawn, position);

        // when
        mowerService.executeInstruction(mower, instructions);

        // then
        Position destinationPosition = new Position(2, 2, 'W');
        Assertions.assertEquals(destinationPosition, mower.getPosition());
    }

    @Test
    public void shouldNotMowAsInstructions() {
        // given
        Position position = new Position(1, 2, 'N');
        Lawn lawn = new Lawn(5, 5);
        List<Instruction> instructions = new ArrayList<>(); // GAA
        instructions.add(Instruction.LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.FORWARD);
        Mower mower = new Mower(lawn, position);

        // when
        mowerService.executeInstruction(mower, instructions);

        // then
        Position destinationPosition = new Position(0, 2, 'W');
        Assertions.assertEquals(destinationPosition, mower.getPosition());
    }

    @Test
    public void shouldSkipAndContinueAsInstructions() {
        // given
        Position position = new Position(1, 2, 'N');
        Lawn lawn = new Lawn(5, 5);
        List<Instruction> instructions = new ArrayList<>(); // GAAGAA
        instructions.add(Instruction.LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.FORWARD);
        Mower mower = new Mower(lawn, position);

        // when
        mowerService.executeInstruction(mower, instructions);

        // then
        Position destinationPosition = new Position(0, 0, 'S');
        Assertions.assertEquals(destinationPosition, mower.getPosition());
    }

    @Test
    public void shouldNotExecuteIfMowerNotInsideLawn() {
        // given
        Position position = new Position(1, 3, 'N');
        Lawn lawn = new Lawn(1, 2);
        List<Instruction> instructions = new ArrayList<>(); // G
        instructions.add(Instruction.LEFT);
        Mower mower = new Mower(lawn, position);

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> mowerService.executeInstruction(mower, instructions));
    }
}
