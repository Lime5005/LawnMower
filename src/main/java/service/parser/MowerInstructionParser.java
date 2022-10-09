package service.parser;

import model.*;
import service.ValidateLawn;
import service.ValidateOrientation;
import service.ValidatePosition;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MowerInstructionParser {
    // Initialise the returning data
    private final List<Mower> mowers = new ArrayList<>();
    private final Map<Mower, List<Instruction>> mowerInstructionMap = new LinkedHashMap<>();

    // Initialise all validators
    ValidateLawn lawnValidator = new ValidateLawn();
    ValidatePosition positionValidator = new ValidatePosition();
    ValidateOrientation orientationValidator = new ValidateOrientation();

    public Lawn getLawnFromFile(List<String> fileData) {
        String lawnStr = fileData.get(0);
        if (lawnValidator.isValidLawn(lawnStr)) {
            String[] split = lawnStr.split("\\s+");
            int xMax = Integer.parseInt(split[0]);
            int yMax = Integer.parseInt(split[1]);
            return new Lawn(xMax, yMax);
        } else {
            throw new IllegalArgumentException("Invalid lawn data " + lawnStr);
        }
    }

    public Map<Mower, List<Instruction>> getMowerInstructionMap(List<String> fileData) {
        Lawn lawnFromFile = getLawnFromFile(fileData);
        List<String> mowerAndInstructions = fileData.subList(1, fileData.size());
        Mower mower;
        for (int i = 0; i < mowerAndInstructions.size(); i += 2) {
            String mowerPosition = mowerAndInstructions.get(i);
            String instructions = mowerAndInstructions.get(i + 1);
            if (positionValidator.isValidPosition(mowerPosition)) {
                mower = parseStringToMower(mowerPosition, lawnFromFile);
                List<Instruction> instructionsForMower = parseStringToInstructions(instructions);
                mowers.add(mower);
                mowerInstructionMap.put(mower, instructionsForMower);
            }
        }
        return mowerInstructionMap;
    }

    private Mower parseStringToMower(String mowerPosition, Lawn lawnFromFile) {
        String[] split = mowerPosition.split("\\s+");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        char dir = split[2].charAt(0);
        return new Mower(lawnFromFile, new Position(x, y, dir));
    }

    private List<Instruction> parseStringToInstructions(String instructions) {
        List<Instruction> list = new ArrayList<>();
        for (char c : instructions.toCharArray()) {
            String s = String.valueOf(c);
            if (orientationValidator.isValidOrientation(s)) {
                Instruction dir = Instruction.getDir(c);
                if (dir != null) {
                    list.add(dir);
                }
            }
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No instructions");
        }
        return list;
    }
}
