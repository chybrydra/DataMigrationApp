package pl.lukaszgrymulski.validators;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvValidatorTest {

    @Test
    void validateCsvLineShouldReturnTrueWithContacts() {
        String line = "Kondor,Nowak,12,Lublin, 123334231, mail@mail.com, jbr";
        List<String> lineAsList = transformCsvLineToList(line);
        assertTrue(CsvValidator.validateCsvLine(lineAsList));
    }

    @Test
    void validateCsvLineShouldReturnTrueWithoutAnyContact() {
        String line = "Kondor,Nowak,12,Lublin";
        List<String> lineAsList = transformCsvLineToList(line);
        assertTrue(CsvValidator.validateCsvLine(lineAsList));
    }

    @Test
    void validateCsvLineShouldReturnTrueWithEmptyAge() {
        String line = "Kondor,Nowak,,Lublin";
        List<String> lineAsList = transformCsvLineToList(line);
        assertTrue(CsvValidator.validateCsvLine(lineAsList));
    }

    @Test
    void validateCsvLineShouldReturnFalseIfNameContainsDigits() {
        String line = "Kondor1,Nowak,12,Lublin,66666666,kondor@gmail.com,12333,jbr";
        List<String> lineAsList = transformCsvLineToList(line);
        assertFalse(CsvValidator.validateCsvLine(lineAsList));
    }

    @Test
    void validateCsvLineShouldReturnFalseIfSurnameContainsDigits() {
        String line = "Kondor,Now2ak,12,Lublin,66666666,kondor@gmail.com,12333,jbr";
        List<String> lineAsList = transformCsvLineToList(line);
        assertFalse(CsvValidator.validateCsvLine(lineAsList));
    }

    @Test
    void validateCsvLineShouldReturnFalseIfAgeIsNeitherEmptyNorNumeric() {
        String line = "Kondor,Nowak,12a,Lublin,66666666,kondor@gmail.com,12333,jbr";
        List<String> lineAsList = transformCsvLineToList(line);
        assertFalse(CsvValidator.validateCsvLine(lineAsList));
    }

    @Test
    void validateCsvLineShouldReturnTrueWithMinimumData() {
        String line = "Kondor,Nowak";
        List<String> lineAsList = transformCsvLineToList(line);
        assertTrue(CsvValidator.validateCsvLine(lineAsList));
    }

    @Test
    void validateCsvLineShouldReturnFalseIfNotEnoughData() {
        String line = "Kondor";
        List<String> lineAsList = transformCsvLineToList(line);
        assertFalse(CsvValidator.validateCsvLine(lineAsList));
    }

    private List<String> transformCsvLineToList(String line) {
        return Arrays.asList(line.split(","));
    }
}