package pl.lukaszgrymulski.migration;

import org.junit.jupiter.api.Test;
import pl.lukaszgrymulski.models.Client;

import static org.junit.jupiter.api.Assertions.*;

class CsvDataExtractorTest {

    @Test
    void extractClientObjectShouldReturnExpectedClientObject() {
        String name = "John";
        String surname = "Doe";
        String age = "24";
        String city = "Ohio";
        String contact1 = "123123123";
        String contact2 = "a@a.com";
        String[] csvLineAsArray = {name, surname, age, city, contact1, contact2};
        Client client = new Client(null, name, surname, Integer.valueOf(age));
        assertEquals(client, CsvDataExtractor.extractClientObject(csvLineAsArray));
    }

    @Test
    void extractClientObjectShouldReturnExpectedObjectIfAgeIsEmpty() {
        String name = "John";
        String surname = "Doe";
        String age = "";
        String[] csvLineAsArray = {name, surname, age};
        Client client = new Client(null, name, surname, null);
        assertEquals(client, CsvDataExtractor.extractClientObject(csvLineAsArray));
    }

    @Test
    void extractClientObjectShouldReturnExpectedObjectIfMinimumDataInArray() {
        String name = "John";
        String surname = "Doe";
        String[] csvLineAsArray = {name, surname};
        Client client = new Client(null, name, surname, null);
        assertEquals(client, CsvDataExtractor.extractClientObject(csvLineAsArray));
    }



    @Test
    void extractContactList() {
    }
}