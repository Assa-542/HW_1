package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController implements Server {
    private final ServerGui gui;
    private final ServerRepository repository;
    private boolean running = false;
    private ServerSocket serverSocket;

    public ServerController(ServerGui gui, ServerRepository repository) {
        this.gui = gui;
        this.repository = repository;
    }

    @Override
    public void start() {
        try {
            serverSocket = new ServerSocket(12345);
            running = true;
            gui.showMessage("Server started.");

            while (running) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, repository, gui).start();
            }
        } catch (IOException e) {
            gui.showMessage("Server failed to start: " + e.getMessage());
        }
    }

    @Override
    public void stop() {
        try {
            running = false;
            if (serverSocket != null) {
                serverSocket.close();
            }
            gui.showMessage("Server stopped.");
        } catch (IOException e) {
            gui.showMessage("Failed to stop server: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;
        private final ServerRepository repository;
        private final ServerGui gui;

        public ClientHandler(Socket clientSocket, ServerRepository repository, ServerGui gui) {
            this.clientSocket = clientSocket;
            this.repository = repository;
            this.gui = gui;
        }

        @Override
        public void run() {
            try (
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                String message;
                while ((message = in.readLine()) != null) {
                    repository.saveMessage("Client: " + message);
                    gui.showMessage("Client: " + message);
                    out.println("Echo: " + message);
                }
            } catch (IOException e) {
                gui.showMessage("Connection error: " + e.getMessage());
            }
        }
    }
}
