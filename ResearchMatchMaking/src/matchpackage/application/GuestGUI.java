package matchpackage.application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import jade.core.behaviours.OneShotBehaviour;
import matchpackage.access.SignUpGUI;

public class GuestGUI extends JFrame implements ActionListener {

	private JPanel jPanel;
	private JLabel welcomeLabel;
	private JPanel subJPanel;
	private JPanel topPanel;
	private JTextArea listProviders;
	private JButton signupButton;
	private JButton loginButton;
	private JTextArea enterKeywords;
	private JButton searchButton;
	

	private GUIAgent guiAgent;
	private SignUpGUI signUpGui;

	public GuestGUI(GUIAgent guiAgent) {

		this.guiAgent = guiAgent;
		jPanel = new JPanel(new BorderLayout());
		subJPanel = new JPanel(new FlowLayout());
		topPanel = new JPanel(new FlowLayout());

		welcomeLabel = new JLabel(
				"Welcome as a Guest to the research matchmaking. Please enter list of keywords"
				+ " separated by comma for getting list of providers based on that.");
		loginButton = new JButton("Login in");
		signupButton = new JButton("Sign up");
		loginButton.addActionListener(this);
		signupButton.addActionListener(this);
		

		topPanel.add(welcomeLabel);
		topPanel.add(loginButton);
		topPanel.add(signupButton);

		enterKeywords = new JTextArea(10, 30);
		enterKeywords.setLineWrap(true);
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);

		subJPanel.add(enterKeywords, "West");
		subJPanel.add(searchButton, "East");

		listProviders = new JTextArea("THis is the list Sanyam", 10,40);

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
		if (e.getSource() == searchButton) {
			
			System.out.println("Button has been pressed");
			String keywords = enterKeywords.getText();
			guiAgent.setStep(2);
			guiAgent.callSearchKeywords(keywords);
		}
			
		if(e.getSource() == signupButton) {
			
			 signUpGui = new SignUpGUI();
			 
		}
		

	}
	
	
	public JTextArea getListProviders() {
		return listProviders;
	}

	public void setListProviders(String listProviders) {
		this.listProviders = new JTextArea(listProviders);
	}
	
	public void setContentListProvider (String content) {
		

		listProviders.setText(content);
		
		
		
		System.out.println("I am getting the content to set " + content);
		
	}

	public JTextArea getEnterKeywords() {
		return enterKeywords;
	}

	public void setEnterKeywords(String keywords) {
		this.enterKeywords.setText(keywords); 
	}
	
	

}
