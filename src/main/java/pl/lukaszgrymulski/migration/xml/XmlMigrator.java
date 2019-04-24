package pl.lukaszgrymulski.migration.xml;

import pl.lukaszgrymulski.dao.MigrationUnitDao;
import pl.lukaszgrymulski.migration.ContactTypeDeterminer;
import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.models.Contact;
import pl.lukaszgrymulski.models.ContactType;
import pl.lukaszgrymulski.models.MigrationUnit;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlMigrator {

    public void migrateFileToDatabase(File file) {
        MigrationUnit migrationUnit = null;
        Client client = null;
        List<Contact> contactList = null;
        Contact contact = null;
        String text = null;
        boolean insideContacts = false;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);


            while(reader.hasNext()) {
                int event = reader.next();

                switch(event){
                    case XMLStreamConstants.START_ELEMENT: {
                        if ("person".equals(reader.getLocalName())) {
                            migrationUnit = new MigrationUnit();
                            client = new Client();
                        }
                        if ("contacts".equals(reader.getLocalName())) {
                            contactList = new ArrayList<Contact>();
                            insideContacts = true;
                        }
                        break;
                    }
                    case XMLStreamConstants.CHARACTERS: {
                        text = reader.getText().trim();
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        switch (reader.getLocalName()) {
                            case "person": {
                                migrationUnit.setClient(client);
                                migrationUnit.setContactList(contactList);
                                migrateToDatabase(migrationUnit);
                            }
                            case "name": {
                                client.setName(text);
                                break;
                            }
                            case "surname": {
                                client.setSurname(text);
                                break;
                            }
                            case "age": {
                                client.setAge(Integer.valueOf(text));
                                break;
                            }
                            case "city": {
                                break;
                            }
                            case "contacts": {
                                insideContacts = false;
                                break;
                            }
                            default: {
                                if (insideContacts) {
                                    contact = new Contact();
                                    String currentTag = reader.getLocalName();
                                    int contactTypeId = ContactTypeDeterminer
                                            .determineContactTypeIdByXmlTagName(currentTag);
                                    contact.setContactTypeId(contactTypeId);
                                    if (contactTypeId == ContactType.UNKNOWN.getTypeId()) {
                                        contact.setContact("<" + reader.getLocalName() + ">" + text);
                                    } else {
                                        contact.setContact(text);
                                    }
                                    contactList.add(contact);
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    private void migrateToDatabase(MigrationUnit migrationUnit) {
        MigrationUnitDao migrationUnitDao = new MigrationUnitDao();
        migrationUnitDao.saveMigrationUnit(migrationUnit);
    }

}
