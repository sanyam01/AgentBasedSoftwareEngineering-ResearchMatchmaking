package matchpackage.application;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AppGUI extends JFrame implements ActionListener {
	
	
	private JLabel mainHeading = new JLabel("Hey! Welcome to the Research Matchmaking");
	private JButton mainButton = new JButton("Please click here to access the application");
	private GuestGUI guestGUI;
	private GUIAgent guiAgent;

	

	AppGUI(GUIAgent guiAgent) {
		
		JPanel controlPanel = new JPanel(new BorderLayout());
		controlPanel.add(mainHeading,"North");
		controlPanel.add(mainButton, "Center");
		getContentPane().add(controlPanel);
		setSize(300, 300);
		setVisible(true);
		mainButton.addActionListener(this);
		this.guiAgent = guiAgent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Yes! This button has been clicked.");
		guestGUI = new GuestGUI(guiAgent);
		guiAgent.setGuestProviders();
		
		

	}
	
	public GuestGUI getGuestGUI() {
		return guestGUI;
	}

	public void setGuestGUI(GuestGUI guestGUI) {
		this.guestGUI = guestGUI;
	}

}
