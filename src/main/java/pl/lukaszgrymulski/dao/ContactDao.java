package pl.lukaszgrymulski.dao;

import pl.lukaszgrymulski.dao.schema.ContactSchema;
import pl.lukaszgrymulski.models.Contact;
import pl.lukaszgrymulski.persistence.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDao {

    static String tableName = ContactSchema.getTableName();
    static String idColName = ContactSchema.getIdColName();
    static String customerIdColName = ContactSchema.getCustomerIdColName();
    static String typeColName = ContactSchema.getTypeColName();
    static String contactColName = ContactSchema.getContactColName();


    Connection connection = DatabaseConnection.getConnection();

    public void saveContactToDB(Contact contact) {
        String query = String.format(
            "INSERT INTO %s(%s,%s,%s,%s) VALUES(null,?,?,?)",
            tableName, idColName, customerIdColName, typeColName, contactColName
        );
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, contact.getClientId());
            statement.setInt(2, contact.getContactTypeId());
            statement.setString(3, contact.getContact());
            statement.executeUpdate();

            System.out.printf("Successfully migrated contact: [client: %d, type: %d, contact: %s]\n",
                    contact.getClientId(),
                    contact.getContactTypeId(),
                    contact.getContact()
            );

        } catch (SQLException e) {
            System.out.printf("Could not migrate contact: [client: %d, type: %d, contact: %s]\n",
                    contact.getClientId(),
                    contact.getContactTypeId(),
                    contact.getContact()
            );
        }
    }

    public boolean contactExistsInDB(Contact contact) {
        try {
            String query = String.format(
                    "SELECT %s FROM %s WHERE %s=? AND %s=? AND %s=?",
                    idColName, tableName, customerIdColName, typeColName, contactColName
            );
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, contact.getClientId());
            statement.setInt(2, contact.getContactTypeId());
            statement.setString(3, contact.getContact());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
