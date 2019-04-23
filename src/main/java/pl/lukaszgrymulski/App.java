package pl.lukaszgrymulski;

import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.models.Contact;
import pl.lukaszgrymulski.models.ContactType;

public class App {

    public static void main(String[] args) {
        System.out.println("Hola amigo");
        Client client = new Client(3, "Adam", "Ondra", 25);
        System.out.println(client);
        Contact contact = new Contact(7, 3, ContactType.JABBER, "123123123");
        System.out.println(contact);
    }

}
