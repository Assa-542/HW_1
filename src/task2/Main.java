package task2;

import Server.ServerController;
import Server.ServerGui;
import Server.ServerRepository;
import Client.ClientController;
import Client.ClientGui;

public class Main {
    public static void main(String[] args) {
        // Запуск сервера
        new Thread(() -> {
            ServerGui gui = new ServerGui();
            ServerRepository repository = new ServerRepository();
            ServerController controller = new ServerController(gui, repository);
            controller.start();
        }).start();

        // Небольшая задержка перед запуском клиента, чтобы сервер успел запуститься
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Запуск клиента
        new Thread(() -> {
            ClientGui clientGui = new ClientGui();
            ClientController clientController = new ClientController(clientGui);
            clientGui.setController(clientController);
            clientController.connect();
        }).start();
    }
}
