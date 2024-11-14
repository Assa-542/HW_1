package Client;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;
    private static final String LOG_FILE = "chat_log.txt";

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private JTextArea chatHistory;
    private JTextField messageField;

    public ChatClient() {
        JFrame frame = new JFrame("Chat Client");
        chatHistory = new JTextArea(20, 50);
        chatHistory.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatHistory);
        messageField = new JTextField(40);
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(this::sendMessage);
        messageField.addActionListener(this::sendMessage);

        JPanel panel = new JPanel();
        panel.add(messageField);
        panel.add(sendButton);
        frame.getContentPane().add(scrollPane, "Center");
        frame.getContentPane().add(panel, "South");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            socket = new Socket(HOST, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            loadChatHistory();
            new Thread(this::listenForMessages).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(ActionEvent event) {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            out.println(message);
            chatHistory.append("You: " + message + "\n");
            messageField.setText("");
            saveChatHistory("You: " + message + "\n");
        }
    }

    private void listenForMessages() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                chatHistory.append("Server: " + message + "\n");
                saveChatHistory("Server: " + message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChatHistory() {
        try (BufferedReader logReader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = logReader.readLine()) != null) {
                chatHistory.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("No chat history found.");
        }
    }

    private void saveChatHistory(String message) {
        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            logWriter.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatClient::new);
    }
}
