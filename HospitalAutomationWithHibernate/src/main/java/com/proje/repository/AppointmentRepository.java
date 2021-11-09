package com.proje.repository;

import java.util.Date;
import java.util.List;

import com.proje.model.Appointment;


public interface AppointmentRepository {
	
	List<Appointment> getAppoitmentListForPatient(int patient_id);
	
	List<Appointment> getAppoitmentListForDoctor(int doctor_id);

	boolean saveAppointment(int doctor_id, int patient_id, String doctor_name, String patient_name, Date app_date,Date app_time);
	
	boolean deleteAppointment(int id);
	
	void deleteAppointmentByDoctorID(int doctor_id);
}
