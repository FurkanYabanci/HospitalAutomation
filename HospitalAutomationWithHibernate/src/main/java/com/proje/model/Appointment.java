package com.proje.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int doctor_id;

	private int patient_id;

	private String doctor_name;

	private String patient_name;

	@Temporal(TemporalType.DATE)
	private Date app_date;

	@Temporal(TemporalType.TIME)
	private Date app_time;

	public Appointment() {
		// TODO Auto-generated constructor stub
	}

	public Appointment(int doctor_id, int patient_id, String doctor_name, String patient_name, Date app_date,
			Date app_time) {
		super();
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.doctor_name = doctor_name;
		this.patient_name = patient_name;
		this.app_date = app_date;
		this.app_time = app_time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public Date getApp_date() {
		return app_date;
	}

	public void setApp_date(Date app_date) {
		this.app_date = app_date;
	}

	public Date getApp_time() {
		return app_time;
	}

	public void setApp_time(Date app_time) {
		this.app_time = app_time;
	}

}
