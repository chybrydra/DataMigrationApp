package pl.lukaszgrymulski.models;

import java.util.Objects;

public class Contact {

    Integer id;
    Integer clientId;
    Integer contactTypeId;
    String contact;

    public Contact() {
    }

    public Contact(Integer id, Integer clientId, Integer contactTypeId, String contact) {
        this.id = id;
        this.clientId = clientId;
        this.contactTypeId = contactTypeId;
        this.contact = contact;
    }

    public Contact(Integer id, Integer clientId, ContactType contactType, String contact) {
        this.id = id;
        this.clientId = clientId;
        this.contactTypeId = contactType.getTypeId();
        this.contact = contact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(Integer contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public void setContactTypeId(ContactType contactType) {
        this.contactTypeId = contactType.getTypeId();
    }

    public String getContact() {
        return contact;
    }

    public String setContact(String contact) {
        return this.contact = contact;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", contactType=" + contactTypeId +
                ", contact='" + contact + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact1 = (Contact) o;
        return Objects.equals(id, contact1.id) &&
                Objects.equals(clientId, contact1.clientId) &&
                Objects.equals(contactTypeId, contact1.contactTypeId) &&
                Objects.equals(contact, contact1.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, contactTypeId, contact);
    }
}
