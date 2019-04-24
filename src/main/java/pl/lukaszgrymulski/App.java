package pl.lukaszgrymulski;


import pl.lukaszgrymulski.dao.ClientDao;
import pl.lukaszgrymulski.models.Client;

public class App {

    public static void main(String[] args) {
        System.out.println("hola amigo");
        Client client = new Client(null, "Tony", "Montana", null);
        ClientDao clientDao = new ClientDao();

        clientDao.saveClientInDatabase(client);
    }

}
