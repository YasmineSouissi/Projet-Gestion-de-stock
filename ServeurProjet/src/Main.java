import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //DemandeServer ds = new DemandeServer();
        try {
            Serveur.Server server = new Serveur.Server(1234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
/*public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        DemandeServer ds = new DemandeServer();
    }
}*/