package com.proje.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.proje.helper.Helper;
import com.proje.repository.UserRepository;
import com.proje.repository.impl.UserRepositoryImpl;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private UserRepository userRepository = new UserRepositoryImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Ad Soyad");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(10, 10, 183, 32);
		w_pane.add(lblNewLabel_2);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 37, 266, 25);
		w_pane.add(fld_name);
		
		JLabel lblNewLabel_2_1 = new JLabel("T.C. Numaranýz");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(10, 72, 183, 32);
		w_pane.add(lblNewLabel_2_1);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 99, 266, 25);
		w_pane.add(fld_tcno);
		
		JLabel lblNewLabel_4 = new JLabel("Þifre");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(10, 134, 183, 32);
		w_pane.add(lblNewLabel_4);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 161, 266, 25);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Kayýt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_name.getText().length() == 0 || fld_pass.getText().length() == 0) {
					Helper.showMessage("fill");
				} else if (fld_tcno.getText().length() != 11 || fld_tcno.getText().length() == 0) {
					Helper.showMessage("Lütfen TC numaranýzý 11 haneli giriniz!");
				}
				else {
					boolean control = userRepository.registerPatient(fld_tcno.getText(),fld_pass.getText(),fld_name.getText());
					if (control) {
						Helper.showMessage("success");
						LoginGUI loginGUI = new LoginGUI();
						loginGUI.setVisible(true);
						dispose();
					}else {
						Helper.showMessage("Bu TC numarasýna ait kullanýcý mevcut!");
					}
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_register.setBounds(10, 212, 266, 32);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_backto.setBounds(10, 254, 266, 32);
		w_pane.add(btn_backto);
	}
}
