import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class Client implements Runnable  {
    private final Chat chat;
    private final Socket socket;
    private final Scanner in;
    private final PrintStream out;
    final UUID id = UUID.randomUUID();

    /**
     * @param chat A chat to for a communication.
     * @param socket A socket to interact.
     * @throws IOException On an input/output stream creating.
     */
    public Client(Chat chat, Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        this.chat = chat;
        this.socket = socket;
        this.in = new Scanner(is);
        this.out = new PrintStream(os);
    }

    /**
     * @param message A message to print in a chat.
     */
    void printMessage(String message) {
        this.out.println(message);
    }

    public void run() {
        try {
            String input = in.nextLine();

            while (!input.equals("bye")) {
                chat.publish(input, this.id.toString());
                input = in.nextLine();
            }

            chat.removeClient(this);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
