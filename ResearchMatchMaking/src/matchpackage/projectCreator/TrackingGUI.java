package matchpackage.projectCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackingGUI extends JFrame implements ActionListener {
    private JLabel clientLabel;
    private JTextArea chatArea;
    private JScrollPane scrollPane;
    private JPanel jPanel;

    public TrackingGUI() {
        JPanel overallPanel;
        JPanel firstPanel;
        JPanel secondPanel;
        JPanel thirdPanel;
        JPanel fourthPanel, fifthPanel,sixthPanel, buttonPanel;


        overallPanel = new JPanel();
        firstPanel = new JPanel();
        secondPanel = new JPanel();
        thirdPanel = new JPanel();
        buttonPanel = new JPanel(new FlowLayout());

        overallPanel.setLayout(new GridLayout(3, 1, 1, 1));
        firstPanel.setLayout(new GridLayout(1, 3, 1, 1));
        secondPanel.setLayout(new GridLayout(1, 3, 1, 1));
        thirdPanel.setLayout(new GridLayout(1, 3, 1, 1));

        JButton enterKeywords = new JButton("Get Status");
        JTextArea statusArea = new JTextArea("");


        firstPanel.add(enterKeywords);
        firstPanel.add(statusArea);
        firstPanel.setBackground(Color.gray);

        JButton submitButton = new JButton("Submit");
        JTextArea statusArea2 = new JTextArea("");


        secondPanel.add(submitButton);
        secondPanel.add(statusArea2);
        secondPanel.setBackground(Color.gray);

        JButton change = new JButton("Change DDL?");
        JTextArea statusArea3 = new JTextArea("");


        thirdPanel.add(change);
        thirdPanel.add(statusArea3);
        thirdPanel.setBackground(Color.gray);

        overallPanel.add(firstPanel);
        overallPanel.add(secondPanel);
        overallPanel.add(thirdPanel);

        getContentPane().add(overallPanel);
        setTitle("Customer Interface");
        setSize(600, 600);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
