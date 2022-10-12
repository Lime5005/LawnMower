package model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Orientation {
    NORTH('N', 0, 1),
    EAST('E', 1, 0),
    SOUTH('S', 0, -1),
    WEST('W', -1, 0);

    private final char orientation;
    private final int xForward;
    private final int yForward;

    public char getOrientation() {
        return orientation;
    }

    Orientation(char orientation, int xForward, int yForward) {
        this.orientation = orientation;
        this.xForward = xForward;
        this.yForward = yForward;
    }

    public static Orientation getByChar(char c) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.orientation == c) {
                return orientation;
            }
        }
        return null;
    }
}
