package pl.lukaszgrymulski.validators;

import com.sun.media.sound.InvalidDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CsvValidatorTest {

    @Test
    void validateCsvLineShouldReturnTrueWithContacts() throws InvalidDataException {
        String line = "Kondor,Nowak,12,Lublin, 123334231, mail@mail.com, jbr";
        assertTrue(CsvValidator.validateCsvLine(line));
    }

    @Test
    void validateCsvLineShouldReturnTrueWithoutAnyContact() throws InvalidDataException {
        String line = "Kondor,Nowak,12,Lublin";
        assertTrue(CsvValidator.validateCsvLine(line));
    }

    @Test
    void validateCsvLineShouldReturnTrueWithEmptyAge() {
        String line = "Kondor,Nowak,,Lublin";
        assertTrue(CsvValidator.validateCsvLine(line));
    }

    @Test
    void validateCsvLineShouldReturnFalseIfNameContainsDigits() {
        String line = "Kondor1,Nowak,12,Lublin,66666666,kondor@gmail.com,12333,jbr";
        assertFalse(CsvValidator.validateCsvLine(line));
    }

    @Test
    void validateCsvLineShouldReturnFalseIfSurnameContainsDigits() {
        String line = "Kondor,Now2ak,12,Lublin,66666666,kondor@gmail.com,12333,jbr";
        assertFalse(CsvValidator.validateCsvLine(line));
    }

    @Test
    void validateCsvLineShouldReturnFalseIfAgeIsNeitherEmptyNorNumeric() {
        String line = "Kondor,Nowak,12a,Lublin,66666666,kondor@gmail.com,12333,jbr";
        assertFalse(CsvValidator.validateCsvLine(line));
    }

    @Test
    void validateCsvLineShouldReturnTrueWithMinimumData() {
        String line = "Kondor,Nowak";
        assertTrue(CsvValidator.validateCsvLine(line));
    }

    @Test
    void validateCsvLineShouldReturnFalseIfNotEnoughData() {
        String line = "Kondor";
        assertFalse(CsvValidator.validateCsvLine(line));
    }
}