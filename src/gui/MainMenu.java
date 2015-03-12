package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JSpinner;

import java.awt.FlowLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainMenu frame = new MainMenu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setTitle("Auto Registration System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{286, 286, 0};
		gbl_contentPane.rowHeights = new int[]{148, 148, 148, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton vehicle_reg_btn = new JButton("New Vehicle Registration");
		vehicle_reg_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new VehicleRegPanel();
				dialog.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
				dialog.setTitle("New Vehicle Registration Form");
				dialog.pack();
				dialog.setVisible(true);
			}
		});
		
		GridBagConstraints gbc_vehicle_reg_btn = new GridBagConstraints();
		gbc_vehicle_reg_btn.fill = GridBagConstraints.BOTH;
		gbc_vehicle_reg_btn.insets = new Insets(0, 0, 5, 5);
		gbc_vehicle_reg_btn.gridx = 0;
		gbc_vehicle_reg_btn.gridy = 0;
		contentPane.add(vehicle_reg_btn, gbc_vehicle_reg_btn);
		
		JButton auto_trans_btn = new JButton("Auto Transaction");
		GridBagConstraints gbc_auto_trans_btn = new GridBagConstraints();
		gbc_auto_trans_btn.fill = GridBagConstraints.BOTH;
		gbc_auto_trans_btn.insets = new Insets(0, 0, 5, 0);
		gbc_auto_trans_btn.gridx = 1;
		gbc_auto_trans_btn.gridy = 0;
		contentPane.add(auto_trans_btn, gbc_auto_trans_btn);
		
		JButton drive_lic_btn = new JButton("Driver Licence Registration");
		drive_lic_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_drive_lic_btn = new GridBagConstraints();
		gbc_drive_lic_btn.fill = GridBagConstraints.BOTH;
		gbc_drive_lic_btn.insets = new Insets(0, 0, 5, 5);
		gbc_drive_lic_btn.gridx = 0;
		gbc_drive_lic_btn.gridy = 1;
		contentPane.add(drive_lic_btn, gbc_drive_lic_btn);
		
		JButton viol_rec_btn = new JButton("Record Violation");
		GridBagConstraints gbc_viol_rec_btn = new GridBagConstraints();
		gbc_viol_rec_btn.fill = GridBagConstraints.BOTH;
		gbc_viol_rec_btn.insets = new Insets(0, 0, 5, 0);
		gbc_viol_rec_btn.gridx = 1;
		gbc_viol_rec_btn.gridy = 1;
		contentPane.add(viol_rec_btn, gbc_viol_rec_btn);
		
		JButton search_btn = new JButton("Search");
		GridBagConstraints gbc_search_btn = new GridBagConstraints();
		gbc_search_btn.gridwidth = 2;
		gbc_search_btn.fill = GridBagConstraints.BOTH;
		gbc_search_btn.insets = new Insets(0, 0, 0, 5);
		gbc_search_btn.gridx = 0;
		gbc_search_btn.gridy = 2;
		contentPane.add(search_btn, gbc_search_btn);
	}

}
