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
		setPreferredSize(new Dimension(400,300));
		setBackground(Color.GRAY);

		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());

		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 20;

		JButton subscribeBtn = new JButton("Subscribe reversi");
		subscribeBtn.addActionListener(e -> {
			clientController.getServerCommander().sendSubscribeCommand("Reversi");
		});

		add(subscribeBtn);
//
//		listText = new JLabel("Playerlist :");
//		listText.setForeground(Color.WHITE);
//		add(listText, gbc);
//
//		gbc.weighty = 10;
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//
//		String players[]= { "player1", "john doe", "foo", "bar","oke"};
//		if (players.length>0){
//			playerList = new JList(players);
//			playerList.setFixedCellWidth(100);
//
//			/* Challenge btn */
//			listBtn = new JButton("Challenge");
//
//			/* See challenged player */
//			acceptedPlayer = new JLabel("");
//			acceptedPlayer.setForeground(Color.WHITE);
//
//			listBtn.addActionListener(e -> seeAcceptedPlayer());
//
//			add(playerList, gbc);
//
//			gbc.gridx = 0;
//			gbc.gridy = 2;
//			add(listBtn, gbc);
//		}
//		else {
//			acceptedPlayer = new JLabel("No players found");
//			acceptedPlayer.setForeground(Color.WHITE);
//		}
//
//		gbc.gridx = 1;
//		gbc.gridy = 0;
//		gbc.ipadx = 20;
//
//		listText = new JLabel("Invitelist :");
//		listText.setForeground(Color.WHITE);
//		add(listText, gbc);
//
//		gbc.gridx = 1;
//		gbc.gridy = 1;
//
//		String invites[]= { "inv1", "inv2", "inv3", "inv4"};
//		if (invites.length>0){
//			inviteList = new JList(invites);
//			inviteList.setFixedCellWidth(100);
//
//			/* Accept btn */
//			listBtn2 = new JButton("Accept");
//			add(listBtn2);
//
//			/* See accepted challenge */
//			acceptedInvite = new JLabel("");
//			acceptedInvite.setForeground(Color.WHITE);
//
//			listBtn2.addActionListener(e -> seeAcceptedInvite());
//
//			add(inviteList, gbc);
//
//			gbc.weighty = 4;
//			gbc.gridx = 1;
//			gbc.gridy = 2;
//			add(listBtn2, gbc);
//		}
//		else {
//			acceptedInvite = new JLabel("No players found");
//			acceptedInvite.setForeground(Color.WHITE);
//		}
//	}
//
//	public void seeAcceptedPlayer() {
//		if (playerList.getSelectedValue() != null) {
//			String data = "";
//			data = "" + playerList.getSelectedValue();
//			acceptedPlayer.setText(data);
//			System.out.println(data);
//		}
//	}
//	public void seeAcceptedInvite() {
//		if (inviteList.getSelectedValue() != null) {
//			String data = "";
//			data = "" + inviteList.getSelectedValue();
//			acceptedInvite.setText(data);
//			System.out.println(data);
//		}
	}
}
