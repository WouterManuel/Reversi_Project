package view;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ServerLoginPanel extends JPanel {
    ClientController clientController;

    public ServerLoginPanel(ClientController clientController) {
        this.clientController = clientController;

        setPreferredSize(new Dimension(300,400));
        setLayout(new GridBagLayout());
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.GRAY.brighter(), 5));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 10, 2 ,10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel messageLabel = new JLabel("<html><b>Error:</b> <font color='red'>Try different username</font></html>");
        messageLabel.setVisible(false);
        add(messageLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel usernameLabel = new JLabel("Type a username: ");
        add(usernameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JTextField username = new JTextField( 15);
		username.addActionListener(e -> {
            String selectedUsername = username.getText();
            if(!(selectedUsername == null || selectedUsername.isEmpty())) {
                clientController.isLoggedIn(selectedUsername);
                if(!clientController.isUsernameSet()) {
                    messageLabel.setText("<html><b>Error:</b> <font color='red'>" + clientController.getServerCommander().getErrorMessage() + "</font></html>");
                    messageLabel.setVisible(true);
                }
            } else messageLabel.setVisible(true);
        });
        add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        /* Connect btn */
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> {
            String selectedUsername = username.getText();
            if(!(selectedUsername == null || selectedUsername.isEmpty())) {
                clientController.isLoggedIn(selectedUsername);
                if(!clientController.isUsernameSet()) {
                    messageLabel.setText("<html><b>Error:</b> <font color='red'>" + clientController.getServerCommander().getErrorMessage() + "</font></html>");
                    messageLabel.setVisible(true);
                }
            } else messageLabel.setVisible(true);
        });
        add(loginBtn,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        /* Logout btn */
        JButton logoutBtn = new JButton("Disconnect");
        logoutBtn.addActionListener(e -> clientController.getServerCommander().sendLogoutCommand());
        add(logoutBtn,gbc);
    }
}
