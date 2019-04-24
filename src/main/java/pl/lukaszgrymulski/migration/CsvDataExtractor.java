package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class CsvDataExtractor {

    public static Client extractClientObject(List<String> lineAsList) {
        Client client = new Client();
        client.setName(lineAsList.get(0));
        client.setSurname(lineAsList.get(1));
        if (lineAsList.size()>2) {
            if (!lineAsList.get(2).equals("")) {
                int age = Integer.valueOf(lineAsList.get(2));
                client.setAge(age);
            }
        }
        return client;
    }

    public static List<Contact> extractContactList(List<String> lineAsList) {
        if (lineAsList.size()<5) return new ArrayList<>();
        List<String> contactValues = new ArrayList<>();
        for (int i=4; i<lineAsList.size(); i++) {
            contactValues.add(lineAsList.get(i));
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
