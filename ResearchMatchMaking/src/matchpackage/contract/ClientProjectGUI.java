package matchpackage.contract;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import matchpackage.access.CustomerAgent;

public class ClientProjectGUI extends JFrame implements ActionListener {

	private JPanel overall, JPanel1, JPanel2, JPanel3, JPanel4, JPanel5;
	private JLabel tentativeDeadline, progress, time, changeRequest, status;
	private JTextArea deadlineArea, progressArea, timeArea, requestArea, statusArea;
	private JComboBox comboBox;
	private JButton submitRequest;
	CustomerAgent customerAgent;

	public ClientProjectGUI(CustomerAgent customerAgent) {

		this.customerAgent = customerAgent;

		overall = new JPanel();
		JPanel1 = new JPanel();
		JPanel2 = new JPanel();
		JPanel3 = new JPanel();
		JPanel4 = new JPanel();
		JPanel5 = new JPanel();

		overall.setLayout(new GridLayout(5, 1, 1, 1));
		JPanel1.setLayout(new GridLayout(1, 2, 1, 1));
		JPanel2.setLayout(new GridLayout(1, 2, 1, 1));
		JPanel3.setLayout(new GridLayout(1, 2, 1, 1));
		JPanel4.setLayout(new GridLayout(1, 3, 1, 1));
		JPanel5.setLayout(new GridLayout(1, 2, 1, 1));

		tentativeDeadline = new JLabel("Tentative Deadline");
		progress = new JLabel("Progress Report");
		time = new JLabel("Estimated time of completion");
		changeRequest = new JLabel("Request Change");
		status = new JLabel("Status of project");

		deadlineArea = new JTextArea(5, 10);
		progressArea = new JTextArea(5, 10);
		timeArea = new JTextArea(5, 10);
		requestArea = new JTextArea(5, 10);
		submitRequest = new JButton("Submit");
		submitRequest.addActionListener(this);
		statusArea = new JTextArea("Pending", 5, 10);

		String[] choices = { "Pending", "Complete" };
		comboBox = new JComboBox(choices);

		JPanel1.add(tentativeDeadline);
		JPanel1.add(deadlineArea);

		JPanel2.add(progress);
		JPanel2.add(progressArea);

		JPanel3.add(time);
		JPanel3.add(timeArea);

		JPanel4.add(changeRequest);
		JPanel4.add(requestArea);
		JPanel4.add(submitRequest);

		JPanel5.add(status);
		JPanel5.add(statusArea);

		overall.add(JPanel1);
		overall.add(JPanel2);
		overall.add(JPanel3);
		overall.add(JPanel4);
		overall.add(JPanel5);

		getContentPane().add(overall);
		setTitle("Client Project Tracker");
		setSize(600, 600);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitRequest) {
			customerAgent.changeRequest(requestArea.getText());
			System.out.println("I am getting clicked");
			System.out.println("the value is " + requestArea.getText());
		}
		// TODO Auto-generated method stub

	}
	
	

	public void setDeadlineArea(String text) {
		deadlineArea.setText(text);
	}

	public void setProgressArea(String text) {
		progressArea.setText(text);
	}

	public void setTimeArea(String text) {
		timeArea.setText(text);
	}

	public void setStatusArea(String text) {
		statusArea.setText(text);
	}
	
	public void setTextArea(String text) {
		requestArea.setText(text);
	}

}
