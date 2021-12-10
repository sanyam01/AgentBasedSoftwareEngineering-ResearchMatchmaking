package matchpackage.application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import jade.core.behaviours.OneShotBehaviour;

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
	private JScrollPane scrollProviders;
	private JTable providerTable;
	DefaultTableModel tableModel;

	private GUIAgent guiAgent;
	private SignUpGUI signUpGui;
	private LoginGUI loginGUI;
	String[] columnNames = { "Name", "Website", "Logo", "Keywords", "Resume" };

	public GuestGUI(GUIAgent guiAgent) {

		this.guiAgent = guiAgent;
		jPanel = new JPanel(new BorderLayout());
		subJPanel = new JPanel(new FlowLayout());
		topPanel = new JPanel(new FlowLayout());

		welcomeLabel = new JLabel("Welcome as a Guest to the research matchmaking. Please enter list of keywords"
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

		listProviders = new JTextArea(10, 40);
		providerTable = new JTable();
		tableModel = (DefaultTableModel) providerTable.getModel();
		tableModel.setColumnIdentifiers(columnNames);
		scrollProviders = new JScrollPane(providerTable);

		jPanel.add(topPanel, "North");
		jPanel.add(subJPanel, "Center");
		jPanel.add(scrollProviders, "South");

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
			//guiAgent.callSearchKeywords(keywords);
			
			
		}

		if (e.getSource() == signupButton) {

			signUpGui = new SignUpGUI(this.guiAgent);

		}

		if (e.getSource() == loginButton) {
			loginGUI = new LoginGUI(this.guiAgent);
		}

	}

	public JTextArea getListProviders() {
		return listProviders;
	}

	public void setListProviders(String listProviders) {
		this.listProviders = new JTextArea(listProviders);
	}
	
	public String getStringKeyWords() {
		return enterKeywords.getText();
	}

	public void setContentListProvider(String content) {
		
		tableModel.setRowCount(0);
		tableModel.fireTableDataChanged();

		String[] listRows = content.split("\n");

		int numRows = (int) listRows.length;

		String[][] providerData = new String[numRows][5];

		for (int i = 0; i < numRows; i++) {
			String[] data = listRows[i].split("\\*");
			for (int j = 0; j < 5; j++) {
				providerData[i][j] = data[j];
			}
			
			tableModel.addRow(data);
			
		}

		tableModel.fireTableDataChanged();
		
		System.out.println("I am getting the content to set " + content);
		providerTable.repaint();

	}

	public JTextArea getEnterKeywords() {
		return enterKeywords;
	}

	public void setEnterKeywords(String keywords) {
		this.enterKeywords.setText(keywords);
	}

}
