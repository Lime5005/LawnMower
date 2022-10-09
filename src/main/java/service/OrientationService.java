package service;

import model.Orientation;
import model.Position;

public class OrientationService  {
    public Position getForwardPosition(Position position) {
        Orientation orientationByOri = Orientation.getOrientationByOri(position.getDir());
        assert orientationByOri != null;
        int x = position.getX() + orientationByOri.getXForward();
        int y = position.getY() + orientationByOri.getYForward();
        return new Position(x, y, position.getDir());
    }

    public Orientation getClockwiseOrientation(Position position) {
        return switch (position.getDir()) {
            case 'N' -> Orientation.EAST;
            case 'E' -> Orientation.SOUTH;
            case 'S' -> Orientation.WEST;
            case 'W' -> Orientation.NORTH;
            default -> throw new IllegalStateException("Unexpected value to turn right: " + position.getDir());
        };
    }

    public Orientation getCounterClockwiseOrientation(Position position) {
        return switch (position.getDir()) {
            case 'N' -> Orientation.WEST;
            case 'E' -> Orientation.NORTH;
            case 'S' -> Orientation.EAST;
            case 'W' -> Orientation.SOUTH;
            default -> throw new IllegalStateException("Unexpected value to turn left: " + position.getDir());
        };
    }
}
