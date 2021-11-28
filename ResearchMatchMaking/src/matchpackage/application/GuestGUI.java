package matchpackage.application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GuestGUI extends JFrame implements ActionListener {

	private JPanel jPanel;
	private JLabel welcomeLabel;
	private JPanel subJPanel;
	private JPanel topPanel;
	private JTextArea listProviders;
	private JButton loginButton;
	private JTextArea enterKeywords;

	public GuestGUI() {

		jPanel = new JPanel(new BorderLayout());
		subJPanel = new JPanel(new FlowLayout());
		topPanel = new JPanel(new FlowLayout());

		welcomeLabel = new JLabel(
				"Welcome as a Guest to the research matchmaking. Please enter list of keywords"
				+ " separated by comma for getting list of providers based on that.");
		loginButton = new JButton("Sign up/Login in");

//		topPanel.add(welcomeLabel, "West");
//		topPanel.add(loginButton, "East");

		topPanel.add(welcomeLabel);
		topPanel.add(loginButton);

		enterKeywords = new JTextArea(10, 30);
		enterKeywords.setLineWrap(true);
		loginButton = new JButton("Search");
//		loginButton.setSize(20,20);

		subJPanel.add(enterKeywords, "West");
		subJPanel.add(loginButton, "East");

		listProviders = new JTextArea("Here a list of providers will be shown", 10, 40);

		jPanel.add(topPanel, "North");
		jPanel.add(subJPanel, "Center");
		jPanel.add(listProviders, "South");

		getContentPane().add(jPanel);
		setSize(500, 500);
		setVisible(true);
		pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
