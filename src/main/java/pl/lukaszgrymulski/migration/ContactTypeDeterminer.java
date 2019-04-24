package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.models.ContactType;
import pl.lukaszgrymulski.validators.PatternStore;

public class ContactTypeDeterminer {

    public static Integer determineContactTypeId(String contactValue) {
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

}
