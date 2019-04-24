package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.models.Contact;
import pl.lukaszgrymulski.models.ContactType;
import pl.lukaszgrymulski.validators.PatternStore;

public class ContactTypeDeterminer {

    public static Integer determineContactTypeIdByValue(String contactValue) {
        if (contactValue.matches(PatternStore.JABBER_PATTERN)){
            return ContactType.JABBER.getTypeId();
        }
        if (contactValue.matches(PatternStore.EMAIL_PATTERN)) {
            return ContactType.EMAIL.getTypeId();
        }
        if (contactValue.matches(PatternStore.PHONE_PATTERN)) {
            return ContactType.PHONE.getTypeId();
        }
        return ContactType.UNKNOWN.getTypeId();
    }

    public static int determineContactTypeIdByXmlTagName(String localName) {
        if (localName.equalsIgnoreCase("email")) {
            return ContactType.EMAIL.getTypeId();
        }
        if (localName.equalsIgnoreCase("phone")) {
            return ContactType.PHONE.getTypeId();
        }
        if (localName.equalsIgnoreCase("jabber")) {
            return ContactType.JABBER.getTypeId();
        }
        return ContactType.UNKNOWN.getTypeId();
    }
}
