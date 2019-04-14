package view;

import controller.ClientController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ServerDetailsPanel extends JPanel {
    JList playerList;

    JTable inviteList;
    DefaultTableModel tableModel;
    JButton challengeBtn;
    JButton acceptBtn;
    JButton logoutBtn;
    JLabel listText;
    ClientController clientController;

    public ServerDetailsPanel(ClientController clientController) {
        this.clientController = clientController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(350, 400));
        setBackground(Color.DARK_GRAY.darker());

        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        listText = new JLabel("Playerlist:");
        listText.setForeground(Color.WHITE);
        add(listText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        DefaultListModel<String> listModel = new DefaultListModel<>();
        playerList = new JList<>(listModel);

        challengeBtn = new JButton("Challenge");
        challengeBtn.addActionListener(e -> {
            clientController.getServerCommander().sendChallengeCommand(playerList.getSelectedValue().toString(), "Reversi");
        });

        JScrollPane playerListScroll = new JScrollPane(playerList);
        playerListScroll.setMinimumSize(new Dimension(140, 200));
        add(playerListScroll, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        add(challengeBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        listText = new JLabel("Challenges:");
        listText.setForeground(Color.WHITE);
        add(listText, gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        inviteList = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "GAME", "FROM"}, 0);
        inviteList.setModel(tableModel);
        inviteList.getColumnModel().getColumn(0).setPreferredWidth(20);
        inviteList.getColumnModel().getColumn(1).setPreferredWidth(68);
        inviteList.getColumnModel().getColumn(2).setPreferredWidth(69);
        inviteList.setAutoResizeMode(inviteList.AUTO_RESIZE_OFF);
        add(inviteList);

        acceptBtn = new JButton("Accept");
        add(acceptBtn);
        acceptBtn.addActionListener(e -> {
            clientController.getInvites().remove(tableModel.getValueAt(inviteList.getSelectedRow(), 0));
            clientController.getServerCommander().sendAcceptChallengeCommand((Integer) tableModel.getValueAt(inviteList.getSelectedRow(), 0));
        });

        JScrollPane inviteListScroll = new JScrollPane(inviteList);
        inviteListScroll.setMinimumSize(new Dimension(160, 200));
        add(inviteListScroll, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(acceptBtn, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        logoutBtn = new JButton("Logout");
        add(logoutBtn, gbc);
        logoutBtn.addActionListener(e -> {
            clientController.sendLogout();
        });

        new Thread(() -> {
            try {
                Thread.sleep(200);
                while (clientController.isLoggedIn()) {
                    Thread.sleep(2000);
                    Integer inviteNumber;
                    String challenger;
                    String game;
                    Hashtable<Integer, List<String>> newInviteList = new Hashtable<>();
                    ArrayList<String> newPlayerList = clientController.getServerCommander().getPlayerlist();

                    listModel.clear();
                    try {
                        for (String p : newPlayerList) {
                            if (!p.equals(clientController.getUsername()))
                                listModel.addElement(p);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        tableModel.removeRow(i);
                    }

                    newInviteList = clientController.getInvites();
                    Iterator it = newInviteList.keys().asIterator();
                    if (it.hasNext()) {
                        inviteNumber = (Integer) it.next();
                        challenger = newInviteList.get(inviteNumber).get(0);
                        game = newInviteList.get(inviteNumber).get(1);

                        tableModel.addRow(new Object[]{inviteNumber, game, challenger});

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}

