package service;

public class ValidateOrientation {

    public static boolean isValidOrientation(String orientation) {
        return orientation.matches("^[GDA]+$");
    }
}
