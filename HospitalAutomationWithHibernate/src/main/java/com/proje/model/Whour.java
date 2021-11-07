package com.proje.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Whour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int doctor_id;

	private String doctor_name;

	@Temporal(TemporalType.DATE)
	private Date wdate;

	@Temporal(TemporalType.TIME)
	private Date wtime;

	@Enumerated(EnumType.STRING)
	private Status status;

	public Whour() {
		// TODO Auto-generated constructor stub
	}

	public Whour(int doctor_id, String doctor_name, Date wdate, Date wtime, Status status) {
		super();
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.wdate = wdate;
		this.wtime = wtime;
		this.status = status;
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

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public Date getWtime() {
		return wtime;
	}

	public void setWtime(Date wtime) {
		this.wtime = wtime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
