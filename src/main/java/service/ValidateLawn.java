package service;

public class ValidateLawn {
    public boolean checkLawn(String lawnStr) {
        boolean isValid = false;
        if (isValidLawnPattern(lawnStr)) {
            String[] split = lawnStr.split("\\s+");
            int xMax = Integer.parseInt(split[0]);
            int yMax = Integer.parseInt(split[1]);
            if (xMax > 0 && yMax > 0) {
                isValid = true;
            }
        }
        return isValid;
    }

    private boolean isValidLawnPattern(String str) {
        return str.matches("^\\s{0,}(\\d+)\\s+(\\d+)\\s{0,}$");
    }
}
