package pl.lukaszgrymulski.migration;

import org.junit.jupiter.api.Test;
import pl.lukaszgrymulski.models.ContactType;

import static org.junit.jupiter.api.Assertions.*;

class ContactTypeDeterminerTest {

    @Test
    void determineContactTypeIdShouldReturnEmailId() {
        String contact = "test@test.com";
        int id = ContactTypeDeterminer.determineContactTypeIdByValue(contact);
        assertTrue(id == ContactType.EMAIL.getTypeId());
    }

    @Test
    void determineContactTypeIdShouldReturnPhoneId() {
        String contact = "123456789";
        int id = ContactTypeDeterminer.determineContactTypeIdByValue(contact);
        assertTrue(id == ContactType.PHONE.getTypeId());
    }

    @Test
    void determineContactTypeIdShouldReturnJabberId() {
        String contact = "john@doe.pl/resource";
        int id = ContactTypeDeterminer.determineContactTypeIdByValue(contact);
        assertTrue(id == ContactType.JABBER.getTypeId());
    }

    @Test
    void determineContactTypeIdShouldReturnUnknownId() {
        String contact = "john.doe";
        int id = ContactTypeDeterminer.determineContactTypeIdByValue(contact);
        assertTrue(id == ContactType.UNKNOWN.getTypeId());
    }
}