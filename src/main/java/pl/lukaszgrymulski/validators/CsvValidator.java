package pl.lukaszgrymulski.validators;

import java.util.List;

public class CsvValidator {

    public static boolean validateCsvLine(List<String> lineAsList) {
        int numberOfElements = lineAsList.size();

        if (numberOfElements < 2) {
            System.err.println("line ( "+ lineAsList +") has not enough data");
            return false;
        }

        if (!lineAsList.get(0).matches("[\\D]+")) {
            System.err.println("Name: " + lineAsList.get(0) + " should not contain digits!");
            return false;
        }
        if (!lineAsList.get(1).matches("[\\D]+")) {
            System.err.println("Surname: " + lineAsList.get(1) + " should not contain digits!");
            return false;
        }
        if (numberOfElements > 2) {
            if (!lineAsList.get(2).matches("[\\d]*")) {
                System.err.println("Age: " + lineAsList.get(2) + " must be number or null!");
                return false;
            }
        }
        if (numberOfElements > 3) {
            if (!lineAsList.get(3).matches("[\\D]+")) {
                System.err.println("City: " + lineAsList.get(3) + " should not contain digits!");
                return false;
            }
        }

        return true;
    }

}
