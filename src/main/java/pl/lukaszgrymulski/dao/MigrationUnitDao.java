package pl.lukaszgrymulski.dao;

import pl.lukaszgrymulski.models.MigrationUnit;

public class MigrationUnitDao {

    ClientDao clientDao = new ClientDao();
    ContactDao contactDao = new ContactDao();

    public void saveMigrationUnit(MigrationUnit migrationUnit) {
        Integer clientGeneratedId = null;
        clientGeneratedId = clientDao.findUserInDatabase(migrationUnit.getClient());

        if (clientGeneratedId == null) {
            clientGeneratedId = clientDao.saveClientInDatabase(migrationUnit.getClient());
        }

        Integer finalClientGeneratedId = clientGeneratedId;
        migrationUnit.getContactList().stream().forEach(c -> {
            c.setClientId(finalClientGeneratedId);
            if (!contactDao.contactExistsInDB(c)) {
                contactDao.saveContactToDB(c);
            }
        });
    }

}
