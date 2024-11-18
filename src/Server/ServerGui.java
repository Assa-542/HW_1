package Server;

import javax.swing.*;

public class ServerGui {
    private final JFrame frame;
    private final JTextArea textArea;

    public ServerGui() {
        frame = new JFrame("Server");
        textArea = new JTextArea(20, 40);
        frame.getContentPane().add(new JScrollPane(textArea), "Center");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void showMessage(String message) {
        textArea.append(message + "\n");
    }
}
