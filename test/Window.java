package test;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	static final long serialVersionUID = 1L;

	public Window(){
		rPanel rp = new rPanel();
		add(rp);
		setTitle("Reversi");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Window();
	}
}
