package pl.lukaszgrymulski;

import pl.lukaszgrymulski.migration.MigrationFactory;

import java.io.File;

public class App {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\ŁUKASZ\\Desktop\\dane-osoby.xml");
        MigrationFactory factory = new MigrationFactory();
        factory.migrate(file);
    }

}
