package pl.lukaszgrymulski.validators;

public class CsvValidator {

    public static boolean validateCsvLine(String line) {
        String[] elements = line.split(",");
        int numberOfElements = elements.length;

        if (numberOfElements < 2) {
            System.err.println("line ( "+ line +") has not enough data");
            return false;
        }

        if (!elements[0].matches("[\\D]+")) {
            System.err.println("Name: " + elements[0] + " should not contain digits!");
            return false;
        }
        if (!elements[1].matches("[\\D]+")) {
            System.err.println("Surname: " + elements[1] + " should not contain digits!");
            return false;
        }
        if (numberOfElements > 2) {
            if (!elements[2].matches("[\\d]*")) {
                System.err.println("Age: " + elements[2] + " must be number or null!");
                return false;
            }
        }
        if (numberOfElements > 3) {
            if (!elements[3].matches("[\\D]+")) {
                System.err.println("City: " + elements[3] + " should not contain digits!");
                return false;
            }
        }

        return true;
    }

}
