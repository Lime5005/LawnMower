package service;

import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MowerService {
    private final Logger logger = LoggerFactory.getLogger(MowerService.class);
    private final OrientationService orientationService = new OrientationService();

    public Position executeInstruction(Mower mower, List<Instruction> instructionList) {
        logger.info("[Function: executeInstruction] mower (" + mower + ") and instructionList (" + instructionList + ")");
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
                if (orientation != null) {
                    char ori = orientation.getOri();
                    assert position != null;
                    position.setDir(ori);
                }
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
            logger.error("Error while changeOrientation for instruction " + instruction);
            throw new IllegalArgumentException("Change Orientation failed with instruction + (" + instruction + ") and position (" + position + ")");
        }
    }

    private void assertMowerInsideLawn(Position position, Lawn lawn) {
        if (position.getX() > lawn.xMax() || position.getY() > lawn.yMax()) {
            logger.error("Error while assertMowerInsideLawn for position " + position + " and lawn " + lawn);
            throw new IllegalArgumentException("Mower position (" + position  + ") outside of lawn (" + lawn + ")");
        }
    }
}
