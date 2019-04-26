package pl.lukaszgrymulski.dao.schema;

import pl.lukaszgrymulski.persistence.DBConfigurationFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ContactSchema {

    static String tableName = null;
    static String idColName = null;
    static String customerIdColName = null;
    static String typeColName = null;
    static String contactColName = null;

    private static void loadData() {
        String path = DBConfigurationFile.getDbConfigFilePath();
        File configFile = new File(path);
        try (InputStream input = new FileInputStream(configFile)) {
            Properties props = new Properties();
            props.load(input);
            tableName = props.getProperty("contact.table.name");
            idColName = props.getProperty("contact.table.col.id");
            customerIdColName = props.getProperty("contact.table.col.customerid");
            typeColName = props.getProperty("contact.table.col.type");
            contactColName = props.getProperty("contact.table.col.contact");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getTableName() {
        if (tableName==null) loadData();
        return tableName;
    }

    public static String getIdColName() {
        if (idColName==null) loadData();
        return idColName;
    }

    public static String getCustomerIdColName() {
        if (customerIdColName==null) loadData();
        return customerIdColName;
    }

    public static String getTypeColName() {
        if (typeColName==null) loadData();
        return typeColName;
    }

    public static String getContactColName() {
        if (contactColName==null) loadData();
        return contactColName;
    }
}
