package matchpackage.contract;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import matchpackage.access.ProviderAgent;

public class ProviderFeedbackGUI extends JFrame implements ActionListener {

	private JPanel overall;
	private JPanel JPanel1, JPanel2, JPanel3;
	private JLabel commentLabel, ratingLabel;
	private JTextArea commentArea, ratingArea;
	private JButton submit;
	private ProviderAgent providerAgent;

	public ProviderFeedbackGUI(ProviderAgent agent) {
		
		this.providerAgent = agent;

		overall = new JPanel();
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();

		overall.setLayout(new GridLayout(3, 1, 1, 1));
		JPanel1.setLayout(new GridLayout(1, 2, 1, 1));
		JPanel2.setLayout(new GridLayout(1, 2, 1, 1));

		commentLabel = new JLabel("Enter comments for the client");
		ratingLabel = new JLabel("Leave a rating");

		commentArea = new JTextArea(5, 10);
		ratingArea = new JTextArea(5, 10);
		submit = new JButton("Submit");

		JPanel1.add(commentLabel);
		JPanel1.add(commentArea);

		JPanel2.add(ratingLabel);
		JPanel2.add(ratingArea);

		JPanel3.add(submit);

		overall.add(JPanel1);
		overall.add(JPanel2);
		overall.add(JPanel3);

		getContentPane().add(overall);
		setTitle("Provider Feedback Messenger");
		setSize(600, 300);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == submit) {
			this.setVisible(false);
		}

	}

}
