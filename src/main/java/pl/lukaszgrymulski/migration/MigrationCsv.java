package pl.lukaszgrymulski.migration;

import pl.lukaszgrymulski.validators.CsvValidator;

import java.io.*;

public class MigrationCsv {

    public void migrateToDatabase(File file) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (CsvValidator.validateCsvLine(line)) {
                    System.out.println("Lec gou!");
                } else {
                    System.out.println("LIIIPA");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("There was an input-output exception while trying to migrate file");
        }


    }

}
