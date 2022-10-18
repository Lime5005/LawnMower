package service;

import model.Instruction;
import model.Lawn;
import model.Mower;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.parser.MowerInstructionParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserTest {

    private final MowerInstructionParser mowerInstructionParser = new MowerInstructionParser();

    @Test
    public void shouldThrowExceptionIfInvalidLawn() {
        // given
        List<String> list = new ArrayList<>();
        list.add("0 0\n");
        list.add("1 2 N\n");
        list.add("D");

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> mowerInstructionParser.getLawnFromFile(list));
    }

    @Test
    public void shouldThrowExceptionIfInvalidPosition() {
        // given
        List<String> list = new ArrayList<>();
        list.add("1 1\n");
        list.add("-1 0 S\n");
        list.add("D");

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> mowerInstructionParser.getMowerInstructionMap(list));
    }

    @Test
    public void shouldThrowExceptionIfInvalidInstructions() {
        // given
        String sb = "A" + "s";

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> mowerInstructionParser.parseStringToInstructions(sb));
    }

    @Test
    public void shouldParseLawnIfValidLawn() {
        // given
        List<String> list = new ArrayList<>();
        list.add("3 3\n");
        list.add("1 2 N");
        list.add("ADD");

        // when
        Lawn lawnFromFile = mowerInstructionParser.getLawnFromFile(list);

        // then
        Lawn lawn = new Lawn(3, 3);
        Assertions.assertEquals(lawn, lawnFromFile);
    }

    @Test
    public void shouldParseMowerAndInstructionsIfAllValid() {
        // given
        List<String> list = new ArrayList<>();
        list.add("3 3\n");
        list.add("1 2 N\n");
        list.add("ADD");

        // when
        Map<Mower, List<Instruction>> mowerInstructionMap = mowerInstructionParser.getMowerInstructionMap(list);

        // then
        Map<Mower, List<Instruction>> map = new HashMap<>();
        Lawn lawn = new Lawn(3, 3);
        Mower mower = new Mower(lawn, new Position(1, 2, 'N'));
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.RIGHT);
        instructions.add(Instruction.RIGHT);
        map.put(mower, instructions);
        int size = mowerInstructionMap.keySet().size();

        Assertions.assertEquals(size, mowerInstructionParser.getMowers().size());
        Assertions.assertEquals(map, mowerInstructionMap);
    }

    @Test
    public void shouldThrowExceptionIfEmptyInstructions() {
        // given
        String string = "";

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> mowerInstructionParser.parseStringToInstructions(string));
    }
}
