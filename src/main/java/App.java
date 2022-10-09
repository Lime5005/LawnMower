import model.Instruction;
import model.Mower;
import model.Position;
import service.MowerService;
import service.parser.FileParser;
import service.parser.MowerInstructionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        MowerService mowerService = new MowerService();
        List<Position> positions = new ArrayList<>();
        String path = "src/test/resources/standard_test.txt";
        FileParser fileParser = new FileParser(path);
        List<String> lawnMowersAndInstructions = fileParser.getLawnMowersAndInstructions();

        MowerInstructionParser mowerInstructionParser = new MowerInstructionParser();
        Map<Mower, List<Instruction>> map = mowerInstructionParser.getMowerInstructionMap(lawnMowersAndInstructions);

        for (Mower mower : map.keySet()) {
            List<Instruction> instructions = map.get(mower);
            Position position = mowerService.executeInstruction(mower, instructions);
            positions.add(position);
        }
        System.out.println(positions); //[Position(x=1, y=3, dir=N), Position(x=5, y=1, dir=E)]
    }
}
