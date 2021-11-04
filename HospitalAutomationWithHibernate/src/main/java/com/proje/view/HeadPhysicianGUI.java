package com.proje.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.proje.helper.Helper;
import com.proje.helper.Item;
import com.proje.model.HeadPhysician;
import com.proje.repository.UserRepository;
import com.proje.repository.impl.UserRepositoryImpl;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class HeadPhysicianGUI extends JFrame {

	private JPanel w_pane;
	static HeadPhysician headPhysician = new HeadPhysician();
	private JTextField fld_doctorName;
	private JTextField fld_doctorTcno;
	private JTextField fld_doctorID;
	private JPasswordField fld_doctorPass;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private UserRepository userRepository = new UserRepositoryImpl();
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private JTable table_worker;
	private JComboBox select_doctor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeadPhysicianGUI frame = new HeadPhysicianGUI(headPhysician);
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
	public HeadPhysicianGUI(HeadPhysician headPhysician) {

		// table_doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC No";
		colDoctorName[3] = "�ifre";
		doctorModel.setColumnIdentifiers(colDoctorName);

		doctorData = new Object[4];
		for (int i = 0; i < userRepository.getDoctorList().size(); i++) {
			doctorData[0] = userRepository.getDoctorList().get(i).getId();
			doctorData[1] = userRepository.getDoctorList().get(i).getName();
			doctorData[2] = userRepository.getDoctorList().get(i).getTcno();
			doctorData[3] = userRepository.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}


		setResizable(false);
		setTitle("Hastane Y�netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho�geldiniz,Say�n " + headPhysician.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 10, 250, 34);
		w_pane.add(lblNewLabel);

		JButton btn_out = new JButton("��k�� Yap");
		btn_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_out.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		btn_out.setBounds(499, 10, 227, 37);
		w_pane.add(btn_out);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 70, 716, 383);
		w_pane.add(tabbedPane);

		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Y�netimi", null, w_doctor, null);
		w_doctor.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Ad Soyad");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(518, 14, 183, 32);
		w_doctor.add(lblNewLabel_2);

		fld_doctorName = new JTextField();
		fld_doctorName.setColumns(10);
		fld_doctorName.setBounds(518, 41, 183, 25);
		w_doctor.add(fld_doctorName);

		JLabel lblNewLabel_3 = new JLabel("T.C. No");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(518, 74, 183, 32);
		w_doctor.add(lblNewLabel_3);

		fld_doctorTcno = new JTextField();
		fld_doctorTcno.setColumns(10);
		fld_doctorTcno.setBounds(518, 101, 183, 25);
		w_doctor.add(fld_doctorTcno);

		JLabel lblNewLabel_4 = new JLabel("�ifre");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(518, 134, 183, 32);
		w_doctor.add(lblNewLabel_4);

		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorName.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMessage("fill");
				} else if (fld_doctorTcno.getText().length() != 11 || fld_doctorTcno.getText().length() == 0) {
					Helper.showMessage("L�tfen TC numaran�z� 11 haneli giriniz!");
				}else {
					boolean control = userRepository.saveDoctor(fld_doctorName.getText(), fld_doctorTcno.getText(),
							fld_doctorPass.getText());
					if (control) {
						Helper.showMessage("Kay�t ba�ar�l�");
						fld_doctorName.setText(null);
						fld_doctorPass.setText(null);
						fld_doctorTcno.setText(null);
						updateComboBox();
						try {
							updateDoctorModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						Helper.showMessage("Bu TC numaras� ile kay�tl� biri bulunmaktad�r!");
					}
				}
			}
		});
		btn_addDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_addDoctor.setBounds(518, 196, 183, 32);
		w_doctor.add(btn_addDoctor);

		JLabel lblNewLabel_2_2_1 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_2_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_2_1.setBounds(518, 252, 183, 32);
		w_doctor.add(lblNewLabel_2_2_1);

		fld_doctorID = new JTextField();
		fld_doctorID.setEditable(false);
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(518, 279, 183, 25);
		w_doctor.add(fld_doctorID);

		JButton btn_deleteDoctor = new JButton("Sil");
		btn_deleteDoctor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0) {
					Helper.showMessage("L�tfen bir silinecek kullan�c�y� se�iniz");
				} else {
					if (Helper.confirm("sure")) {
						int doctorID = Integer.parseInt(fld_doctorID.getText());
						boolean control = userRepository.deleteDoctor(doctorID);
						if (control) {
							Helper.showMessage("��lem ba�ar�l�");
							fld_doctorID.setText(null);
							updateComboBox();
							try {
								updateDoctorModel();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		btn_deleteDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_deleteDoctor.setBounds(518, 314, 183, 32);
		w_doctor.add(btn_deleteDoctor);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(518, 161, 183, 25);
		w_doctor.add(fld_doctorPass);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 14, 494, 332);
		w_doctor.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});

		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int doctorID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String doctorName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String doctorTc = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String doctorPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					userRepository.updateDoctor(doctorID, doctorName, doctorTc, doctorPass);
				}

			}
		});

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 10, 255, 336);
		w_clinic.add(w_scrollClinic);

		
		table_clinic = new JTable();
		w_scrollClinic.setViewportView(table_clinic);

		JLabel lblNewLabel_2_1 = new JLabel("Poliklinik Ad�");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(275, 10, 147, 32);
		w_clinic.add(lblNewLabel_2_1);

		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(275, 37, 156, 25);
		w_clinic.add(fld_clinicName);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(446, 10, 255, 336);
		w_clinic.add(w_scrollWorker);

		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_addClinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_addClinic.setBounds(275, 72, 156, 32);
		w_clinic.add(btn_addClinic);

		select_doctor = new JComboBox();

		select_doctor.setBounds(275, 262, 156, 39);
		updateComboBox();

		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_clinic.add(select_doctor);

		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_addWorker.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_addWorker.setBounds(275, 314, 156, 32);
		w_clinic.add(btn_addWorker);

		JLabel lblNewLabel_2_1_1 = new JLabel("Poliklinik Ad�");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1_1.setBounds(275, 144, 147, 32);
		w_clinic.add(lblNewLabel_2_1_1);

		JButton btn_selectWorker = new JButton("Se�");
		btn_selectWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_selectWorker.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_selectWorker.setBounds(275, 172, 156, 32);
		w_clinic.add(btn_selectWorker);
	}

	public void updateDoctorModel() throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		doctorData = new Object[4];
		for (int i = 0; i < userRepository.getDoctorList().size(); i++) {
			doctorData[0] = userRepository.getDoctorList().get(i).getId();
			doctorData[1] = userRepository.getDoctorList().get(i).getName();
			doctorData[2] = userRepository.getDoctorList().get(i).getTcno();
			doctorData[3] = userRepository.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}

	
	public DefaultComboBoxModel updateComboBox() {
		DefaultComboBoxModel dm = new DefaultComboBoxModel();
		dm.removeAllElements();
		for (int i = 0; i < userRepository.getDoctorList().size(); i++) {
			dm.addElement(new Item(userRepository.getDoctorList().get(i).getId(),
					userRepository.getDoctorList().get(i).getName()));
			select_doctor.setModel(dm);
		}
		return dm;
	}
}
