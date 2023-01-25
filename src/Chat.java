import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class Chat {
    private final LinkedList<Client> clients = new LinkedList<>();

    /**
     * @param socket A socket to interact.
     * @return Returns a new client.
     * @throws IOException On a client creating.
     */
    Client createClient(Socket socket) throws IOException {
        Client client = new Client(this, socket);
        clients.add(client);

        return client;
    }

    /**
     * @param message A string to publish in a chat.
     * @param authorId A message's author.
     */
    void publish(String message, String authorId) {
        for (Client client : clients) {
            if (client.id.toString().equals(authorId)) {
                continue;
            }

            client.printMessage(message);
        }
    }

    /**
     * @param client A client to remove.
     */
    void removeClient(Client client) {
        clients.remove(client);
    }
}
