package view;

import controller.ClientController;
import controller.ServerCommand;

import javax.swing.*;
import java.awt.*;

public class ServerLoginPanel extends JPanel {
    ClientController clientController;
    ServerCommand serverCommander;

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
        add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        /* Connect btn */
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> {
            if(!(username.getText().equals("") || username.getText().isEmpty()))
                {clientController.tryLogin(username.getText());}
            else {messageLabel.setVisible(true);}
        });
        add(loginBtn,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        /* Logout btn */
        JButton logoutBtn = new JButton("Disconnect");
        logoutBtn.addActionListener(e -> serverCommander.sendLogoutCommand());
        add(logoutBtn,gbc);
    }

    public void setServerCommander(ServerCommand serverCommander) {
        this.serverCommander = serverCommander;
    }
}
