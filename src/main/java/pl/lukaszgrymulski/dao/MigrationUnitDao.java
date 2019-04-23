package pl.lukaszgrymulski.dao;

import pl.lukaszgrymulski.models.MigrationUnit;

public class MigrationUnitDao {

    ClientDao clientDao = new ClientDao();
    ContactDao contactDao = new ContactDao();

    public void saveMigrationUnit(MigrationUnit migrationUnit) {
        int clientGeneratedId = clientDao.saveClientInDatabase(migrationUnit.getClient());
        migrationUnit.getContactList().stream().forEach(c -> {
            c.setClientId(clientGeneratedId);
            contactDao.saveContactToDB(c);
        });
    }

}
