package service;

public class ValidatePosition {

    public static boolean isValidPosition(String position) {
        return position.matches("^\\s*(\\d+)\\s+(\\d+)\\s+([NESW])\\s*$");
    }
}
