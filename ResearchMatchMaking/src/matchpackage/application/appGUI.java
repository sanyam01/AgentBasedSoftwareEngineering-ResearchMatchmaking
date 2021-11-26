package matchpackage.application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class appGUI extends JFrame implements ActionListener {
	
	private JButton mainButton = new JButton("Please click here to access the application");
	
	appGUI(){
		getContentPane().add(mainButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
