package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class CsvDataExtractor {

    public static Client extractClientObject(String[] lineAsArray) {
        Client client = new Client();
        client.setName(lineAsArray[0]);
        client.setSurname(lineAsArray[1]);
        if (lineAsArray.length>2) {
            if (!lineAsArray[2].equals("")) {
                int age = Integer.valueOf(lineAsArray[2]);
                client.setAge(age);
            }
        }

        return client;
    }

    public static List<Contact> extractContactList(String[] lineAsArray) {
        if (lineAsArray.length<5) return new ArrayList<>();
        List<String> contactValues = new ArrayList<>();
        for (int i=4; i<lineAsArray.length; i++) {
            contactValues.add(lineAsArray[i]);
        }

        List<Contact> contacts = new ArrayList<>();

        for(String contactValue : contactValues) {
            contacts.add(new Contact(
               null,
               null,
               ContactTypeDeterminer.determineContactTypeId(contactValue),
                contactValue
            ));
        }

        return contacts;
    }




}
