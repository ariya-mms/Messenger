package client.gui;

import client.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm {
    private JPanel panel;
    private JTextField textField;
    private JButton loginButton;

    public LoginForm() {
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Main.connectToServer()) {
                    Main.setUserName(textField.getText());
                    Main.switchForm(new ChatForm().getPanel());
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
