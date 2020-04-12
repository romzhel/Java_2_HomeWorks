package Lesson_6;

import javafx.beans.value.ChangeListener;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServerConsole {
    public static final int SERVER_PORT = 8189;
    private static Exchanger exchanger;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            ConsoleMessenger consoleMessenger = new ConsoleMessenger();
            exchanger = new Exchanger();
            ChangeListener<Boolean> disconnectionListener = (observable, oldValue, newValue) -> {
                if (oldValue && !newValue) {
                    startServer(serverSocket);
                }
            };
            exchanger.connectionStatusProperty().addListener(disconnectionListener);
            exchanger.setMessageListener(consoleMessenger::displayMessage);

            startServer(serverSocket);
            consoleMessenger.start(exchanger::sendMessage);

            exchanger.connectionStatusProperty().removeListener(disconnectionListener);
            exchanger.disconnect();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startServer(ServerSocket serverSocket) {
        System.out.println("ожидаем подключение клиентов....");
        try {
            exchanger.start(serverSocket.accept());
            System.out.println("клиент подключен");
        } catch (Exception e) {

        }
    }
}
