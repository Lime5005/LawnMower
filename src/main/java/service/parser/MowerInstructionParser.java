package service.parser;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.MowerService;
import service.ValidateLawn;
import service.ValidateOrientation;
import service.ValidatePosition;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will parse the string collected from file to Objects:
 * Lawn, Mower, Instructions*
 */
public class MowerInstructionParser {
    private final Logger logger = LoggerFactory.getLogger(MowerInstructionParser.class);
    // Initialize the returning data
    private final List<Mower> mowers = new ArrayList<>();
    private final Map<Mower, List<Instruction>> mowerInstructionMap = new LinkedHashMap<>();

    /**
     * Take the string list from file and get the first line *
     * @param fileData file data list
     * @return a Lawn object
     */
    public Lawn getLawnFromFile(List<String> fileData) {
        String lawnStr = fileData.get(0);
        if (ValidateLawn.isValidLawn(lawnStr)) {
            int xMax = Integer.parseInt(lawnStr.split("\\s+")[0]);
            int yMax = Integer.parseInt(lawnStr.split("\\s+")[1]);
            return new Lawn(xMax, yMax);
        } else {
            logger.error("Error while parsing lawn " + lawnStr);
            throw new IllegalArgumentException("Invalid lawn data " + lawnStr);
        }
    }

    public List<Mower> getMowers() {
        return mowers;
    }

    /**
     * Take the string list from file and get the rest of the lines except the first one *
     * @param fileData  file data list
     * @return a map contains the mower as key, its instructions as value
     */
    public Map<Mower, List<Instruction>> getMowerInstructionMap(List<String> fileData) {
        Lawn lawnFromFile = getLawnFromFile(fileData);
        List<String> mowerAndInstructions = fileData.subList(1, fileData.size());
        for (int i = 0; i < mowerAndInstructions.size(); i += 2) {
            String mowerPosition = mowerAndInstructions.get(i);
            String instructions = mowerAndInstructions.get(i + 1);
            if (ValidatePosition.isValidPosition(mowerPosition)) {
                Mower mower = parseStringToMower(mowerPosition, lawnFromFile);
                List<Instruction> instructionsForMower = parseStringToInstructions(instructions);
                mowers.add(mower);
                mowerInstructionMap.put(mower, instructionsForMower);
            } else {
                logger.error("Error while parsing position " + mowerPosition);
                throw new IllegalArgumentException("Invalid position " + mowerPosition);
            }
        }
        return mowerInstructionMap;
    }

    /**
     * Help getMowerInstructionMap to extract the mower object *
     * @param mowerPosition the string line from file which is valid for mower data pattern
     * @param lawnFromFile the lawn object received from the getMowerInstructionMap
     * @return a new mower object
     */
    private Mower parseStringToMower(String mowerPosition, Lawn lawnFromFile) {
        int x = Integer.parseInt(mowerPosition.split("\\s+")[0]);
        int y = Integer.parseInt(mowerPosition.split("\\s+")[1]);
        char orientation = mowerPosition.split("\\s+")[2].charAt(0);
        return new Mower(lawnFromFile, new Position(x, y, orientation));
    }

    /**
     * Help getMowerInstructionMap to extract the string instructions as an object,
     * if is valid instruction pattern *
     * @param instructions the string line from file
     * @return a list of Instruction, or throw an IllegalArgumentException if empty or invalid data
     */
    public List<Instruction> parseStringToInstructions(String instructions) {
        List<Instruction> list = new ArrayList<>();
        for (char c : instructions.toCharArray()) {
            String s = String.valueOf(c);
            if (ValidateOrientation.isValidOrientation(s)) {
                Instruction dir = Instruction.getDirection(c);
                if (dir != null) {
                    list.add(dir);
                }
            } else {
                logger.error("Error while parseStringToInstructions " + instructions);
                throw new IllegalArgumentException("Invalid orientation " + s);
            }
        }
        if (list.isEmpty()) {
            logger.error("Error while parseStringToInstructions size 0");
            throw new IllegalArgumentException("No instructions");
        }
        return list;
    }
}
