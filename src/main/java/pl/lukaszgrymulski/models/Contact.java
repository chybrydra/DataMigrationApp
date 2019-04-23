package pl.lukaszgrymulski.models;

public class Contact {

    Integer id;
    Integer customerId;
    Integer contactTypeId;
    String contact;

    public Contact() {
    }

    public Contact(Integer id, Integer customerId, ContactType contactType, String contact) {
        this.id = id;
        this.customerId = customerId;
        this.contactTypeId = contactType.getTypeId();
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(int contactTypeId) {
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
                ", customerId=" + customerId +
                ", contactType=" + contactTypeId +
                ", contact='" + contact + '\'' +
                '}';
    }
}
