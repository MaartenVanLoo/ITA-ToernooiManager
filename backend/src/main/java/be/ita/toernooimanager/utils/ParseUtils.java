package be.ita.toernooimanager.utils;

import java.util.regex.Pattern;

public class ParseUtils {
    private static final Pattern numeric = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum){
        if (strNum == null) {
            return false;
        }
        return numeric.matcher(strNum).matches();
    }
}
