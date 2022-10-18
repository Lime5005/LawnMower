import lombok.extern.slf4j.Slf4j;
import model.Instruction;
import model.Mower;
import model.Position;
import service.MowerService;
import service.parser.FileParser;
import service.parser.MowerInstructionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class App {
    public static void main(String[] args) {
        MowerService mowerService = new MowerService();
        //String path = "src/test/resources/standard_test.txt";
        try {
            String path = args[0];
            FileParser fileParser = new FileParser(path);
            List<String> lawnMowersAndInstructions = fileParser.getLawnMowersAndInstructions();

            MowerInstructionParser mowerInstructionParser = new MowerInstructionParser();
            Map<Mower, List<Instruction>> map = mowerInstructionParser.getMowerInstructionMap(lawnMowersAndInstructions);

            map.keySet().stream()
                    .map(mower ->
                            mowerService.executeInstruction(mower, map.get(mower)))
                    .forEach(System.out::println);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("No args in terminal command" + e);
            System.out.println("No file path");
        }
    }
}
