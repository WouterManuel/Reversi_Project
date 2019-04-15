package view;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ServerConnectionPanel extends JPanel {
    ClientController clientController;

    public ServerConnectionPanel(ClientController clientController) {
        this.clientController = clientController;
        setPreferredSize(new Dimension(350,400));
        setLayout(new GridBagLayout());
        setBackground(Color.darkGray.darker());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 10, 2 ,10);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel playOnline = new JLabel("<html><br><div style='color: white;margin-bottom: 35px; font-size: 20px;'>Play Online</div></html>");
        add(playOnline, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel hostnameLabel = new JLabel("Hostname:");
        hostnameLabel.setForeground(Color.WHITE);
        add(hostnameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField hostname = new JTextField("localhost", 10);
        add(hostname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel portLabel = new JLabel("Port:");
        portLabel.setForeground(Color.WHITE);
        add(portLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JTextField port = new JTextField("7789",10);
        add(port, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel timeoutLabel = new JLabel("Timeout:");
        timeoutLabel.setForeground(Color.WHITE);
        add(timeoutLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextField timeout = new JTextField("10000",10);
        add(timeout, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel messageLabel = new JLabel("<html><font color='red'><b>Error: </b></font>Failed to connect to the server</html>");
        messageLabel.setVisible(false);
        messageLabel.setForeground(Color.GRAY.brighter());
        add(messageLabel, gbc);


        /* Connect btn */
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        JButton connectBtn = new JButton("Connect");
        connectBtn.addActionListener(e -> {clientController.startServerCommand(hostname.getText(), Integer.valueOf(port.getText()), Integer.valueOf(timeout.getText()));
            if(!clientController.isConnected())
                messageLabel.setVisible(true);
        });
        add(connectBtn, gbc);
    }
}
