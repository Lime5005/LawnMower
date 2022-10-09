package model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Lawn {
    private final int xMax;
    private final int yMax;

    public Lawn(int xMax, int yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
    }
}
