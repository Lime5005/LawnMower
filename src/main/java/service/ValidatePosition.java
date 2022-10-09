package service;

public class ValidatePosition {

    public boolean isValidPosition(String position) {
        boolean isValid = false;
        if (isValidMowerPosition(position)) {
            isValid = true;
        }
        return isValid;
    }

    private boolean isValidMowerPosition (String position) {
        return position.matches("^\\s*(\\d+)\\s+(\\d+)\\s+([NESW])\\s*$");
    }
}
