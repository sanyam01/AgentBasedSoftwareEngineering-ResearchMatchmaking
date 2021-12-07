package matchpackage.access;

import jade.core.Agent;
import matchpackage.Bidding.Bidder;
import matchpackage.projectCreator.ClientChatGUI;
import matchpackage.projectCreator.TrackingGUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CustomerGUI extends JFrame implements ActionListener {

	private matchpackage.Bidding.Bidder bidding;
//	private matchpackage.Bidding.Reveiver price;

	private JPanel overallPanel, firstPanel, secondPanel, thirdPanel;
	private JPanel fourthPanel, fifthPanel,sixthPanel, buttonPanel;

	private JLabel enterKeywords;
	private JTextArea keywordsArea;
	private JButton searchButton;

	private JTextArea listProviders;

	private JLabel bidValue;
	private JLabel providerValue;
	private JTextArea bidValueArea;
	private JTextArea providerID;
	private JButton bidButton;

	private JTextArea bidAcceptance;

	private JLabel contract;
	private JTextArea contractArea;
	private JButton acceptContractButton;
	private JButton rejectContractButton;
	private JButton trackingButton;

	private JButton trakingAndFeedback;
	private JButton ifchange;
	private JTextArea changeText;
	public CustomerGUI(matchpackage.Bidding.Bidder bidder) {
		this.bidding= bidder;


		overallPanel = new JPanel();
		firstPanel = new JPanel();
		secondPanel = new JPanel();
		thirdPanel = new JPanel();
		fourthPanel = new JPanel();
		fifthPanel = new JPanel();
		sixthPanel= new JPanel();
		buttonPanel = new JPanel(new FlowLayout());

		overallPanel.setLayout(new GridLayout(6, 1, 1, 1));
		firstPanel.setLayout(new GridLayout(1, 3, 1, 1));
		thirdPanel.setLayout(new GridLayout(1, 3, 1, 1));
		sixthPanel.setLayout(new GridLayout(1, 3, 1, 1));

		enterKeywords = new JLabel("Enter keywords");
		keywordsArea = new JTextArea("");
		searchButton = new JButton("Search");

		firstPanel.add(enterKeywords);
		firstPanel.add(keywordsArea);
		firstPanel.add(searchButton);
		firstPanel.setBackground(Color.gray);

		listProviders = new JTextArea();
		JScrollPane scrollPaneList = new JScrollPane(listProviders);

		bidValue = new JLabel("Enter bid value");
		bidValueArea = new JTextArea(10,10);
		providerValue = new JLabel("Enter provider ID");
		providerID = new JTextArea(10,10);
		bidButton = new JButton("Submit Bid");


		secondPanel.add(bidValue);
		secondPanel.add(bidValueArea);
		secondPanel.add(providerValue);
		secondPanel.add(providerID);
		secondPanel.add(bidButton);
		secondPanel.setBackground(Color.gray);

		bidAcceptance = new JTextArea();
		bidButton.addActionListener(this);
		bidAcceptance.setBackground(Color.lightGray);



		contract = new JLabel("Contract");
		contractArea = new JTextArea(10,20);
		acceptContractButton = new JButton("Accept Contract");
		acceptContractButton.addActionListener(this);
		rejectContractButton = new JButton("Reject Contract");
		rejectContractButton.addActionListener(this);

		trakingAndFeedback = new JButton("trakingAndFeedback");
		ifchange = new JButton("change?");
		changeText= new JTextArea(10,20);
		ifchange.addActionListener(this);
		trakingAndFeedback.addActionListener(this);

		buttonPanel.add(acceptContractButton);
		buttonPanel.add(rejectContractButton);

		thirdPanel.add(contract);
		thirdPanel.add(contractArea);
		thirdPanel.add(buttonPanel);
		thirdPanel.setBackground(Color.gray);
//
		sixthPanel.add(trakingAndFeedback);
		sixthPanel.add(ifchange);
		sixthPanel.add(changeText);
		sixthPanel.setBackground(Color.gray);


		overallPanel.add(firstPanel);
		overallPanel.add(scrollPaneList);
		overallPanel.add(secondPanel);
		overallPanel.add(bidAcceptance);
		overallPanel.add(thirdPanel);
		overallPanel.add(sixthPanel);
		
		getContentPane().add(overallPanel);
		setTitle("Customer Interface");
		setSize(600, 600);
		setVisible(true);

	}

	public void showBidderStatus(String info){
		this.bidAcceptance.setText(info);
	}
	public void showBiddingResults(String info){
		this.contractArea.setText(info);
	}
	public void showChangeResults(String info){
		this.changeText.setText(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		if (e.getSource() == bidButton) {
			// ProviderGUI providerGui = new ProviderGUI();
			float value;

			try{
				value = Float.parseFloat(bidValueArea.getText());
				System.out.println(value);
				this.bidding.startBidding(value);

			}catch(NumberFormatException err){
				JOptionPane.showMessageDialog(this.overallPanel,err.getMessage(),"Your input is not correct!",
						JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource()==ifchange){
			this.changeText.setText("The change request is sending");
		}
		if (e.getSource()==acceptContractButton){
			this.contractArea.setText("The contract has been accepted");
			ClientChatGUI clientGui = new ClientChatGUI();
		}

		if (e.getSource()==rejectContractButton){
//			this.contractArea.setText("The contract has been accepted");
			this.contractArea.setText("The contract has been rejected");
		}
		if (e.getSource()==trakingAndFeedback){

			TrackingGUI trackingGUI = new TrackingGUI();
		}
	}

}
