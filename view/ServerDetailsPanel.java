package view;

import javax.swing.*;
import java.awt.*;

public class ServerDetailsPanel extends JPanel{
    JList playerList;
    JList inviteList;
    JButton listBtn;
    JButton listBtn2;
    JLabel acceptedPlayer;
    JLabel acceptedInvite;
    JLabel listText;
    JLabel listText2;


    public ServerDetailsPanel() {
        setPreferredSize(new Dimension(400,400));
        setLayout(new GridLayout(3,2));

        listText = new JLabel("Playerlist :");
		add(listText);

        listText2 = new JLabel("Invitelist :");
        add(listText2);

		String players[]= { "player1", "john doe", "foo", "bar","oke"};
		if (players.length>0){
			playerList = new JList(players);
			playerList.setFixedCellWidth(100);

			/* Challenge btn */
			listBtn = new JButton("Challenge");
			listBtn.addActionListener(e -> System.out.println("Challenge"));

			/* See challenged player */
			acceptedPlayer = new JLabel("");
			acceptedPlayer.setForeground(Color.WHITE);

			listBtn.addActionListener(e -> seeAcceptedPlayer());

			add(playerList);
			add(listBtn);
		}
		else {
			acceptedPlayer = new JLabel("No players found");
			acceptedPlayer.setForeground(Color.WHITE);
		}

		String invites[]= { "inv1", "inv2", "inv3", "inv4"};
		if (invites.length>0){
			inviteList = new JList(invites);
			inviteList.setFixedCellWidth(100);

			/* Accept btn */
			listBtn2 = new JButton("Accept");
			add(listBtn2);

			/* See accepted challenge */
			acceptedInvite = new JLabel("");
			acceptedInvite.setForeground(Color.WHITE);

			listBtn.addActionListener(e -> seeAcceptedInvite());

			add(inviteList);
			add(listBtn2);
		}
		else {
			acceptedInvite = new JLabel("No players found");
			acceptedInvite.setForeground(Color.WHITE);
		}
    }

	public void seeAcceptedPlayer() {
		if (playerList.getSelectedValue() != null) {
			String data = "";
			data = "" + playerList.getSelectedValue();
			acceptedPlayer.setText(data);
		}
	}
	public void seeAcceptedInvite() {
		if (inviteList.getSelectedValue() != null) {
			String data = "";
			data = "" + inviteList.getSelectedValue();
			acceptedInvite.setText(data);
		}
	}
}
