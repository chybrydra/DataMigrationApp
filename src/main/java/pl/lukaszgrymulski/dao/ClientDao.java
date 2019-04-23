package pl.lukaszgrymulski.dao;

import pl.lukaszgrymulski.persistence.DatabaseConnection;

import java.sql.Connection;

public class ClientDao {



    public void saveClientInDatabase() {
        Connection connection = DatabaseConnection.getConnection();
        System.out.println("Gitara!");

    }

}
