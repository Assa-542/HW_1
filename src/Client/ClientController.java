package Client;

import java.io.*;
import java.net.Socket;

public class ClientController implements Client {
    private final ClientGui gui;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientController(ClientGui gui) {
        this.gui = gui;
    }

    @Override
    public void connect() {
        try {
            socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            loadChatHistory();
            gui.showMessage("Connected to server.");

            new Thread(this::listenForMessages).start();
        } catch (IOException e) {
            gui.showMessage("Connection failed: " + e.getMessage());
        }
    }

    @Override
    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
                gui.showMessage("Disconnected from server.");
            }
        } catch (IOException e) {
            gui.showMessage("Disconnection failed: " + e.getMessage());
        }
    }

    @Override
    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
            gui.showMessage("You: " + message);
            saveChatHistory("You: " + message + "\n");
        }
    }

    private void listenForMessages() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                gui.showMessage("Server: " + message);
                saveChatHistory("Server: " + message + "\n");
            }
        } catch (IOException e) {
            gui.showMessage("Error reading from server: " + e.getMessage());
        }
    }

    private void loadChatHistory() {
        try (BufferedReader logReader = new BufferedReader(new FileReader("chat_log.txt"))) {
            String line;
            while ((line = logReader.readLine()) != null) {
                gui.showMessage(line);
            }
        } catch (IOException e) {
            gui.showMessage("No chat history found.");
        }
    }

    private void saveChatHistory(String message) {
        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter("chat_log.txt", true))) {
            logWriter.write(message);
        } catch (IOException e) {
            gui.showMessage("Failed to save chat history: " + e.getMessage());
        }
    }
}
