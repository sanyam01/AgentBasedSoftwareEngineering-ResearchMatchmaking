package matchpackage.contract;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;

public class ClientChatGUI extends JFrame implements ActionListener {

	private JLabel clientLabel;
	private JTextArea chatArea;
	private JScrollPane scrollPane;
	private JPanel jPanel;

	public ClientChatGUI() {

		clientLabel = new JLabel("Client Messenger");
		chatArea = new JTextArea(30, 10);
		scrollPane = new JScrollPane(chatArea);

		jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
		jPanel.add(clientLabel);
		jPanel.add(scrollPane);

		getContentPane().add(jPanel);
		setTitle("Client Messenger");
		setSize(600, 600);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
