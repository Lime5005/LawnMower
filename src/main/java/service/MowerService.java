package service;

import model.*;

import java.util.List;

/**
 * The class to make the mower execute the instructions as designed *
 */
public class MowerService {
    private final OrientationService orientationService = new OrientationService();

    /**
     * The main function called to get the final position after execution *
     * @param mower the mower object that contains its position and the lawn it will work on
     * @param instructionList a list of Instruction object
     * @return the mower's position after execution
     */
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

    /**
     * Help executeInstruction to execute if it's forward instruction *
     * @param position the mower's initial position
     * @param lawn the lawn that the mower will work on
     * @return the mower's final position, either moved, or not changed if forward is not possible
     */
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

    /**
     * Help the executeInstruction to execute if it's turn left or right types of instructions *
     * @param instruction the instruction received
     * @param position the mower's initial position
     * @return an Orientation object contains the mower's position orientation, or throw an exception if
     * not able to execute the instruction *
     */
    private Orientation changeOrientation(Instruction instruction, Position position) {
        if (instruction.equals(Instruction.LEFT)) {
            return orientationService.getCounterClockwiseOrientation(position);
        } else if (instruction.equals(Instruction.RIGHT)) {
            return orientationService.getClockwiseOrientation(position);
        } else {
            throw new IllegalArgumentException("Change Orientation failed with instruction + (" + instruction + ") and position (" + position + ")");
        }
    }

    /**
     * Prevent the execution if the mower is not inside the lawn *
     * @param position the mower's position
     * @param lawn the lawn that the mower will work on
     */
    private void assertMowerInsideLawn(Position position, Lawn lawn) {
        if (position.getX() > lawn.xMax() || position.getY() > lawn.yMax()) {
            throw new IllegalArgumentException("Mower position (" + position  + ") outside of lawn (" + lawn + ")");
        }
    }
}
