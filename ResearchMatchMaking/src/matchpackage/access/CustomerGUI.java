package matchpackage.access;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CustomerGUI extends JFrame implements ActionListener {

	private JPanel overallPanel, firstPanel, secondPanel, thirdPanel;
	private JPanel fourthPanel, fifthPanel, buttonPanel;

	private JLabel enterKeywords;
	private JTextArea keywordsArea;
	private JButton searchButton;

	private JTextArea listProviders;

	private JLabel bidValue;
	private JTextArea bidValueArea;
	private JButton bidButton;

	private JTextArea bidAcceptance;

	private JLabel contract;
	private JTextArea contractArea;
	private JButton acceptContractButton;
	private JButton rejectContractButton;

	public CustomerGUI() {

		overallPanel = new JPanel();
		firstPanel = new JPanel();
		secondPanel = new JPanel();
		thirdPanel = new JPanel();
		fourthPanel = new JPanel();
		fifthPanel = new JPanel();
		buttonPanel = new JPanel(new FlowLayout());

		overallPanel.setLayout(new GridLayout(5, 1, 1, 1));
		firstPanel.setLayout(new GridLayout(1, 3, 1, 1));
		thirdPanel.setLayout(new GridLayout(1, 3, 1, 1));
		fifthPanel.setLayout(new GridLayout(1, 3, 1, 1));

		enterKeywords = new JLabel("Enter keywords");
		keywordsArea = new JTextArea("");
		searchButton = new JButton("Search");

		firstPanel.add(enterKeywords);
		firstPanel.add(keywordsArea);
		firstPanel.add(searchButton);

		listProviders = new JTextArea();
		JScrollPane scrollPaneList = new JScrollPane(listProviders);

		bidValue = new JLabel("Enter bid value");
		bidValueArea = new JTextArea(10,20);
		bidButton = new JButton("Submit Bid");

		secondPanel.add(bidValue);
		secondPanel.add(bidValueArea);
		secondPanel.add(bidButton);

		bidAcceptance = new JTextArea();

		contract = new JLabel("Contract");
		contractArea = new JTextArea(10,20);
		acceptContractButton = new JButton("Accept Contract");
		rejectContractButton = new JButton("Reject Contract");

		buttonPanel.add(acceptContractButton);
		buttonPanel.add(rejectContractButton);

		thirdPanel.add(contract);
		thirdPanel.add(contractArea);
		thirdPanel.add(buttonPanel);

		overallPanel.add(firstPanel);
		overallPanel.add(scrollPaneList);
		overallPanel.add(secondPanel);
		overallPanel.add(bidAcceptance);
		overallPanel.add(thirdPanel);
		
		getContentPane().add(overallPanel);
		setSize(600, 600);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
