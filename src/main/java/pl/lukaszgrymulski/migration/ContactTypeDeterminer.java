package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.models.ContactType;

public class ContactTypeDeterminer {

    public static Integer determineContactTypeId(String contactValue) {
        if (contactValue.matches("^.+[@].+[\\.].+$")) {
            return ContactType.EMAIL.getTypeId();
        }
        if (contactValue.matches("^\\d{9}$")) {
            return ContactType.PHONE.getTypeId();
        }
        if (contactValue.equals("jbr")){
            return ContactType.JABBER.getTypeId();
        }
        return ContactType.UNKNOWN.getTypeId();
    }

}
