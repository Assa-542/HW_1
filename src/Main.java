public class Main {
    public static void main(String[] args) {
        // Запуск сервера
        new Thread(() -> server.ChatServer.main(args)).start();

        // Запуск клиента
        new Thread(() -> client.ChatClient.main(args)).start();
    }
}
