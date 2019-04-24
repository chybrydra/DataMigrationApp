package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.models.Contact;
import pl.lukaszgrymulski.models.MigrationUnit;
import pl.lukaszgrymulski.validators.CsvValidator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.List;

public class CsvMigrator {

    public void migrateToDatabase(File file) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";
            String[] lineAsArray = null;
            int lineNumber= 1 ;
            while ((line = reader.readLine()) != null) {
                lineAsArray = line.split(",");
                if (CsvValidator.validateCsvLine(line)) {
                    MigrationUnit unit = new MigrationUnit();
                    unit.setClient(CsvDataExtractor.extractClientObject(lineAsArray));
                    unit.setContactList(CsvDataExtractor.extractContactList(lineAsArray));
                } else {
                    throw new IllegalArgumentException(
                            String.format("Could not migrate csv line %d (%s) as its data is invalid.",
                                    lineNumber,
                                    line)
                    );
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("There was an input-output exception while trying to migrate file");
        }


    }





}
