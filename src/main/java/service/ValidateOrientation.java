package service;

public class ValidateOrientation {

    public boolean checkOrientation(String orientationStr) {
        boolean isValid = false;
        if (isValidOrientation(orientationStr)) {
            isValid = true;
        }
        return isValid;
    }

    private boolean isValidOrientation (String str) {
        return str.matches("^[GDA]+$");
    }
}
