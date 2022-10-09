package service;

public class ValidateOrientation {

    public boolean isValidOrientation(String orientation) {
        boolean isValid = false;
        if (isValidMowerOrientation(orientation)) {
            isValid = true;
        }
        return isValid;
    }

    private boolean isValidMowerOrientation (String orientation) {
        return orientation.matches("^[GDA]+$");
    }
}
