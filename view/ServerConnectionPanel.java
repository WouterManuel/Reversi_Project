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

        /* Connect btn */
        JButton listBtn = new JButton("Connect now");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        listBtn.addActionListener(e -> clientController.startServerCommand(hostname.getText(), Integer.valueOf(port.getText())));
        add(listBtn, gbc);
    }
}
