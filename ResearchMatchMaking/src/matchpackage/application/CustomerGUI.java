package matchpackage.application;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import matchpackage.access.CustomerAgent;

public class CustomerGUI extends JFrame implements ActionListener {
	
	private CustomerAgent customerAgent;
	private int selectedProvider;

	private JPanel overallPanel, firstPanel, secondPanel, thirdPanel;
	private JPanel fourthPanel, fifthPanel, buttonPanel, sixthPanel;

	private JLabel enterKeywords;
	private JTextArea keywordsArea;
	private JButton searchButton;

	private JTextArea listProviders;
	DefaultTableModel tableModel;
	String[] columnNames = { "Name", "Website", "Logo", "Keywords", "Resume", "Compensation" };
	private JScrollPane scrollProviders;
	private JTable providerTable;

	private JLabel bidValue;
	private JTextArea bidValueArea;
	private JButton bidButton;

	private JTextArea bidAcceptance;

	private JLabel contract;
	private JTextArea contractArea;
	private JButton acceptContractButton;
	private JButton rejectContractButton;
	private int selectedRow;
	private String providerName;
	
	//Adding mingrui Code...........
	//..............................
	private JButton trackingButton;
	private JButton trackingAndFeedback;
	private JButton ifChange;
	private JTextArea changeText;

	public CustomerGUI(CustomerAgent customerAgent) {
		
		this.customerAgent = customerAgent;

		overallPanel = new JPanel();
		firstPanel = new JPanel();
		secondPanel = new JPanel();
		thirdPanel = new JPanel();
		fourthPanel = new JPanel();
		fifthPanel = new JPanel();
		sixthPanel = new JPanel();
		buttonPanel = new JPanel(new FlowLayout());

		overallPanel.setLayout(new GridLayout(6, 1, 1, 1));
		firstPanel.setLayout(new GridLayout(1, 3, 1, 1));
		thirdPanel.setLayout(new GridLayout(1, 3, 1, 1));
		fifthPanel.setLayout(new GridLayout(1, 3, 1, 1));

		enterKeywords = new JLabel("Enter keywords");
		keywordsArea = new JTextArea("");
		searchButton = new JButton("Search");
		searchButton.addActionListener(this);

		firstPanel.add(enterKeywords);
		firstPanel.add(keywordsArea);
		firstPanel.add(searchButton);

		listProviders = new JTextArea();
		providerTable = new JTable();
		tableModel = (DefaultTableModel) providerTable.getModel();
		tableModel.setColumnIdentifiers(columnNames);
		scrollProviders = new JScrollPane(providerTable);
		
		
		
		ListSelectionModel model = providerTable.getSelectionModel();
		
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!(model.isSelectionEmpty()))
				   selectedRow = model.getMinSelectionIndex();
				System.out.println(model.toString());
				System.out.println("Row has been selected........................");
				System.out.println("Row no is " + selectedRow);
				selectedProvider = selectedRow;
					
			}
			
		});
		
		bidValue = new JLabel("Enter bid value");
		bidValueArea = new JTextArea(10, 20);
		bidButton = new JButton("Submit Bid");
		bidButton.addActionListener(this);

		secondPanel.add(bidValue);
		secondPanel.add(bidValueArea);
		secondPanel.add(bidButton);

		bidAcceptance = new JTextArea();

		contract = new JLabel("Contract");
		contractArea = new JTextArea(10, 20);
		acceptContractButton = new JButton("Accept Contract");
		rejectContractButton = new JButton("Reject Contract");
		acceptContractButton.addActionListener(this);
		rejectContractButton.addActionListener(this);

		buttonPanel.add(acceptContractButton);
		buttonPanel.add(rejectContractButton);

		thirdPanel.add(contract);
		thirdPanel.add(contractArea);
		thirdPanel.add(buttonPanel);
		
		//Mingrui Code............................
		//........................................
		
		trackingAndFeedback = new JButton("trakingAndFeedback");
		ifChange = new JButton("change?");
		changeText= new JTextArea(10,20);
		ifChange.addActionListener(this);
		trackingAndFeedback.addActionListener(this);
		
		sixthPanel.add(trackingAndFeedback);
		sixthPanel.add(ifChange);
		sixthPanel.add(changeText);
		sixthPanel.setBackground(Color.gray);

		overallPanel.add(firstPanel);
		overallPanel.add(scrollProviders);
		overallPanel.add(secondPanel);
		overallPanel.add(bidAcceptance);
		overallPanel.add(thirdPanel);
		overallPanel.add(sixthPanel);

		getContentPane().add(overallPanel);
		setTitle("Customer Interface");
		setSize(600, 600);
		setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == searchButton) {
			String keywords = keywordsArea.getText();
			customerAgent.setKeywords(keywords);
			
		}
		
		if(e.getSource() == bidButton) {
			System.out.println(providerTable.getValueAt(selectedRow, 0));
			String providerName = providerTable.getValueAt(selectedRow, 0).toString();
			String bidValue = bidValueArea.getText();
			Double bidValues = Double.parseDouble(bidValue);
		   customerAgent.placeBid (providerName, bidValues);
			
			
		}
		
		if (e.getSource()==acceptContractButton){
			
			//this.contractArea.setText("The contract has been accepted");
			customerAgent.afterAcceptingContract("ACCEPT");
			
		}

		if (e.getSource()==rejectContractButton){
			
			//this.contractArea.setText("The contract has been rejected");
			customerAgent.afterAcceptingContract("REJECT");
		}
		

	}
	
	public void showBidderStatus(String info){
		bidAcceptance.setText(info);
	}
	public void showBiddingResults(String info){
		this.contractArea.setText(info);
	}
	
	public void showChangeResults(String info){
		this.changeText.setText(info);
	}
	
//////.........................................
		//.............................................End
	

	public void showGUI() {
		this.setVisible(true);
	}

	public void setContentListProvider(String content) {

		tableModel.setRowCount(0);
		tableModel.fireTableDataChanged();
		System.out.println("I am in here updating the table in GUI agent");

		String[] listRows = content.split("\n");

		int numRows = (int) listRows.length;

		String[][] providerData = new String[numRows][5];

		for (int i = 0; i < numRows; i++) {
			String[] data = listRows[i].split("\\*");


			tableModel.addRow(data);

		}

		tableModel.fireTableDataChanged();

		System.out.println("I am getting the content to set " + content);
		//providerTable.repaint();

	}
	
	
	public void tableRepaint() {
		//providerTable.repaint();
		tableModel.fireTableDataChanged();
	}
	
	public JTable getProviderTable() {
		return providerTable;
	}
	
	public void setTextContract(String content) {
		contractArea.setText(content);
	}
	
	public void setBidValueArea(String content) {
		bidValueArea.setText(content);
	}

	

}
