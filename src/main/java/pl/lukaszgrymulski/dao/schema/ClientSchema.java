package pl.lukaszgrymulski.dao.schema;

import pl.lukaszgrymulski.persistence.DBConfigurationFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientSchema {

    static String tableName = null;
    static String idColName = null;
    static String nameColName = null;
    static String surnameColName = null;
    static String ageColName = null;

    private static void loadData() {
        String path = DBConfigurationFile.getDbConfigFilePath();
        File configFile = new File(path);
        try (InputStream input = new FileInputStream(configFile)) {
            Properties props = new Properties();
            props.load(input);
            tableName = props.getProperty("customer.table");
            idColName = props.getProperty("customer.table.col.id");
            nameColName = props.getProperty("customer.table.col.name");
            surnameColName = props.getProperty("customer.table.col.surname");
            ageColName = props.getProperty("customer.table.col.age");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getTableName() {
        if (tableName==null) loadData();
        return tableName;
    }

    public static String getIdColName() {
        if (idColName ==null) loadData();
        return idColName;
    }

    public static String getNameColName() {
        if (nameColName ==null) loadData();
        return nameColName;
    }

    public static String getSurnameColName() {
        if (surnameColName ==null) loadData();
        return surnameColName;
    }

    public static String getAgeColName() {
        if (ageColName ==null) loadData();
        return ageColName;
    }
}
