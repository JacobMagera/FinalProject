package org.example.util;

public class Util {
    public static String toTitleCase(String strIn) {
        if (strIn == null || strIn.isEmpty()) {
            return strIn;
        }

        String firstLetter1 = strIn.substring(0, 1).toUpperCase();
        String otherLetters1 = strIn.substring(1, strIn.indexOf(" ")).toLowerCase();

        String firstLetter2 = strIn.substring(strIn.indexOf(" ") + 1, strIn.indexOf(" ") + 2).toUpperCase();
        String otherLetters2 = strIn.substring(strIn.indexOf(" ") + 2).toLowerCase();

        return String.format("%s%s %s%s", firstLetter1, otherLetters1, firstLetter2, otherLetters2);
    }
}
