package com.proje.repository;

public interface WorkerRepository {

	boolean saveWorker(int user_id,int clinic_id);
	
	void deleteWorkerByDoctorID(int user_id);
}
