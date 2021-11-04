package com.proje.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.proje.helper.Helper;
import com.proje.model.HeadPhysician;
import com.proje.model.User;
import com.proje.model.UserType;
import com.proje.repository.UserRepository;
import com.proje.repository.impl.UserRepositoryImpl;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_patientTcno;
	private JPasswordField fld_patientPass;
	private JTextField fld_doctorTcno;
	private JPasswordField fld_doctorPass;
	private UserRepository userRepository = new UserRepositoryImpl();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("kit.png")));
		lblNewLabel.setBounds(204, 10, 73, 71);
		w_pane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Hastane Yönetim Sistemine Hoþgeldiniz");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(7, 90, 466, 33);
		w_pane.add(lblNewLabel_1);

		JTabbedPane w_tabPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabPane.setBounds(10, 133, 463, 220);
		w_pane.add(w_tabPane);

		JPanel w_patientLogin = new JPanel();
		w_patientLogin.setBackground(Color.WHITE);
		w_tabPane.addTab("Hasta Giriþi", null, w_patientLogin, null);
		w_patientLogin.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("T.C. Numaranýz :");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(55, 35, 173, 32);
		w_patientLogin.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Þifre :");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(55, 87, 173, 32);
		w_patientLogin.add(lblNewLabel_2_1);

		fld_patientTcno = new JTextField();
		fld_patientTcno.setBounds(212, 42, 183, 25);
		w_patientLogin.add(fld_patientTcno);
		fld_patientTcno.setColumns(10);

		fld_patientPass = new JPasswordField();
		fld_patientPass.setBounds(212, 94, 183, 25);
		w_patientLogin.add(fld_patientPass);

		JButton btn_registerPatient = new JButton("Kayýt Ol");
		btn_registerPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btn_registerPatient.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_registerPatient.setBounds(24, 138, 204, 32);
		w_patientLogin.add(btn_registerPatient);

		JButton btn_loginPatient = new JButton("Giriþ Yap");
		btn_loginPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_loginPatient.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_loginPatient.setBounds(238, 138, 204, 32);
		w_patientLogin.add(btn_loginPatient);

		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabPane.addTab("Doktor Giriþi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);

		JLabel lblNewLabel_2_2 = new JLabel("T.C. Numaranýz :");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_2.setBounds(55, 35, 173, 32);
		w_doctorLogin.add(lblNewLabel_2_2);

		fld_doctorTcno = new JTextField();
		fld_doctorTcno.setColumns(10);
		fld_doctorTcno.setBounds(212, 42, 183, 25);
		w_doctorLogin.add(fld_doctorTcno);

		JLabel lblNewLabel_2_1_1 = new JLabel("Þifre :");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1_1.setBounds(55, 87, 173, 32);
		w_doctorLogin.add(lblNewLabel_2_1_1);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(212, 94, 183, 25);
		w_doctorLogin.add(fld_doctorPass);

		JButton btn_loginDoctor = new JButton("Giriþ Yap");
		btn_loginDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTcno.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMessage("fill");
				} else {
					List<User> users = userRepository.getUserList();
					for (User user : users) {
						if (fld_doctorTcno.getText().equals(user.getTcno())
								&& fld_doctorPass.getText().equals(user.getPassword())) {
							if (user.getType().equals(UserType.bashekim)) {
								HeadPhysician headPhysician = new HeadPhysician();
								headPhysician.setId(user.getId());
								headPhysician.setName(user.getName());
								headPhysician.setPassword(user.getPassword());
								headPhysician.setTcno(user.getTcno());
								headPhysician.setType(user.getType());
								HeadPhysicianGUI headPhysicianGUI = new HeadPhysicianGUI(headPhysician);
								headPhysicianGUI.setVisible(true);
								dispose();
							}
						}
						
						
					}
				}
			}
		});
		btn_loginDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_loginDoctor.setBounds(24, 138, 418, 32);
		w_doctorLogin.add(btn_loginDoctor);
	}
}
