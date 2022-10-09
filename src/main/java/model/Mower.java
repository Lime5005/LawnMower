package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Mower {
    private Lawn lawn;
    private Position position;

    public Mower(Lawn lawn, Position position) {
        this.lawn = lawn;
        this.position = position;
    }
}
