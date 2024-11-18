package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClientGui {
    private final JFrame frame;
    private final JTextArea chatHistory;
    private final JTextField messageField;
    private ClientController controller;

    public ClientGui() {
        frame = new JFrame("Chat Client");
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
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    private void sendMessage(ActionEvent event) {
        String message = messageField.getText();
        if (!message.isEmpty() && controller != null) {
            controller.sendMessage(message);
            messageField.setText("");
        }
    }

    public void showMessage(String message) {
        chatHistory.append(message + "\n");
    }
}
