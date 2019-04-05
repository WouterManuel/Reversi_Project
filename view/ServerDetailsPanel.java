package view;

import javax.swing.*;
import java.awt.*;

public class ServerDetailsPanel extends JPanel {
    JList playerList;
    JList inviteList;
    JButton listBtn;
    JLabel acceptedPlayer;
    JLabel acceptedInvite;
    JLabel listText;


    public ServerDetailsPanel() {
        setPreferredSize(new Dimension(300,400));
        setLayout(new GridLayout(3,2));

        listText = new JLabel("<html><br><span style='font-size: 25px'>"+"Playerlist :"+"</span></html>");
		listText.setForeground(Color.WHITE);
		add(listText,0.1);

        listText = new JLabel("<html><br>"+"Invitelist :"+"</html>");
        listText.setForeground(Color.WHITE);
        add(listText,1);

		String players[]= { "player1", "john doe", "foo", "bar"};
		if (players.length>0){
			playerList = new JList(players);
			playerList.setFixedCellWidth(100);

			/* Challenge btn */
			listBtn = new JButton("Challenge");

			/* See challenged player */
			acceptedPlayer = new JLabel("");
			acceptedPlayer.setForeground(Color.WHITE);

			listBtn.addActionListener(e -> seeAcceptedPlayer());

			add(playerList);
		}
		else {
			acceptedPlayer = new JLabel("No players found");
			acceptedPlayer.setForeground(Color.WHITE);
		}

		String invites[]= { "player1", "john doe", "foo", "bar"};
		if (invites.length>0){
			inviteList = new JList(invites);
			inviteList.setFixedCellWidth(100);

			/* Accept btn */
			listBtn = new JButton("Accept");

			/* See accepted challenge */
			acceptedInvite = new JLabel("");
			acceptedInvite.setForeground(Color.WHITE);

			listBtn.addActionListener(e -> seeAcceptedInvite());
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
