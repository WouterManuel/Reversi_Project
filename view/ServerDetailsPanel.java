package view;

import controller.ClientController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerDetailsPanel extends JPanel {
    JList playerList;
    JList inviteList;
    JList serverList;
    JButton challengeBtn;
    JButton acceptBtn;
    JButton logoutBtn;
    JLabel acceptedPlayer;
    JLabel acceptedInvite;
    JLabel listText;
    ClientController clientController;

    public ServerDetailsPanel(ClientController clientController) {
        this.clientController = clientController;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(350, 400));
        setBackground(Color.DARK_GRAY.darker());

        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 10);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        String testList = clientController.getServerCommander().getPlayerlist().toString();
        System.out.println(testList);

        listText = new JLabel("Playerlist :");
        listText.setForeground(Color.WHITE);
        add(listText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

            DefaultListModel<String> listModel = new DefaultListModel<>();
            playerList = new JList<>(listModel);

                    /* Challenge btn */
                    challengeBtn = new JButton("Challenge");

            /* See challenged player */
            acceptedPlayer = new JLabel("");
            acceptedPlayer.setForeground(Color.WHITE);

            challengeBtn.addActionListener(e -> {
                clientController.getServerCommander().sendChallengeCommand(playerList.getSelectedValue().toString(), "Tic-tac-toe");
            });


            JScrollPane playerListScroll = new JScrollPane(playerList);

            playerListScroll.setMinimumSize(new Dimension(125, 130));
            playerListScroll.setPreferredSize(new Dimension(125, 150));
            //playerListScroll.setMaximumSize(new Dimension(80, 200));


            add(playerListScroll, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            add(challengeBtn, gbc);
            acceptedPlayer = new JLabel("No players found");
            acceptedPlayer.setForeground(Color.WHITE);

            gbc.gridx = 1;
            gbc.gridy = 0;

            listText = new JLabel("Invites :");
            listText.setForeground(Color.WHITE);
            add(listText, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;

            // Server data here
            String player = "PlayerOne";
            String gameType = "TicTacToe";


            String invites[] = {player + " - " + gameType, "inv2", "inv3", "inv4", "inv5", "inv1", "inv2"};
            if (invites.length > 0) {
                inviteList = new JList<>(clientController.getServerCommander().getPlayerlist().toArray());

                /* Accept btn */
                acceptBtn = new JButton("Accept");
                add(acceptBtn);

                /* See accepted challenge */
                acceptedInvite = new JLabel("");
                acceptedInvite.setForeground(Color.WHITE);

                acceptBtn.addActionListener(e -> {
                    clientController.getServerCommander().sendAcceptChallengeCommand(0);
                });

                JScrollPane inviteListScroll = new JScrollPane(inviteList);

                inviteListScroll.setMinimumSize(new Dimension(125, 130));
                inviteListScroll.setPreferredSize(new Dimension(125, 150));

                add(inviteListScroll, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                add(acceptBtn, gbc);
            } else {
                acceptedInvite = new JLabel("No players found");
                acceptedInvite.setForeground(Color.WHITE);
            }

            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.gridy = 3;

            add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

            logoutBtn = new JButton("Logout");
            gbc.gridx = 1;
            gbc.gridy = 4;
            add(logoutBtn, gbc);
            logoutBtn.addActionListener(e -> {
                clientController.sendLogout();
            });

            gbc.gridx = 1;
            gbc.gridy = 5;

            new Thread(() -> {
                try {
                    Thread.sleep(100);
                    while (clientController.isLoggedIn()) {
                        ArrayList<String> newPlayerList = clientController.getServerCommander().getPlayerlist();
                        listModel.clear();

                        for (String p : newPlayerList) {
                            listModel.addElement(p);
                        }
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }

