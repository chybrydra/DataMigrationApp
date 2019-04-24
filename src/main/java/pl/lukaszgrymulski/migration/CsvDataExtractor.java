package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.models.Contact;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        throw new NotImplementedException();
    }



}
