package client.gui;

import client.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ChatForm {
    private JTextArea messages;
    private JPanel panel;
    private JTextField textField;
    private JButton sendButton;
    private JButton refreshButton;

    public ChatForm() {
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Main.sendToServer(Main.getUserName() + ": " + textField.getText());
                    Main.listenToServer();
                    messages.setText(Main.getMessages());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Main.sendToServer("");
                    Main.listenToServer();
                    messages.setText(Main.getMessages());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}