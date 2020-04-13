package Lesson_6;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatClientWindow extends Application {
    private final String TITLE = "Чат (%sподключено к серверу)";
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Exchanger exchanger;
    private TextArea taChatMessages;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        exchanger = new Exchanger();
        exchanger.setMessageListener(this::displayInputMessage);
        initGUI(primaryStage);
        new Thread(this::connectToServer).start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        exchanger.disconnect();
    }

    private void connectToServer() {
        System.out.println("ожидаем появления сервера...");
        while (true) {
            try {
                exchanger.start(SERVER_ADDR, SERVER_PORT);
                System.out.println("сервер на связи");
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void initGUI(Stage primaryStage) {
        VBox vbRootPane = new VBox();
        vbRootPane.setPadding(new Insets(5, 5, 5, 5));

        taChatMessages = new TextArea();
        taChatMessages.setWrapText(true);
        taChatMessages.setEditable(false);
        VBox.setVgrow(taChatMessages, Priority.ALWAYS);

        HBox hbInputTreatPane = new HBox();
        hbInputTreatPane.setPadding(new Insets(5, 0, 0, 0));

        TextField tfMessageForSend = new TextField();
        tfMessageForSend.setDisable(true);
        HBox.setHgrow(tfMessageForSend, Priority.ALWAYS);
        tfMessageForSend.setOnKeyReleased(key -> {
            if (key.getCode() == KeyCode.ENTER) {
                displayAndSendMessage(tfMessageForSend);
            }
        });

        Button btnSend = new Button("Отправить");
        btnSend.setDisable(true);
        btnSend.setOnAction(event -> displayAndSendMessage(tfMessageForSend));

        hbInputTreatPane.getChildren().addAll(tfMessageForSend, btnSend);

        vbRootPane.getChildren().addAll(taChatMessages, hbInputTreatPane);
        Scene scene = new Scene(vbRootPane, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle(String.format(TITLE, "не "));

        primaryStage.show();

        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);

        exchanger.connectionStatusProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                primaryStage.setTitle(String.format(TITLE, newValue ? "" : "не "));
                tfMessageForSend.setDisable(!newValue);
                btnSend.setDisable(!newValue);
            });

            if (oldValue && !newValue) {
                connectToServer();
            }
        });

        tfMessageForSend.requestFocus();
    }

    private void displayAndSendMessage(TextField textField) {
        if (!textField.getText().isEmpty() && exchanger.isConnected()) {
            exchanger.sendMessage(textField.getText());

            taChatMessages.appendText("Client: " + textField.getText() + ">\n");
            textField.clear();
            textField.requestFocus();
        }
    }

    private void displayInputMessage(String message) {
        Platform.runLater(() -> taChatMessages.appendText("Server: >" + message + "\n"));
    }
}
