package pl.lukaszgrymulski.migration;

import org.junit.jupiter.api.Test;
import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.models.Contact;
import pl.lukaszgrymulski.models.ContactType;

import java.util.Arrays;
import java.util.List;

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
        List<String> csvLineAsList = Arrays.asList(name, surname, age, city, contact1, contact2);
        Client client = new Client(null, name, surname, Integer.valueOf(age));
        assertEquals(client, CsvDataExtractor.extractClientObject(csvLineAsList));
    }

    @Test
    void extractClientObjectShouldReturnExpectedObjectIfAgeIsEmpty() {
        String name = "John";
        String surname = "Doe";
        String age = "";
        List<String> csvLineAsList = Arrays.asList(name, surname, age);
        Client client = new Client(null, name, surname, null);
        assertEquals(client, CsvDataExtractor.extractClientObject(csvLineAsList));
    }

    @Test
    void extractClientObjectShouldReturnExpectedObjectIfMinimumDataInArray() {
        String name = "John";
        String surname = "Doe";
        List<String> csvLineAsList = Arrays.asList(name, surname);
        Client client = new Client(null, name, surname, null);
        assertEquals(client, CsvDataExtractor.extractClientObject(csvLineAsList));
    }

    @Test
    void extractContactListShouldEqualIfAllDataIsValid() {
        String email = "test@test.com";
        Contact contact1 = new Contact(null, null, ContactType.EMAIL, email);
        String phone = "123456789";
        Contact contact2 = new Contact(null, null, ContactType.PHONE, phone);
        String jabber = "jbr";
        Contact contact3 = new Contact(null, null, ContactType.JABBER, jabber);
        String unknown = "john.doe";
        Contact contact4 = new Contact(null, null, ContactType.UNKNOWN, unknown);

        List<Contact> contacts = Arrays.asList(contact1, contact2, contact3, contact4);
        List<String> contactsList = Arrays.asList("name", "surname", "age", "city", email, phone, jabber, unknown);

        assertEquals(contacts, CsvDataExtractor.extractContactList(contactsList));
    }
}