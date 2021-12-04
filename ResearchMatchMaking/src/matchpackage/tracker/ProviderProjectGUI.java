package matchpackage.tracker;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ProviderProjectGUI extends JFrame implements ActionListener {

	private JPanel overall, JPanel1, JPanel2, JPanel3, JPanel4, JPanel5;
	private JLabel tentativeDeadline, progress, time, changeRequest, status;
	private JTextArea deadlineArea, progressArea, timeArea, requestArea;
	private JTextArea statusArea;
	private JButton acceptRequest, rejectRequest;

	public ProviderProjectGUI() {

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
		changeRequest = new JLabel("Change request from Client");
		status = new JLabel("Status of project");

		deadlineArea = new JTextArea(5, 10);
		progressArea = new JTextArea(5, 10);
		timeArea = new JTextArea(5, 10);
		requestArea = new JTextArea(5, 10);
		statusArea = new JTextArea("Pending", 5, 10);
		statusArea.setEditable(false);
		acceptRequest = new JButton("Accept");
		rejectRequest = new JButton("Reject");
		


		JPanel1.add(tentativeDeadline);
		JPanel1.add(deadlineArea);

		JPanel2.add(progress);
		JPanel2.add(progressArea);

		JPanel3.add(time);
		JPanel3.add(timeArea);

		JPanel4.add(changeRequest);
		JPanel4.add(requestArea);
		JPanel4.add(acceptRequest);
		JPanel4.add(rejectRequest);

		JPanel5.add(status);
		JPanel5.add(statusArea);

		overall.add(JPanel1);
		overall.add(JPanel2);
		overall.add(JPanel3);
		overall.add(JPanel4);
		overall.add(JPanel5);

		getContentPane().add(overall);
		setTitle("Provider Project Tracker");
		setSize(700, 600);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
