package matchpackage.application;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import matchpackage.contract.ClientChatGUI;
import matchpackage.contract.ClientFeedbackGUI;
import matchpackage.contract.ClientProjectGUI;
import matchpackage.contract.ProviderChatGUI;
import matchpackage.contract.ProviderFeedbackGUI;
import matchpackage.contract.ProviderProjectGUI;

public class SignUpGUI extends JFrame implements ActionListener {

	private JLabel name;
	private JLabel password;
	private JLabel resume;
	private JLabel website;
	private JLabel logo;
	private JLabel keywords;
	private JLabel service;
	private JLabel headerLabel;
	private JLabel hourlyCompensation;
	private JButton submit;
	private JPanel header, bottom, overall, total;
	private JLabel planLabel;

	private JTextArea nameText;
	private JTextArea passwordText;
	private JTextArea resumeText;
	private JTextArea websiteText;
	private JTextArea logoText;
	private JTextArea keywordsText;
	private JTextArea hourlyCompensationText;
	private JTextArea contractText;

	private JComboBox<String> planComboBox;

	private JComboBox<String> serviceComboBox;

	private JComboBox<String> contractComboBox;

	private ProviderGUI providerGui;
	private ClientChatGUI clientChatGUI;
	private ProviderChatGUI providerChatGUI;
	private ClientFeedbackGUI clientFeedbackGUI;
	private ProviderFeedbackGUI providerFeedbackGUI;
	private ProviderProjectGUI providerProjectGUI;
	private ClientProjectGUI clientProjectGUI;
	private GUIAgent guiAgent;

	public SignUpGUI(GUIAgent guiAgent) {

		this.guiAgent = guiAgent;

		overall = new JPanel();
		total = new JPanel();
		total.setLayout(new BoxLayout(total, BoxLayout.PAGE_AXIS));

		headerLabel = new JLabel("Please enter the details for signing up");
		submit = new JButton("Submit");
		submit.addActionListener(this);

		name = new JLabel("Name");
		password = new JLabel("Password");
		resume = new JLabel("Resume");
		website = new JLabel("Website");
		logo = new JLabel("Logo");
		keywords = new JLabel("Keywords");
		service = new JLabel("Service");
		hourlyCompensation = new JLabel("Hourly Compensation");

		nameText = new JTextArea("");
		passwordText = new JTextArea("");
		resumeText = new JTextArea("");
		websiteText = new JTextArea("");
		logoText = new JTextArea("");
		keywordsText = new JTextArea("");
		hourlyCompensationText = new JTextArea("");
		contractText = new JTextArea("");

		String[] choices = { "Provider", "Client" };

		String[] choicesPlan = { "Basic", "Premium" };

		String[] contractOption = { "Accept", "Reject" };

		planLabel = new JLabel("Please choose the plan");
		planComboBox = new JComboBox<String>(choicesPlan);

		contractComboBox = new JComboBox<String>(contractOption);
		contractComboBox.addActionListener(this);

		serviceComboBox = new JComboBox<String>(choices);
		serviceComboBox.addActionListener(this);

		overall.setLayout(new GridLayout(10, 2, 1, 1));
		overall.add(name);
		overall.add(nameText);

		overall.add(password);
		overall.add(passwordText);

		overall.add(service);
		overall.add(serviceComboBox);

		overall.add(resume);
		overall.add(resumeText);

		overall.add(website);
		overall.add(websiteText);

		overall.add(logo);
		overall.add(logoText);

		overall.add(keywords);
		overall.add(keywordsText);

		overall.add(hourlyCompensation);
		overall.add(hourlyCompensationText);

		overall.add(planLabel);
		overall.add(planComboBox);

		overall.add(contractText);
		overall.add(contractComboBox);

		total.add(headerLabel);
		total.add(overall);
		total.add(submit);

		getContentPane().add(total);
		setTitle("Sign up Interface");
		setSize(400, 400);
		setVisible(true);
		System.out.println("This is sign up graphical user interface");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == serviceComboBox) {

			String x = serviceComboBox.getSelectedItem().toString();

			if (x.equals("Client")) {

				resumeText.setText("NA");
				websiteText.setText("NA");
				logoText.setText("NA");
				keywordsText.setText("NA");

				resumeText.setEditable(false);
				websiteText.setEditable(false);
				logoText.setEditable(false);
				keywordsText.setEditable(false);
				planComboBox.setEditable(false);
				contractComboBox.setEditable(false);
			}

			if (x.equals("Provider")) {

				resumeText.setText("");
				websiteText.setText("");
				logoText.setText("");
				keywordsText.setText("");

				resumeText.setEditable(true);
				websiteText.setEditable(true);
				logoText.setEditable(true);
				keywordsText.setEditable(true);
				planComboBox.setEditable(true);
				contractComboBox.setEditable(true);
			}

		}

		if (e.getSource() == submit) {
			System.out.println("Submit button has been clicked");

			String value = serviceComboBox.getSelectedItem().toString();

			if (value.contentEquals("Provider")) {

				contractText.setText("Please accept the contract");
			}
			else {
				this.guiAgent.createCustomerAgent(nameText.getText(), passwordText.getText());
			}
		}

		if (e.getSource() == contractComboBox) {

			String value = serviceComboBox.getSelectedItem().toString();

			String valueContract = contractComboBox.getSelectedItem().toString();
			if (valueContract.contentEquals("Reject")) {
				value = "Client";
			}

			if (value.contentEquals("Client")) {
				System.out.println("It is a client");
				this.guiAgent.createCustomerAgent(nameText.getText(), passwordText.getText());
			} else {
				String valuePlan = planComboBox.getSelectedItem().toString();
				System.out.println("It is a provider");
				Double compensation = Double.parseDouble(hourlyCompensationText.getText());
				ArrayList<String> keywordsArray = new ArrayList<String>();
				String[] data = keywordsText.getText().split(",");
				for (String i : data)
					keywordsArray.add(i);
				this.guiAgent.createProviderAgent(nameText.getText(), passwordText.getText(), websiteText.getText(),
						logoText.getText(), compensation, keywordsArray, resumeText.getText(), valuePlan);
			}

		}
	}
}
