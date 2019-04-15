package view;

import controller.ClientController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;
import java.util.List;

public class ServerDetailsPanel extends JPanel {
    JList playerList;
    JTable inviteList;
    Thread inviteThread;
    DefaultTableModel tableModel;
    JButton challengeBtn;
    JButton acceptBtn;
    JButton logoutBtn;
    JLabel listText;
    String opponent;
    int opponentAccept;
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
        listText = new JLabel("<html><div style='font-size:14;'>Game Server</div?</html>");
        listText.setForeground(Color.GREEN);
        add(listText, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        listText = new JLabel("Playerlist:");
        listText.setForeground(Color.WHITE);
        add(listText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        playerList = new JList<>(listModel);
        playerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                try {
                    opponent = playerList.getSelectedValue().toString();
                }catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });


        challengeBtn = new JButton("Challenge");
        challengeBtn.addActionListener(e -> {
            clientController.getServerCommander().sendChallengeCommand(opponent, "Reversi");
        });
        JScrollPane playerListScroll = new JScrollPane(playerList);
        playerListScroll.setMinimumSize(new Dimension(140, 200));
        add(playerListScroll, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        add(challengeBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        listText = new JLabel("Challenges:");
        listText.setForeground(Color.WHITE);
        add(listText, gbc);


        gbc.gridx = 1;
        gbc.gridy = 2;
        inviteList = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Game", "From"},0);
        inviteList.setModel(tableModel);
        inviteList.getColumnModel().getColumn(0).setPreferredWidth(20);
        inviteList.getColumnModel().getColumn(1).setPreferredWidth(68);
        inviteList.getColumnModel().getColumn(2).setPreferredWidth(69);
        inviteList.setAutoResizeMode(inviteList.AUTO_RESIZE_OFF);
        inviteList.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                opponentAccept = (Integer) tableModel.getValueAt(inviteList.getSelectedRow(), 0);
                System.out.println(opponentAccept);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        add(inviteList);

        acceptBtn = new JButton("Accept");
        add(acceptBtn);
        acceptBtn.addActionListener(e -> {
            clientController.getInvites().remove(opponentAccept);
            clientController.getServerCommander().sendAcceptChallengeCommand(opponentAccept);
        });

        JScrollPane inviteListScroll = new JScrollPane(inviteList);
        inviteListScroll.setMinimumSize(new Dimension(160, 200));
        add(inviteListScroll, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(acceptBtn, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        logoutBtn = new JButton("Logout");
        inviteThreadStop click = new inviteThreadStop();
        logoutBtn.addActionListener(click);
        add(logoutBtn, gbc);
        logoutBtn.addActionListener(e -> {
            clientController.sendLogout();
        });

        inviteThread = new Thread(() -> {
            try {
                Thread.sleep(200);
                while (clientController.isLoggedIn()) {
                    Thread.sleep(2000);
                    Integer inviteNumber;
                    String challenger;
                    String game;
                    Hashtable<Integer, List<String>> newInviteList;
                    ArrayList<String> newPlayerList = clientController.getServerCommander().getPlayerlist();

                    listModel.removeAllElements();
                    listModel.add(0,"");
                    try {
                        for (String p : newPlayerList) {
                            if (!p.equals(clientController.getUsername()) && (!p.equals("OK")))
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
        });
        inviteThread.start();
    }

    private class inviteThreadStop implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logoutBtn) {
                inviteThread.stop();
            }
        }
    }
}