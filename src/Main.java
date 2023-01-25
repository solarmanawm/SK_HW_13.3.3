import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    /**
     * @param args Main arguments
     */
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(1234)) {
            Chat chat = new Chat();

            while (true) {
                Socket socket = server.accept();
                Client client = chat.createClient(socket);
                Thread thread = new Thread(client);
                thread.start();
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}