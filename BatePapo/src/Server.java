import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static List<ServerWorker> workers = new ArrayList<>();

    /*
     * Broadcasts a message from a sender to all connected clients.
     */
    public static void broadcast(ServerWorker sender, String message) {
        for (ServerWorker worker : workers) {
            if (worker != sender) {
                worker.sendMessage(message);
            }
        }
    }

    /*
     * Main method to start the chat server and handle client connections.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter port number: ");
        int portNumber = Integer.parseInt(reader.readLine());

        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Server is running on port " + portNumber);

        ExecutorService pool = Executors.newCachedThreadPool();

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected.");

                ServerWorker worker = new ServerWorker(clientSocket, workers);
                workers.add(worker);
                pool.submit(worker);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error accepting client connection", e);
        }
    }
}