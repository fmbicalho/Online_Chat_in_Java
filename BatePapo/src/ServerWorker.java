import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerWorker implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private List<ServerWorker> workers;
    private String nickname;

    /*
     * ServerWorker Constructor.
     */
    public ServerWorker(Socket socket, List<ServerWorker> workers) throws IOException {
        this.socket = socket;
        this.workers = workers;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.nickname = "Client" + (workers.size() + 1);
    }

    /*
     * Main run method that handles client messages and commands.
     */
    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readLine();
                if (message == null) {
                    break;
                }
                if (message.startsWith("/exit")) {
                    break;
                } else if (message.startsWith("/list")) {
                    sendClientList();
                } else if (message.startsWith("/nickname")) {
                    changeNickname(message);
                } else if (message.startsWith("/whisper")) {
                    sendWhisper(message);
                } else {
                    Server.broadcast(this, nickname + ": " + message);
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            workers.remove(this);
            Server.broadcast(this, nickname + " has left the chat.");
        }
    }

    /*
     * Sends a list of all connected clients to this client.
     */
    private void sendClientList() {
        StringBuilder clients = new StringBuilder("Connected clients: ");
        for (ServerWorker worker : workers) {
            clients.append(worker.nickname).append(", ");
        }
        out.println(clients);
    }

    /*
     * Changes the nickname of this client.
     */
    private void changeNickname(String message) {
        String[] parts = message.split(" ");
        if (parts.length > 1) {
            String newNickname = parts[1];
            nickname = newNickname;
            out.println("Nickname changed to: " + newNickname);
        } else {
            out.println("Invalid command. Usage: /nickname <new nickname>");
        }
    }

    /*
     * Sends a private message to a specific client.
     */
    private void sendWhisper(String message) {
        String[] parts = message.split(" ", 3);
        if (parts.length > 2) {
            String recipient = parts[1];
            String whisperMessage = parts[2];
            for (ServerWorker worker : workers) {
                if (worker.nickname.equalsIgnoreCase(recipient)) {
                    worker.sendMessage("(whisper) " + nickname + ": " + whisperMessage);
                    return;
                }
            }
            out.println("Client '" + recipient + "' not found.");
        } else {
            out.println("Invalid whisper command. Usage: /whisper <client> <message>");
        }
    }

    /*
     * Sends a message to this client.
     */
    public void sendMessage(String message) {
        out.println(message);
    }
}