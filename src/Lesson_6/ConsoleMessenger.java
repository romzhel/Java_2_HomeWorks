package Lesson_6;

import java.util.Scanner;

public class ConsoleMessenger {

    public ConsoleMessenger() {
    }

    public void start(InputMessageListener inputMessageListener) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("введите сообщение и нажмите ENTER (/end - выход)");

        String inputMessage = null;
        while (true) {
            if (scanner.hasNextLine()) {
                inputMessage = scanner.nextLine();
                if (inputMessage.equalsIgnoreCase("/end")) {
                    break;
                } else if (!inputMessage.trim().isEmpty()) {
                    inputMessageListener.messageTyped(inputMessage);
                }
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    interface InputMessageListener {
        void messageTyped(String message);
    }
}
