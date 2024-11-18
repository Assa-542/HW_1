package Client;

public interface Client {
    void connect();
    void disconnect();
    void sendMessage(String message);
}
