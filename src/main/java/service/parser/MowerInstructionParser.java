package service.parser;

import model.*;
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
    // Initialize the returning data
    private final List<Mower> mowers = new ArrayList<>();
    private final Map<Mower, List<Instruction>> mowerInstructionMap = new LinkedHashMap<>();

    public Lawn getLawnFromFile(List<String> fileData) {
        String lawnStr = fileData.get(0);
        if (ValidateLawn.isValidLawn(lawnStr)) {
            int xMax = Integer.parseInt(lawnStr.split("\\s+")[0]);
            int yMax = Integer.parseInt(lawnStr.split("\\s+")[1]);
            return new Lawn(xMax, yMax);
        } else {
            throw new IllegalArgumentException("Invalid lawn data " + lawnStr);
        }
    }

    public List<Mower> getMowers() {
        return mowers;
    }

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
                throw new IllegalArgumentException("Invalid position " + mowerPosition);
            }
        }
        return mowerInstructionMap;
    }

    private Mower parseStringToMower(String mowerPosition, Lawn lawnFromFile) {
        int x = Integer.parseInt(mowerPosition.split("\\s+")[0]);
        int y = Integer.parseInt(mowerPosition.split("\\s+")[1]);
        char orientation = mowerPosition.split("\\s+")[2].charAt(0);
        return new Mower(lawnFromFile, new Position(x, y, orientation));
    }

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
                throw new IllegalArgumentException("Invalid orientation " + s);
            }
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No instructions");
        }
        return list;
    }
}
