package client;

import client.gui.LoginForm;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 1313;

    private static JFrame frame;
    private static String userName;
    private static Socket socket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    private static String messages = new String();


    public static void main(String[] args) {
        frame = new JFrame();
        switchForm(new LoginForm().getPanel());
    }


    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Main.userName = userName;
    }

    public static String getMessages() {
        return messages;
    }

    public static void switchForm(JPanel panel) {
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static boolean connectToServer() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void sendToServer(String message) throws IOException {
        outputStream.writeUTF(message);
    }

    public static void listenToServer() throws IOException {
        messages = inputStream.readUTF() + "\n";
    }
}
