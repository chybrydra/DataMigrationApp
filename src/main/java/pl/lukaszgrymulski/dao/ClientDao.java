package pl.lukaszgrymulski.dao;

import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.persistence.DatabaseConnection;

import java.sql.*;

public class ClientDao {

    public int saveClientInDatabase(Client client) {
        Connection connection = DatabaseConnection.getConnection();
        Integer justMigratedClientId = null;

        try {
            String query = "INSERT INTO CLIENTS(ID, NAME, SURNAME, AGE) VALUES (null, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating client ("+client+") failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                justMigratedClientId =  (int) generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating client ("+client+") failed, no ID obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Gitara!");
        return justMigratedClientId;
    }

}
