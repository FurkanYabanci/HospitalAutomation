package com.proje.repository.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.proje.jpafactory.JpaFactory;
import com.proje.jpafactory.impl.JpaFactoryImpl;
import com.proje.model.Appointment;
import com.proje.model.User;
import com.proje.model.UserType;
import com.proje.model.Whour;
import com.proje.repository.AppointmentRepository;

public class AppointmentRepositoryImpl implements AppointmentRepository {

	private JpaFactory jpaFactory = new JpaFactoryImpl();

	private EntityManager entityManager = jpaFactory.getEntityManager();

	private EntityTransaction transaction = entityManager.getTransaction();
	
	
	@Override
	public List<Appointment> getAppoitmentListForPatient(int patient_id) {
		this.transaction.begin();
		TypedQuery<Appointment> typedQuery = (TypedQuery<Appointment>) entityManager.createQuery("SELECT A FROM Appointment A WHERE A.patient_id = :patient_id");
		typedQuery.setParameter("patient_id", patient_id);
		List<Appointment> appointments = typedQuery.getResultList();
		for (Appointment appointment : appointments) {
			this.entityManager.refresh(appointment);
		}
		this.transaction.commit();
		return appointments;
	}
	
	@Override
	public List<Appointment> getAppoitmentListForDoctor(int doctor_id) {
		this.transaction.begin();
		TypedQuery<Appointment> typedQuery = (TypedQuery<Appointment>) entityManager.createQuery("SELECT A FROM Appointment A WHERE A.doctor_id = :doctor_id");
		typedQuery.setParameter("doctor_id", doctor_id);
		List<Appointment> appointments = typedQuery.getResultList();
		for (Appointment appointment : appointments) {
			this.entityManager.refresh(appointment);
		}
		this.transaction.commit();
		return appointments;
	}

	@Override
	public boolean saveAppointment(int doctor_id, int patient_id, String doctor_name, String patient_name,
			Date app_date,Date app_time) {
		this.transaction.begin();
		int key = 0;
		Appointment appointment = new Appointment();
		appointment.setDoctor_id(doctor_id);
		appointment.setDoctor_name(doctor_name);
		appointment.setPatient_id(patient_id);
		appointment.setPatient_name(patient_name);
		appointment.setApp_date(app_date);
		appointment.setApp_time(app_time);
		this.entityManager.persist(appointment);
		key = 1;
		this.transaction.commit();
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteAppointment(int id) {
		this.transaction.begin();
		int key = 0;
		TypedQuery<Appointment> typedQuery = (TypedQuery<Appointment>) entityManager.createQuery("SELECT A FROM Appointment A WHERE A.id = :id");
		typedQuery.setParameter("id", id);
		this.entityManager.remove(typedQuery.getSingleResult());
		key = 1;
		this.transaction.commit();
		if (key == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void deleteAppointmentByDoctorID(int doctor_id) {
		this.transaction.begin();
		TypedQuery<Appointment> typedQuery = (TypedQuery<Appointment>) entityManager.createQuery("SELECT A FROM Appointment A WHERE A.doctor_id = :doctor_id");
		typedQuery.setParameter("doctor_id", doctor_id);
		List<Appointment> appointments = typedQuery.getResultList();
		for (Appointment appointment : appointments) {
			this.entityManager.remove(appointment);
		}
		this.transaction.commit();
	}


	

	

}
