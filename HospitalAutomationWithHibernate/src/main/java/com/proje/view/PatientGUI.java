package com.proje.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ibm.icu.text.SimpleDateFormat;
import com.proje.helper.Helper;
import com.proje.helper.Item;
import com.proje.model.Patient;
import com.proje.repository.ClinicRepository;
import com.proje.repository.UserRepository;
import com.proje.repository.WhourRepository;
import com.proje.repository.impl.ClinicRepositoryImpl;
import com.proje.repository.impl.UserRepositoryImpl;
import com.proje.repository.impl.WhourRepositoryImpl;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class PatientGUI extends JFrame {

	private JPanel w_pane;
	private static Patient patient = new Patient();
	private ClinicRepository clinicRepository = new ClinicRepositoryImpl();
	private UserRepository userRepository = new UserRepositoryImpl();
	private WhourRepository whourRepository = new WhourRepositoryImpl();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private JTable table_whour;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI frame = new PatientGUI(patient);
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
	public PatientGUI(Patient patient) {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[2];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[2];
		
		whourModel = new DefaultTableModel();
		Object[] colWhourName = new Object[3];
		colWhourName[0] = "ID";
		colWhourName[1] = "Tarih";
		colWhourName[2] = "Saat";
		whourModel.setColumnIdentifiers(colWhourName);
		whourData = new Object[3];
			
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz,Sayýn "+patient.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 10, 250, 34);
		w_pane.add(lblNewLabel);
		
		JButton btn_out = new JButton("Çýkýþ Yap");
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
		
		JTabbedPane w_tabPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabPane.setBounds(10, 70, 716, 383);
		w_pane.add(w_tabPane);
		
		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tabPane.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 35, 280, 309);
		w_appointment.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		
		JLabel lblNewLabel_2 = new JLabel("Doktor Listesi");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(10, 10, 105, 22);
		w_appointment.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1.setBounds(300, 35, 105, 32);
		w_appointment.add(lblNewLabel_2_1);
		
		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(300, 65, 150, 35);
		select_clinic.addItem("--Poliklinik Seç--");
		for(int i = 0; i < clinicRepository.getClinicList().size(); i++)
		{
			select_clinic.addItem(new Item(clinicRepository.getClinicList().get(i).getId(),clinicRepository.getClinicList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					for (int i = 0; i < userRepository.findUserJoinWorkerByClinicId(item.getKey()).size(); i++) {
						doctorData[0] = userRepository.findUserJoinWorkerByClinicId(item.getKey()).get(i).getId();
						doctorData[1] = userRepository.findUserJoinWorkerByClinicId(item.getKey()).get(i).getName();
						doctorModel.addRow(doctorData);
					}
				}else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_appointment.add(select_clinic);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Doktor Se\u00E7");
		lblNewLabel_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1_1.setBounds(300, 155, 147, 32);
		w_appointment.add(lblNewLabel_2_1_1);
		
		JButton btn_selectDoctor = new JButton("Seç");
		btn_selectDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_doctor.getSelectedRow();
				if (selRow  >= 0) {
					String selDoctor = table_doctor.getValueAt(selRow, 0).toString();
					int selDoctorID = Integer.parseInt(selDoctor);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					for (int i = 0; i < whourRepository.getWhourList(selDoctorID).size(); i++) {
						whourData[0] = whourRepository.getWhourList(selDoctorID).get(i).getId();
						whourData[1] = whourRepository.getWhourList(selDoctorID).get(i).getWdate();
						whourData[2] = whourRepository.getWhourList(selDoctorID).get(i).getWtime();
						whourModel.addRow(whourData);
					}
					table_whour.setModel(whourModel);
					selectDoctorID = selDoctorID;
					selectDoctorName = table_doctor.getModel().getValueAt(selRow, 1).toString();
				}else {
					Helper.showMessage("Lütfen bir doktor seçiniz");
				}
			}
		});
		btn_selectDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_selectDoctor.setBounds(300, 185, 150, 32);
		w_appointment.add(btn_selectDoctor);
		
		JLabel lblNewLabel_2_2 = new JLabel("Uygun Saatler");
		lblNewLabel_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_2.setBounds(462, 10, 239, 22);
		w_appointment.add(lblNewLabel_2_2);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(462, 35, 239, 309);
		w_appointment.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Randevu");
		lblNewLabel_2_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel_2_1_1_1.setBounds(300, 275, 147, 32);
		w_appointment.add(lblNewLabel_2_1_1_1);
		
		JButton btn_addAppoint = new JButton("Randevu Al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
					Date selDate = null;
					Date selTime = null;
					try {
						selDate = sdfDate.parse(table_whour.getModel().getValueAt(selRow, 1).toString());
						selTime = sdfTime.parse(table_whour.getModel().getValueAt(selRow, 2).toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addAppoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_addAppoint.setBounds(300, 305, 150, 32);
		w_appointment.add(btn_addAppoint);
		
		JPanel w_myAppoint = new JPanel();
		w_tabPane.addTab("Randevularým", null, w_myAppoint, null);
		w_myAppoint.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 10, 691, 336);
		w_myAppoint.add(w_scrollAppoint);
		
		table_appoint = new JTable();
		w_scrollAppoint.setViewportView(table_appoint);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5); // ID ve TARÝH kolonlarýndan ID'nin boyunu deðiþtirdik.
	}
	public void updateWhourModel(int doctor_id) {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whourRepository.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whourRepository.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whourRepository.getWhourList(doctor_id).get(i).getWdate();
			whourData[2] = whourRepository.getWhourList(doctor_id).get(i).getWtime();
			whourModel.addRow(whourData);
		}
	}
}
