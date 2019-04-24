package pl.lukaszgrymulski.models;

import java.util.List;

public class MigrationUnit {

    private Client client;
    private List<Contact> contactList;

    public MigrationUnit() {
    }

    public MigrationUnit(Client client, List<Contact> contactList) {
        this.client = client;
        this.contactList = contactList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return "MigrationUnit{" +
                "client=" + client +
                ", contactList=" + contactList +
                '}';
    }
}
