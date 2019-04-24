package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.migration.csv.CsvMigrator;
import pl.lukaszgrymulski.migration.xml.XmlMigrator;

import java.io.File;

public class MigrationFactory {

    public void migrate(File file) {
        if (!file.exists()) {
            System.out.println("File does not exist");
            return;
        }
        if (!file.isFile()) {
            System.out.println("Chosen object is not a file");
            return;
        }

        String fileType = FileTypeDeterminer.determineFileType(file);

        if (fileType.equalsIgnoreCase("csv")){
            CsvMigrator csvMigrator = new CsvMigrator();
            csvMigrator.migrateFileToDatabase(file);
        } else if (fileType.equalsIgnoreCase("xml")) {
            XmlMigrator xmlMigrator = new XmlMigrator();
            xmlMigrator.migrateFileToDatabase(file);
        } else {
            System.out.println("Cannot migrate file of type: " + fileType + ". You can only migrate files of type: xml, csv.");
        }
    }



}
