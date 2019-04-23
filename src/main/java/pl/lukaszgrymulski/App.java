package pl.lukaszgrymulski;


import pl.lukaszgrymulski.dao.ClientDao;
import pl.lukaszgrymulski.models.Client;

public class App {

    public static void main(String[] args) {
        ClientDao dao = new ClientDao();
        Client client = new Client(3, "Adam", "Ondra", 25);
        int id = dao.saveClientInDatabase(client);
        System.out.println(id);
    }

}
