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

    public Instruction getDir(char c) {
        for (Instruction instruction : Instruction.values()) {
            if (instruction.dir == c) {
                return instruction;
            }
        }
        return null;
    }
}
