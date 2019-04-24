package pl.lukaszgrymulski;


import pl.lukaszgrymulski.ui.UserView;

public class App {

    public static void main(String[] args) {
//        File file = new File("C:\\Users\\ŁUKASZ\\Desktop\\dane-osoby.xml");
//            C:\Users\ŁUKASZ\Desktop\dane-osoby.xml
//        MigrationFactory factory = new MigrationFactory();
//        factory.migrate(file);

        UserView userView = new UserView();
        userView.startUserInferface();

    }

}
