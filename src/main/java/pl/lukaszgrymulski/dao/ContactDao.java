package pl.lukaszgrymulski.dao;

import pl.lukaszgrymulski.models.Contact;
import pl.lukaszgrymulski.persistence.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDao {

    Connection connection = DatabaseConnection.getConnection();

    public void saveContactToDB(Contact contact) {
        String sql = "INSERT INTO CONTACTS(ID, ID_CUSTOMER, TYPE, CONTACT) VALUES(null,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
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
            String query = "SELECT ID FROM CONTACTS WHERE ID_CUSTOMER=? AND TYPE=? AND CONTACT=?";
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
