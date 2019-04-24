package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.dao.MigrationUnitDao;
import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.models.Contact;
import pl.lukaszgrymulski.models.MigrationUnit;
import pl.lukaszgrymulski.validators.CsvValidator;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CsvMigrator {

    MigrationUnitDao migrationUnitDao;

    public CsvMigrator() {
        migrationUnitDao = new MigrationUnitDao();
    }

    public void migrateFileToDatabase(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                List<String> lineAsList = Arrays.asList(line.replace(" ", "").split(","));
                if (CsvValidator.validateCsvLine(lineAsList)) {
                    executeCsvLineMigration(lineAsList);
                } else {
                    System.err.println(
                            String.format("Could not migrate csv line (%s) as its data is invalid.", line)
                    );
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("There was an input-output exception while trying to migrate file");
        }
    }

    private void executeCsvLineMigration(List<String> lineAsList) {
        MigrationUnit migrationUnit = new MigrationUnit();
        Client client = CsvDataExtractor.extractClientObject(lineAsList);
        List<Contact> contactList = CsvDataExtractor.extractContactList(lineAsList);

        migrationUnit.setClient(client);
        migrationUnit.setContactList(contactList);

        migrationUnitDao.saveMigrationUnit(migrationUnit);
    }

}
