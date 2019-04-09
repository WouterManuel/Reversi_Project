package view;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ServerConnectionPanel extends JPanel {
    ClientController clientController;

    public ServerConnectionPanel(ClientController clientController) {
        this.clientController = clientController;
        setPreferredSize(new Dimension(300,400));
        setLayout(new GridBagLayout());
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.GRAY.brighter()));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 10, 2 ,10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel hostnameLabel = new JLabel("Hostname:");
        add(hostnameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField hostname = new JTextField("localhost", 15);
        add(hostname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel portLabel = new JLabel("Port:");
        add(portLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JTextField port = new JTextField("7789", 15);
        add(port, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel messageLabel = new JLabel("<html><font color='red'><b>Error:</b></font> Failed to connect to server</html>");
        messageLabel.setVisible(false);
        add(messageLabel, gbc);

        /* Connect btn */
        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton connectBtn = new JButton("Connect");
        connectBtn.addActionListener(e -> {clientController.startServerCommand(hostname.getText(), Integer.valueOf(port.getText()));
            if(!clientController.getConnectionStatus())
                messageLabel.setVisible(true);
        });
        add(connectBtn, gbc);
    }
}
