package view;

import controller.ClientController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ServerDetailsPanel extends JPanel {
	JList playerList;
	JList inviteList;
	JButton listBtn;
	JButton listBtn2;
	JLabel acceptedPlayer;
	JLabel acceptedInvite;
	JLabel listText;
	JLabel listText2;
	ClientController clientController;

	public ServerDetailsPanel(ClientController clientController) {
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

        listText = new JLabel("Playerlist :");
        listText.setForeground(Color.WHITE);
        add(listText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        // clientController.getServerCommander().getPlayerlist();

        String players[]= { "player1", "john doe", "fyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy", "bar","oke", "1", "2", "3", "4", "5" };
        if (players.length>0){
            playerList = new JList(players);

            /* Challenge btn */
            listBtn = new JButton("Challenge");

            /* See challenged player */
            acceptedPlayer = new JLabel("");
            acceptedPlayer.setForeground(Color.WHITE);

            listBtn.addActionListener(e -> {
                clientController.getServerCommander().sendChallengeCommand(playerList.getSelectedValue().toString(), "Tic-tac-toe");
            });


            JScrollPane playerListScroll = new JScrollPane(playerList);

            playerListScroll.setMinimumSize(new Dimension(30, 130));
            playerListScroll.setPreferredSize(new Dimension(50, 150));
            //playerListScroll.setMaximumSize(new Dimension(80, 200));

            add(playerListScroll, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            add(listBtn, gbc);
        }
        else {
            acceptedPlayer = new JLabel("No players found");
            acceptedPlayer.setForeground(Color.WHITE);
        }

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

        String invites[]= { player+" - "+gameType, "inv2", "inv3", "inv4", "inv5", "inv1", "inv2", "invrrrrrrrrrrrrrrrtyrtytrytrytrytrytrytrrrrrrrr3", "inv4", "inv5"};
        if (invites.length>0){
            inviteList = new JList(invites);

            /* Accept btn */
            listBtn2 = new JButton("Accept");
            add(listBtn2);

            /* See accepted challenge */
            acceptedInvite = new JLabel("");
            acceptedInvite.setForeground(Color.WHITE);

            listBtn2.addActionListener(e -> {clientController.startGame("Reversi", clientController.playingAs());});

            JScrollPane inviteListScroll = new JScrollPane(inviteList);

            inviteListScroll.setMinimumSize(new Dimension(30, 130));
            inviteListScroll.setPreferredSize(new Dimension(50, 150));
            //inviteListScroll.setMaximumSize(new Dimension(80, 200));

            add(inviteListScroll, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            add(listBtn2, gbc);
        }
        else {
            acceptedInvite = new JLabel("No players found");
            acceptedInvite.setForeground(Color.WHITE);
        }

        gbc.gridx = 0;
        gbc.gridy = 3;

        listText = new JLabel("Subscribe:");
        listText.setForeground(Color.WHITE);
        add(listText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;

        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;

        JButton subscribeBtn = new JButton("Subscribe reversi  ");
        subscribeBtn.addActionListener(e -> {
            clientController.getServerCommander().sendSubscribeCommand("Reversi");
        });

        add(subscribeBtn, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;

        JButton subscribeBtnTTT = new JButton("Subscribe TicTacToe");
        subscribeBtnTTT.addActionListener(e -> {
            clientController.getServerCommander().sendSubscribeCommand("Tic-tac-toe");
        });

        add(subscribeBtnTTT, gbc);

    }

    public void seeAcceptedPlayer() {
        if (playerList.getSelectedValue() != null) {
            String data = "";
            data = "" + playerList.getSelectedValue();
            acceptedPlayer.setText(data);
            System.out.println(data);
        }
    }
    public void seeAcceptedInvite() {
        if (inviteList.getSelectedValue() != null) {
            String data = "";
            data = "" + inviteList.getSelectedValue();
            acceptedInvite.setText(data);
            System.out.println(data);
        }
    }
}
