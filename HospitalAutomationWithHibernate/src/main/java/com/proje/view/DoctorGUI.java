package com.proje.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ibm.icu.text.SimpleDateFormat;
import com.proje.helper.Helper;
import com.proje.model.Doctor;
import com.proje.repository.WhourRepository;
import com.proje.repository.impl.WhourRepositoryImpl;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private JPanel w_pane;
	private static Doctor doctor = new Doctor();
	private JTable table_whour;
	private WhourRepository whourRepository = new WhourRepositoryImpl();
	private DefaultTableModel whourModel = new DefaultTableModel();
	private Object[] whourData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {

		whourModel = new DefaultTableModel();
		Object[] colWhourName = new Object[3];
		colWhourName[0] = "ID";
		colWhourName[1] = "Tarih";
		colWhourName[2] = "Saat";
		whourModel.setColumnIdentifiers(colWhourName);

		whourData = new Object[3];
		for (int i = 0; i < whourRepository.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = whourRepository.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = whourRepository.getWhourList(doctor.getId()).get(i).getWdate();
			whourData[2] = whourRepository.getWhourList(doctor.getId()).get(i).getWtime();
			whourModel.addRow(whourData);
		}

		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþgeldiniz,Sayýn " + doctor.getName());
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

		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.WHITE);
		w_tabPane.addTab("Çalýþma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 10, 130, 20);
		w_whour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"}));
		select_time.setBounds(150, 10, 60, 20);
		w_whour.add(select_time);

		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
				String time = " " + select_time.getSelectedItem().toString() + ":00";
				Date wtime = null;
				try {
					wtime = sdf.parse(time);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				Date wdate = select_date.getDate();
				if (wdate != null) {
					boolean control = whourRepository.saveWhour(doctor.getId(), doctor.getName(), wdate, wtime);
					if (control) {
						Helper.showMessage("success");
						try {
							updateWhourModel(doctor);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					Helper.showMessage("error");
				}
			}
		});
		btn_addWhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_addWhour.setBounds(217, 10, 108, 20);
		w_whour.add(btn_addWhour);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(0, 40, 711, 316);
		w_whour.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);

		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String selWhour = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selWhourID = Integer.parseInt(selWhour);
					boolean control = whourRepository.deleteWhour(selWhourID);
					if (control) {
						Helper.showMessage("success");
						try {
							updateWhourModel(doctor);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else {
					Helper.showMessage("Lütfen bir tarih seçiniz!");
				}
			}
		});
		btn_deleteWhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_deleteWhour.setBounds(593, 10, 108, 20);
		w_whour.add(btn_deleteWhour);
	}

	public void updateWhourModel(Doctor doctor) throws SQLException {

		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		whourData = new Object[3];
		for (int i = 0; i < whourRepository.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = whourRepository.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = whourRepository.getWhourList(doctor.getId()).get(i).getWdate();
			whourData[2] = whourRepository.getWhourList(doctor.getId()).get(i).getWtime();
			whourModel.addRow(whourData);
		}
	}
}
