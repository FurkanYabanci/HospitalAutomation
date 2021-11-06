package com.proje.repository;

import java.util.List;

import com.proje.model.User;

public interface UserRepository{

	List<User> getUserList();
	
	List<User> getDoctorList();
	
	List<User> findUserJoinWorkerByClinicId(int clinic_id);
	
	boolean saveDoctor(String name, String tcno, String password);
	
	boolean deleteDoctor(int id);
	
	boolean updateDoctor(int id,String name, String tcno, String password);
	
	boolean registerPatient( String tcno, String password, String name);
	
	
	
}
