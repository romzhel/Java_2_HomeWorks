package Lesson_6;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Exchanger {
    private Socket clientSocket;
    private DataInputStream inStream;
    private DataOutputStream outStream;
    private MessageListener messageListener;
    private DisconnectionListener disconnectionListener;
    private BooleanProperty connectionStatus;

    public Exchanger() {
        connectionStatus = new SimpleBooleanProperty(false);
    }

    public void start(Socket socket) throws Exception {
        clientSocket = socket;
        init();
    }

    public void start(String serverAddress, int port) throws Exception {
        clientSocket = new Socket(serverAddress, port);
        init();
    }

    private void init() throws Exception {
        if (messageListener == null) {
            throw new Exception("MessageListener is null");
        }

        inStream = new DataInputStream(clientSocket.getInputStream());
        outStream = new DataOutputStream(clientSocket.getOutputStream());

        Thread listenerThread = new Thread(() -> {
            String message = null;
            while (true) {
                try {
                    message = inStream.readUTF();
                    messageListener.messageReceived(message);
                } catch (IOException e) {
//                    System.out.println("listenerThread exception: " + e.getMessage());
                    disconnect();

                    if (disconnectionListener != null) {
                        disconnectionListener.disconnect();
                    }

                    break;
                }
            }
        });
        listenerThread.setDaemon(true);
        listenerThread.start();

        connectionStatus.setValue(true);
    }

    public void disconnect() {
        Closeable[] objects = {inStream, outStream, clientSocket};
        for (Closeable object : objects) {
            try {
                object.close();
            } catch (Exception e) {

            }
        }
        connectionStatus.setValue(false);
    }

    public boolean sendMessage(String message) {
        try {
            outStream.writeUTF(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void setDisconnectionListener(DisconnectionListener disconnectionListener) {
        this.disconnectionListener = disconnectionListener;
    }

    public boolean isConnected() {
        return connectionStatus.get();
    }

    public BooleanProperty connectionStatusProperty() {
        return connectionStatus;
    }

    interface MessageListener {
        void messageReceived(String Message);
    }

    interface DisconnectionListener {
        void disconnect();
    }
}
