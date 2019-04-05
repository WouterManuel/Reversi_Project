package view;

import javax.swing.*;
import java.awt.*;

public class ServerConnectionPanel {

    JPanel serverConnectionPanel;

    public ServerConnectionPanel() {
        serverConnectionPanel = new JPanel();
        serverConnectionPanel.setPreferredSize(new Dimension(300,400));
        serverConnectionPanel.setLayout(new GridBagLayout());
        serverConnectionPanel.setBackground(Color.GRAY);
        serverConnectionPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY.brighter()));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 10, 2 ,10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel hostnameLabel = new JLabel("hostname").;
        serverConnectionPanel.add(hostnameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField hostname = new JTextField("localhost", 15);
        serverConnectionPanel.add(hostname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel portLabel = new JLabel("port");
        serverConnectionPanel.add(portLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JTextField port = new JTextField("7789", 15);
        serverConnectionPanel.add(port, gbc);

        /* Connect btn */
        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton listBtn = new JButton("Connect");
        listBtn.addActionListener(e -> controller.ClientController.startServerCommand(hostname.getText(), Integer.valueOf(port.getText())));
        serverConnectionPanel.add(listBtn, gbc);
    }

    public JPanel getServerConnectionPanel() {
        return serverConnectionPanel;
    }

}
