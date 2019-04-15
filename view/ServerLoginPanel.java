package view;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ServerLoginPanel extends JPanel {
    JButton disconnectBtn;
    ClientController clientController;

    public ServerLoginPanel(ClientController clientController) {
        this.clientController = clientController;

        setPreferredSize(new Dimension(350,400));
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY.darker());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 10, 2 ,10);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel messageLabel = new JLabel("<html><b>Error:</b> <font color='red'>Try different username</font></html>");
        messageLabel.setVisible(false);
        add(messageLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel usernameLabel = new JLabel("Type a username");
        usernameLabel.setForeground(Color.WHITE);
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;

        JTextField username = new JTextField("GroepE5", 15);
		username.addActionListener(e -> {
            String selectedUsername = username.getText();
            if(!(selectedUsername == null || selectedUsername.isEmpty())) {
                clientController.setLoggedIn(selectedUsername);
                if(!clientController.isLoggedIn()) {
                    messageLabel.setText("<html><b>Error:</b> <font color='red'>" + clientController.getServerCommander().getErrorMessage() + "</font></html>");
                    messageLabel.setVisible(true);
                }
            } else messageLabel.setVisible(true);
        });
        add(username, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        /* Connect btn */
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> {
            String selectedUsername = username.getText();
            if(!(selectedUsername == null || selectedUsername.isEmpty())) {
                clientController.setLoggedIn(selectedUsername);
                if(!clientController.isLoggedIn()) {
                    messageLabel.setText("<html><b>Error:</b> <font color='red'>" + clientController.getServerCommander().getErrorMessage() + "</font></html>");
                    messageLabel.setVisible(true);
                }
            } else messageLabel.setVisible(true);
        });
        add(loginBtn, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 5;
        disconnectBtn = new JButton("Disconnect");
        add(disconnectBtn,gbc);
        disconnectBtn.addActionListener(e -> {
            clientController.sendLogout();
        });
    }
}
