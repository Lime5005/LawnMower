package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Position {
    private int x;
    private int y;
    private char dir;

    public Position(int x, int y, char dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Orientation getOrientation(char dir) {
        for (Orientation orientation : Orientation.values()) {
            if (dir == orientation.getOri()) {
                return orientation;
            }
        }
        return null;
    }
}
