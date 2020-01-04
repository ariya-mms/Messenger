package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Main {

    static String messages = new String();

    private static final int PORT = 1313;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Session session = new Session(server.accept());
                session.start(); // it does not block this thread
                System.out.println("A new client connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Session extends Thread {
    private final Socket socket;

    public Session(Socket socketForClient) {
        this.socket = socketForClient;
    }

    public void run() {
        try (
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            while (true) {
                try {
                    String message = inputStream.readUTF();
                    Main.messages += (message + "\n");
                    outputStream.writeUTF(Main.messages);
                } catch (EOFException | SocketException e) {
                    System.out.println("A client disconnected");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
