package matchpackage.application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;

import javax.swing.*;

public class ProviderGUI extends JFrame implements ActionListener {

	private JButton logOut;
	private JTextArea listProviders;
	private JComboBox<String> planComboBox;
	private JButton submitPlan;
	private JTextArea verifiedIcon;
	private JButton submitIcon;
	private JLabel listLabel;
	private JLabel planLabel;
	private JLabel iconLabel;
	private JScrollPane scrollPaneProviders;
	private JTextArea verifiedIconLabel;
	

	private JLabel bids;
	private JTextArea bidText;
	private JButton acceptBid;
	private JButton rejectBid;

	private JLabel contract;
	private JTextArea contractText;
	private JButton acceptContract;
	private JButton rejectContract;

	private JPanel jPanel1, jPanel2, jPanel3, jPanel4, jPanel5, overallJPanel;

	public ProviderGUI() {

		
		jPanel1 = new JPanel(new FlowLayout());
		jPanel2 = new JPanel(new FlowLayout());
		jPanel3 = new JPanel(new FlowLayout());
		jPanel4 = new JPanel(new FlowLayout());
		jPanel5 = new JPanel(new FlowLayout());
		overallJPanel = new JPanel();
		overallJPanel.setLayout(new BoxLayout(overallJPanel, BoxLayout.PAGE_AXIS));

		listLabel = new JLabel("List of providers");
		listProviders = new JTextArea(5,30);
		logOut = new JButton("Log Out");
		
		scrollPaneProviders = new JScrollPane(listProviders);

//		jPanel1.add(listLabel);
//		jPanel1.add(scrollPaneProviders);
		jPanel1.add(logOut);

		String[] choices = { "Basic", "Premium" };

		planLabel = new JLabel("Please choose the plan");
		planComboBox = new JComboBox<String>(choices);
		submitPlan = new JButton("Submit");

		jPanel2.add(planLabel);
		jPanel2.add(planComboBox);
		jPanel2.add(submitPlan);

		iconLabel = new JLabel("Please submit  verified proof of business");
		verifiedIcon = new JTextArea("",5,30);
		submitIcon = new JButton("Submit Icon");
		verifiedIconLabel = new JTextArea("",5,30);
		

		jPanel3.add(iconLabel);
		jPanel3.add(verifiedIcon);
		jPanel3.add(submitIcon);
		jPanel3.add(verifiedIconLabel);

		bids = new JLabel("Bids");
		bidText = new JTextArea("",5,30);
		acceptBid = new JButton("Accept");
		rejectBid = new JButton("Reject");

		jPanel4.add(bids);
		jPanel4.add(bidText);
		jPanel4.add(acceptBid);
		jPanel4.add(rejectBid);

		contract = new JLabel("Contract");
		contractText = new JTextArea("",5,30);
		acceptContract = new JButton("Accept");
		rejectContract = new JButton("Reject");

		jPanel5.add(contract);
		jPanel5.add(contractText);
		jPanel5.add(acceptContract);
		jPanel5.add(rejectContract);

		overallJPanel.add(jPanel1);
		overallJPanel.add(jPanel2);
		overallJPanel.add(jPanel3);
		overallJPanel.add(jPanel4);
		overallJPanel.add(jPanel5);

		getContentPane().add(overallJPanel);
		setTitle("Provider Interface");
		setSize(800, 800);
		setVisible(false);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void showGUI() {
		this.setVisible(true);
	}

}
