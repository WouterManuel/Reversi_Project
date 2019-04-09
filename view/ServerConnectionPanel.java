package view;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;

public class ServerConnectionPanel extends JPanel {
    ClientController clientController;

    public ServerConnectionPanel(ClientController clientController) {
        this.clientController = clientController;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(350,400));
        setBackground(Color.GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5 ,10);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel playOnline = new JLabel("Play online");
        playOnline.setFont(new Font("Serif", Font.PLAIN, 20));
        add(playOnline);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel hostnameLabel = new JLabel("Hostname:");
        add(hostnameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField hostname = new JTextField("localhost", 15);
        add(hostname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel portLabel = new JLabel("Port:");
        add(portLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
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
