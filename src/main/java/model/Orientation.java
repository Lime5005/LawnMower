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

    private final char ori;
    private final int xForward;
    private final int yForward;

    public char getOri() {
        return ori;
    }

    Orientation(char ori, int xForward, int yForward) {
        this.ori = ori;
        this.xForward = xForward;
        this.yForward = yForward;
    }

    public static Orientation getOrientationByOri(char ori) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.ori == ori) {
                return orientation;
            }
        }
        return null;
    }
}
