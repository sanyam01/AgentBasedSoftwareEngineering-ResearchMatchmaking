package matchpackage.access;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SignUpGUI extends JFrame implements ActionListener {

	private JLabel name;
	private JLabel password;
	private JLabel resume;
	private JLabel website;
	private JLabel logo;
	private JLabel keywords;
	private JLabel service;
	private JPanel right, left, overall;
	
	private JTextArea nameText;
	private JTextArea passwordText;
	private JTextArea resumeText;
	private JTextArea websiteText;
	private JTextArea logoText;
	private JTextArea keywordsText;
	
	private JComboBox<String> serviceComboBox;
	
	
	public SignUpGUI() {
		
		overall = new JPanel();
		left = new JPanel();
		right = new JPanel();
		
		name = new JLabel("Name");
		password = new JLabel("Password");
		resume = new JLabel("Resume");
		website = new JLabel("Website");
		logo = new JLabel("Logo");
		keywords = new JLabel("Keywords");
		service = new JLabel("Service");
		
		nameText = new JTextArea("");
		passwordText = new JTextArea("");
		resumeText = new JTextArea("");
		websiteText = new JTextArea("");
		logoText = new JTextArea("");
		keywordsText = new JTextArea("");
		
		String[] choices = {"Client", "Provider"};
		
		serviceComboBox = new JComboBox<String>(choices);
		
		left = new JPanel(new BoxLayout(left, BoxLayout.Y_AXIS));
		left.add(name);
		left.add(password);
		left.add(service);
		left.add(resume);
		left.add(website);
		left.add(logo);
		left.add(keywords);
		
		right = new JPanel(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(nameText);
		right.add(passwordText);
		right.add(serviceComboBox);
		right.add(resumeText);
		right.add(websiteText);
		right.add(logoText);
		right.add(keywordsText);
		
		overall.setLayout(new BoxLayout(overall, BoxLayout.X_AXIS));
		overall.add(left);
		overall.add(right);
		
		getContentPane().add(overall);
		setSize(300, 300);
		setVisible(true);
		

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
