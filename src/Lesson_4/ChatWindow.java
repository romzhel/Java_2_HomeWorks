package Lesson_4;

import javafx.application.Application;
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

public class ChatWindow extends Application {
    private TextArea taChatMessages;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vbRootPane = new VBox();
        vbRootPane.setPadding(new Insets(5, 5, 5, 5));

        taChatMessages = new TextArea();
        taChatMessages.setWrapText(true);
        VBox.setVgrow(taChatMessages, Priority.ALWAYS);

        HBox hbInputTreatPane = new HBox();
        hbInputTreatPane.setPadding(new Insets(5, 0, 0, 0));

        TextField tfMessageForSend = new TextField();
        HBox.setHgrow(tfMessageForSend, Priority.ALWAYS);
        tfMessageForSend.setOnKeyReleased(key -> {
            if (key.getCode() == KeyCode.ENTER) {
                displayMessage(tfMessageForSend);
            }
        });

        Button btnSend = new Button("Отправить");
        btnSend.setOnAction(event -> displayMessage(tfMessageForSend));

        hbInputTreatPane.getChildren().addAll(tfMessageForSend, btnSend);

        vbRootPane.getChildren().addAll(taChatMessages, hbInputTreatPane);
        Scene scene = new Scene(vbRootPane, 400, 600);
        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);

        tfMessageForSend.requestFocus();
    }

    private void displayMessage(TextField textField) {
        if (!textField.getText().isEmpty()) {
            taChatMessages.appendText(textField.getText() + "\n");
            textField.setText("");
            textField.requestFocus();
        }
    }
}
