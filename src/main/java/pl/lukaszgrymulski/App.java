package pl.lukaszgrymulski;


import pl.lukaszgrymulski.dao.ClientDao;

public class App {

    public static void main(String[] args) {
        ClientDao dao = new ClientDao();
        dao.saveClientInDatabase();
    }

}
