package service;

public class ValidateLawn {
    public static boolean isValidLawn(String lawn) {
        boolean isValid = false;
        if (isValidLawnPattern(lawn)) {
            String[] split = lawn.split("\\s+");
            int xMax = Integer.parseInt(split[0]);
            int yMax = Integer.parseInt(split[1]);
            if (xMax > 0 && yMax > 0) {
                isValid = true;
            }
        }
        return isValid;
    }

    private static boolean isValidLawnPattern(String lawn) {
        return lawn.matches("^\\s*(\\d+)\\s+(\\d+)\\s*$");
    }
}
