package pl.lukaszgrymulski;

import pl.lukaszgrymulski.migration.CsvMigrator;

import java.io.File;

public class App {

    public static void main(String[] args) {
        CsvMigrator migrator = new CsvMigrator();
        File file = new File("C:\\Users\\ŁUKASZ\\Desktop\\dane-osoby.txt");
        migrator.migrateFileToDatabase(file);
    }

}
