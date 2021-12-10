package matchpackage.contract;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import matchpackage.access.ProviderAgent;

public class ProviderChatGUI extends JFrame implements ActionListener  {
	
	private JLabel providerLabel;
	private JTextArea chatArea;
	private JScrollPane scrollPane;
	private JPanel jPanel,overall;
	private JButton submit;
	private ProviderAgent provideragent;
	
	public ProviderChatGUI(ProviderAgent provideragent) {
		this.provideragent=provideragent;
		providerLabel = new JLabel("Provider Messenger");
		chatArea = new JTextArea(30,10);
		scrollPane = new JScrollPane(chatArea);
		
		jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
		
		jPanel.add(providerLabel);
		jPanel.add(chatArea);
		
		submit = new JButton("Submit");
		submit.addActionListener(this);
		overall.add(jPanel);
		overall.add(submit);
		getContentPane().add(overall);
		setTitle("Provider Messenger");
		setSize(600, 600);
		setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == submit) {
			System.out.println("Submit Login button has been clicked");
			provideragent.showChat(chatArea.getText());
			
			
	
			
			
			
			
		}
	}

}
