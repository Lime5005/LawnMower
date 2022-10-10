package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
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

    @Override
    public String toString() {
        return String.format("%d %d %c", x, y, dir);
    }
}
