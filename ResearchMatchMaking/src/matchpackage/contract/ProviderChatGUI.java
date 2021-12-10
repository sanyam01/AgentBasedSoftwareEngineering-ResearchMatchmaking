package matchpackage.contract;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;

public class ProviderChatGUI extends JFrame implements ActionListener  {
	
	private JLabel providerLabel;
	private JTextArea chatArea;
	private JScrollPane scrollPane;
	private JPanel jPanel;
	
	public ProviderChatGUI() {
		
		providerLabel = new JLabel("Provider Messenger");
		chatArea = new JTextArea(30,10);
		scrollPane = new JScrollPane(chatArea);
		
		jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
		
		jPanel.add(providerLabel);
		jPanel.add(chatArea);
		
		getContentPane().add(jPanel);
		setTitle("Provider Messenger");
		setSize(600, 600);
		setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
