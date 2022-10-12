package service;

import model.Orientation;
import model.Position;

public class OrientationService  {
    public Position getForwardPosition(Position position) {
        Orientation orientation = Orientation.getByChar(position.getOrientation());
        assert orientation != null;
        int x = position.getX() + orientation.getXForward();
        int y = position.getY() + orientation.getYForward();
        return new Position(x, y, position.getOrientation());
    }

    public Orientation getClockwiseOrientation(Position position) {
        return switch (position.getOrientation()) {
            case 'N' -> Orientation.EAST;
            case 'E' -> Orientation.SOUTH;
            case 'S' -> Orientation.WEST;
            case 'W' -> Orientation.NORTH;
            default -> throw new IllegalStateException("Unexpected value to turn right: " + position.getOrientation());
        };
    }

    public Orientation getCounterClockwiseOrientation(Position position) {
        return switch (position.getOrientation()) {
            case 'N' -> Orientation.WEST;
            case 'E' -> Orientation.NORTH;
            case 'S' -> Orientation.EAST;
            case 'W' -> Orientation.SOUTH;
            default -> throw new IllegalStateException("Unexpected value to turn left: " + position.getOrientation());
        };
    }
}
