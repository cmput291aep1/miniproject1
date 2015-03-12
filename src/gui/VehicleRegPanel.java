package gui;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Component;

public class VehicleRegPanel extends JDialog {
	private JTextField txtTest;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public VehicleRegPanel() {
		setTitle("New Vehicle Registration");
		Dimension dim = new Dimension();
		dim.setSize(500, 600);
		setPreferredSize(dim);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		//panel.setPreferredSize(new Dimension(100,50));
		getContentPane().add(panel);
		
		txtTest = new JTextField();
		txtTest.setText("Test");
		txtTest.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(txtTest);
		txtTest.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		
		textField_3 = new JTextField();
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		
	}

}
