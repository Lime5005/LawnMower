package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Position {
    private int x;
    private int y;
    private char orientation;

    public Position(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return String.format("%d %d %c", x, y, orientation);
    }
}
