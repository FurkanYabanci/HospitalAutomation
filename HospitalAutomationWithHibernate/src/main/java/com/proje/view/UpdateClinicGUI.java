package com.proje.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.proje.helper.Helper;
import com.proje.model.Clinic;
import com.proje.repository.ClinicRepository;
import com.proje.repository.impl.ClinicRepositoryImpl;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_editClinicName;
	private ClinicRepository clinicRepository = new ClinicRepositoryImpl();
	static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Poliklinik Adý");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(10, 10, 191, 32);
		contentPane.add(lblNewLabel_2_1);
		
		fld_editClinicName = new JTextField();
		fld_editClinicName.setColumns(10);
		fld_editClinicName.setBounds(10, 37, 191, 25);
		fld_editClinicName.setText(clinic.getName());
		contentPane.add(fld_editClinicName);
		
		JButton btn_editClinic = new JButton("Düzenle");
		btn_editClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					try {
						clinicRepository.updateClinic(clinic.getId(), fld_editClinicName.getText());
						Helper.showMessage("Ýþlem baþarýlý");
						dispose();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});
		btn_editClinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_editClinic.setBounds(10, 72, 191, 32);
		contentPane.add(btn_editClinic);
	}

}
