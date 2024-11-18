package Server;

import java.util.ArrayList;
import java.util.List;

public class ServerRepository {
    private final List<String> messages = new ArrayList<>();

    public void saveMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
