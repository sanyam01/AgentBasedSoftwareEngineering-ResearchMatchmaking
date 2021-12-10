package matchpackage.contract;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import matchpackage.access.CustomerAgent;

public class ClientFeedbackGUI extends JFrame implements ActionListener {

	private JPanel overall;
	private JPanel JPanel1, JPanel2, JPanel3, JPanel4;
	private JLabel commentLabel, ratingLabel;
	private JTextArea commentArea, ratingArea, paymentArea;
	private JButton submit, paymentButton;
	CustomerAgent customerAgent;

	public ClientFeedbackGUI(CustomerAgent customerAgent) {

		this.customerAgent = customerAgent;
		overall = new JPanel();
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();

		overall.setLayout(new GridLayout(4, 1, 1, 1));
		JPanel1.setLayout(new GridLayout(1, 2, 1, 1));
		JPanel2.setLayout(new GridLayout(1, 2, 1, 1));
		JPanel4.setLayout(new GridLayout(1, 2, 1, 1));

		commentLabel = new JLabel("Enter comments for the provider");
		ratingLabel = new JLabel("Leave a rating");

		commentArea = new JTextArea(5, 10);
		ratingArea = new JTextArea(5, 10);
		submit = new JButton("Submit");
		submit.addActionListener(this);
		
		paymentButton = new JButton("Pay the Amount");
		paymentButton.addActionListener(this);
		paymentArea = new JTextArea(5,10);
		

		JPanel1.add(commentLabel);
		JPanel1.add(commentArea);

		JPanel2.add(ratingLabel);
		JPanel2.add(ratingArea);

		JPanel3.add(submit);
		
		JPanel4.add(paymentArea);
		JPanel4.add(paymentButton);
		

		overall.add(JPanel1);
		overall.add(JPanel2);
		overall.add(JPanel3);
		overall.add(JPanel4);

		getContentPane().add(overall);
		setTitle("Client Feedback Messenger");
		setSize(600, 300);
		setVisible(true);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == submit) {
			customerAgent.closeFeedbackWindow();
			ratingArea.setText("");
			commentArea.setText("");
		}
		
		if(e.getSource() == paymentButton) {
			customerAgent.sendPaymentConfirmation();
		}

	}
	
	public void setPaymentArea(String text) {
		paymentArea.setText(text);
	}
	
	

}
