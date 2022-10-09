package model;

import lombok.ToString;

@ToString
public enum Instruction {
    RIGHT('D'),
    LEFT('G'),
    FORWARD('A');

    private final char dir;

    Instruction(char dir) {
        this.dir = dir;
    }
}
