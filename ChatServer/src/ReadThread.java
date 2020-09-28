import java.io.*;
import java.net.*;

/**
 * This thread is responsible for reading server's output and printing it
 * to the console.
 * It runs in an infinite loop until the client disconnects from the server.
 *
 * @author Sonu Kumar
 */
public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;

    public ReadThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            reader = new BufferedReader(new InputStreamReader(
                                            socket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
    // while true
        while (socket !=  null) {
            try {
                String response;
                synchronized(socket) {
                    response = reader.readLine();
                }
                if(response == null) break;
                System.out.println("\n" + response);

                // prints the username after displaying the server's message
                if (client.getUserName() != null) {
                    System.out.print("[" + client.getUserName() + "]: ");
                }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
