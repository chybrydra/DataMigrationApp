package pl.lukaszgrymulski.dao;

import pl.lukaszgrymulski.models.Client;
import pl.lukaszgrymulski.persistence.DatabaseConnection;

import java.sql.*;

public class ClientDao {

    Connection connection = DatabaseConnection.getConnection();

    public Integer findUserInDatabase(Client client) {
        Integer clientId = null;
        try {
            String query = null;
            PreparedStatement statement = null;

            if (client.getAge() != null) {
                query = "SELECT ID FROM CLIENTS WHERE NAME=? AND SURNAME=? AND AGE=?";
                statement = connection.prepareStatement(query);
                statement.setString(1, client.getName());
                statement.setString(2, client.getSurname());
                statement.setInt(3, client.getAge());
            } else {
                query = "SELECT ID FROM CLIENTS WHERE NAME=? AND SURNAME=? AND AGE IS NULL";
                statement = connection.prepareStatement(query);
                statement.setString(1, client.getName());
                statement.setString(2, client.getSurname());
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                clientId = resultSet.getInt("ID");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientId;
    }

    public int saveClientInDatabase(Client client) {
        Integer justMigratedClientId = null;
        try {
            String query = "INSERT INTO CLIENTS(ID, NAME, SURNAME, AGE) VALUES (null, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            if (client.getAge() != null) {
                statement.setInt(3, client.getAge());
            } else {
                statement.setNull(3, Types.INTEGER);
            }
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

            System.out.printf("Successfully migrated: [%d. %s %s (%d yr old)]\n",
                    justMigratedClientId,
                    client.getName(),
                    client.getSurname(),
                    client.getAge()
            );

        } catch (SQLException e) {
            System.out.printf("Could not migrate client: [%s %s (%d yr old)]\n",
                    client.getName(),
                    client.getSurname(),
                    client.getAge()
            );
        }

        return justMigratedClientId;
    }

}
