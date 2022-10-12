package service;

import model.*;

import java.util.List;

public class MowerService {
    private final OrientationService orientationService = new OrientationService();

    public Position executeInstruction(Mower mower, List<Instruction> instructionList) {
        Position position = mower.getPosition();
        Lawn lawn = mower.getLawn();
        assertMowerInsideLawn(position, lawn);
        for (Instruction instruction : instructionList) {
            if (instruction == Instruction.FORWARD) {
                if (goForward(position, lawn) != null) {
                     position = goForward(position, lawn);
                }
            } else {
                Orientation orientation = changeOrientation(instruction, position);
                char orientationChar = orientation.getOrientation();
                position.setOrientation(orientationChar);
            }
        }
        mower.setPosition(position);
        return position;
    }

    private Position goForward(Position position, Lawn lawn) {
        Position forwardPosition = orientationService.getForwardPosition(position);
        int x = forwardPosition.getX();
        int y = forwardPosition.getY();
        if ((x >= 0 && x <= lawn.xMax()) && (y >= 0 && y <= lawn.yMax())) {
            return forwardPosition;
        } else {
            return position;
        }
    }

    private Orientation changeOrientation(Instruction instruction, Position position) {
        if (instruction.equals(Instruction.LEFT)) {
            return orientationService.getCounterClockwiseOrientation(position);
        } else if (instruction.equals(Instruction.RIGHT)) {
            return orientationService.getClockwiseOrientation(position);
        } else {
            throw new IllegalArgumentException("Change Orientation failed with instruction + (" + instruction + ") and position (" + position + ")");
        }
    }

    private void assertMowerInsideLawn(Position position, Lawn lawn) {
        if (position.getX() > lawn.xMax() || position.getY() > lawn.yMax()) {
            throw new IllegalArgumentException("Mower position (" + position  + ") outside of lawn (" + lawn + ")");
        }
    }
}
