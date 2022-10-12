package model;

import lombok.ToString;

@ToString
public enum Instruction {
    RIGHT('D'),
    LEFT('G'),
    FORWARD('A');

    private final char direction;

    Instruction(char direction) {
        this.direction = direction;
    }

    public static Instruction getDirection(char c) {
        for (Instruction value : Instruction.values()) {
            if (value.direction == c) {
                return value;
            }
        }
        return null;
    }
}
