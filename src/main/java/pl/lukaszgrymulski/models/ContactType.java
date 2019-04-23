package pl.lukaszgrymulski.models;

public enum ContactType {

    UNKNOWN(0), EMAIL(1), PHONE(2), JABBER(3);

    private int typeId;

    ContactType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
