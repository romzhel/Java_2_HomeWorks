package Lesson_6;

import java.net.ServerSocket;

public class ChatServerConsole {
    public static final int SERVER_PORT = 8189;
    private static Exchanger exchanger;

    public static void main(String[] args) {
        ConsoleMessenger consoleMessenger = new ConsoleMessenger();
        exchanger = new Exchanger();
        exchanger.setDisconnectionListener(ChatServerConsole::startServer);
        exchanger.setMessageListener(consoleMessenger::displayMessage);

        startServer();
        consoleMessenger.start(exchanger::sendMessage);

        exchanger.disconnect();
    }

    private static void startServer() {
        System.out.println("ожидаем подключение клиентов....");
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
                exchanger.start(serverSocket.accept());
                System.out.println("клиент подключен");
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
