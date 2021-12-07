package matchpackage.access;

import matchpackage.projectCreator.ClientChatGUI;
import matchpackage.projectCreator.ProviderChatGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ProviderGUI extends JFrame implements ActionListener {
	private matchpackage.Bidding.Provider provider;

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
	private JButton showBid;

	private JLabel contract;
	private JTextArea contractText;
	private JButton acceptContract;
	private JButton rejectContract;

	private JPanel jPanel1, jPanel2, jPanel3, jPanel4, jPanel5, overallJPanel;

	private String accepted = "Undecided";

	public ProviderGUI( matchpackage.Bidding.Provider provider) {

		this.provider=provider;
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

		jPanel1.add(listLabel);
		jPanel1.add(scrollPaneProviders);
		jPanel1.add(logOut);
		jPanel1.setBackground(Color.gray);

		String[] choices = { "Basic", "Premium" };

		planLabel = new JLabel("Please choose the plan");
		planComboBox = new JComboBox<String>(choices);
		submitPlan = new JButton("Submit");

		jPanel2.add(planLabel);
		jPanel2.add(planComboBox);
		jPanel2.add(submitPlan);
		jPanel2.setBackground(Color.gray);

		iconLabel = new JLabel("Please submit\n  verified proof of business");
		verifiedIcon = new JTextArea("",5,30);
		submitIcon = new JButton("Submit Icon");
		verifiedIconLabel = new JTextArea("",5,30);
		

		jPanel3.add(iconLabel);
		jPanel3.add(verifiedIcon);
		jPanel3.add(submitIcon);
		jPanel3.add(verifiedIconLabel);
		jPanel3.setBackground(Color.gray);

		bids = new JLabel("Bids");
		bidText = new JTextArea("",5,30);

//		bidText.setText(this.provider.getProviderPrice());
		acceptBid = new JButton("Accept");
		showBid = new JButton("showBid");
		rejectBid = new JButton("Reject");
		showBid.addActionListener(this);
		acceptBid.addActionListener(this);
		rejectBid.addActionListener(this);

		jPanel4.add(bids);
		jPanel4.add(bidText);
		jPanel4.add(showBid);
		jPanel4.add(acceptBid);

		jPanel4.add(rejectBid);
		jPanel4.setBackground(Color.gray);

		contract = new JLabel("Contract");
		contractText = new JTextArea("",5,30);

		acceptContract = new JButton("Accept");
		rejectContract = new JButton("Reject");

		jPanel5.add(contract);
		jPanel5.add(contractText);
		jPanel5.add(acceptContract);
		jPanel5.add(rejectContract);
		jPanel5.setBackground(Color.gray);

		overallJPanel.add(jPanel1);
		overallJPanel.add(jPanel2);
		overallJPanel.add(jPanel3);
		overallJPanel.add(jPanel4);
		overallJPanel.add(jPanel5);

		getContentPane().add(overallJPanel);
		setTitle("Provider Interface");
		setSize(800, 800);
		setVisible(true);
		

	}

	public String getAccepted(){
		return this.accepted;
	}

	public void setAccepted(String newStatus){
		this.accepted = newStatus;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource()==showBid){

			this.bidText.setText(this.provider.getProviderPrice());

		}
		if (e.getSource()==acceptBid){
			this.accepted = "Accept";

			this.bidText.setText(this.provider.getProviderPrice());
			this.contractText.setText("The system will receive 30% of\\n\" +\n" +
					" \"any transaction\"");


		}
		if (e.getSource()==rejectBid){
			this.accepted = "Rejected";

			this.bidText.setText("the provider reject the bidding");

		}
		if (e.getSource()==acceptContract){
			this.contractText.setText("The contract has been accepted");
			ProviderChatGUI providerChatGUI = new ProviderChatGUI();


		}

}}
