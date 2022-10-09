package service;

public class ValidatePosition {

    public boolean checkPosition(String positionStr) {
        boolean isValid = false;
        if (isValidMowerPosition(positionStr)) {
            isValid = true;
        }
        return isValid;
    }

    private boolean isValidMowerPosition (String str) {
        return str.matches("^\\s{0,}(\\d+)\\s+(\\d+)\\s+([NESW])\\s{0,}$");
    }
}
