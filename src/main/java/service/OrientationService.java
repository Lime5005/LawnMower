package service;

import model.Orientation;
import model.Position;

/**
 * Separate the whole business logic into 3 parts:
 * 1, 'A', go forward, the coordinate changed, but the orientation will not
 * 2, 'D', turn right, the orientation changed, but the coordinate will not
 * 3, 'G', turn left, same as 'D', only the orientation changed.
 */
public class OrientationService  {
    /**
     * Go forward, take the initial orientation, but change the coordinate *
     * @param position a mower's initial position
     * @return the changed new position
     */
    public Position getForwardPosition(Position position) {
        Orientation orientation = Orientation.getByChar(position.getOrientation());
        assert orientation != null;
        int x = position.getX() + orientation.getXForward();
        int y = position.getY() + orientation.getYForward();
        return new Position(x, y, position.getOrientation());
    }

    /**
     * If turn right, switch to any of the possible 4 orientations,
     * depending on it's initial position *
     * @param position a mower's initial position
     * @return the changed orientation, or throw an exception if not valid position
     */
    public Orientation getClockwiseOrientation(Position position) {
        return switch (position.getOrientation()) {
            case 'N' -> Orientation.EAST;
            case 'E' -> Orientation.SOUTH;
            case 'S' -> Orientation.WEST;
            case 'W' -> Orientation.NORTH;
            default -> throw new IllegalStateException("Unexpected value to turn right: " + position.getOrientation());
        };
    }

    /**
     * If turn left, switch to any of the possible 4 orientations,
     * depending on it's initial position *
     * @param position a mower's initial position
     * @return the changed orientation, or throw an exception if not valid position
     */
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
