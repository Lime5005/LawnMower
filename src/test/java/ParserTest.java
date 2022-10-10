import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.parser.MowerInstructionParser;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    private final MowerInstructionParser mowerInstructionParser = new MowerInstructionParser();

    @Test
    public void shouldThrowExceptionIfInvalidLawn() {
        // given
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("0");

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mowerInstructionParser.getLawnFromFile(list);
        });
    }

    @Test
    public void shouldThrowExceptionIfInvalidPosition() {
        // given
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("-1");
        list.add("N");

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mowerInstructionParser.getMowerInstructionMap(list);
        });
    }

    @Test
    public void shouldThrowExceptionIfInvalidInstructions() {
        // given
        StringBuilder sb = new StringBuilder();
        sb.append("A");
        sb.append("s");

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            mowerInstructionParser.parseStringToInstructions(sb.toString());
        });
    }
}
