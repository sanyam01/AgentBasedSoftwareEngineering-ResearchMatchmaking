package matchpackage.access;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import matchpackage.afterMarket.ClientFeedbackGUI;
import matchpackage.afterMarket.ProviderFeedbackGUI;
import matchpackage.projectCreator.ClientChatGUI;
import matchpackage.projectCreator.ProviderChatGUI;
import matchpackage.tracker.ClientProjectGUI;
import matchpackage.tracker.ProviderProjectGUI;

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

	private JTextArea nameText;
	private JTextArea passwordText;
	private JTextArea resumeText;
	private JTextArea websiteText;
	private JTextArea logoText;
	private JTextArea keywordsText;
	private JTextArea hourlyCompensationText;

	private JComboBox<String> serviceComboBox;
	
	private ProviderGUI providerGui;
	private ClientChatGUI clientChatGUI;
	private ProviderChatGUI providerChatGUI;
	private ClientFeedbackGUI clientFeedbackGUI;
	private ProviderFeedbackGUI providerFeedbackGUI;
	private ProviderProjectGUI providerProjectGUI;
	private ClientProjectGUI clientProjectGUI;

	public SignUpGUI() {
		
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

		String[] choices = { "Provider", "Client" };

		serviceComboBox = new JComboBox<String>(choices);
		serviceComboBox.addActionListener(this);

		overall.setLayout(new GridLayout(8, 2, 1, 1));
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

		total.add(headerLabel);
		total.add(overall);
		total.add(submit);

		getContentPane().add(total);
		setTitle("Sign up Interface");
		setSize(400, 400);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		matchpackage.Bidding.Provider provider = null;

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
			}
			

		}

		if (e.getSource() == submit) {
			System.out.println("Submit button has been clicked");
			providerGui = new ProviderGUI(provider);
			clientChatGUI = new ClientChatGUI();
			providerChatGUI = new ProviderChatGUI();
			providerFeedbackGUI = new ProviderFeedbackGUI();
			clientFeedbackGUI = new ClientFeedbackGUI();
			providerProjectGUI = new ProviderProjectGUI();
			clientProjectGUI = new ClientProjectGUI();			
		}
	}

}