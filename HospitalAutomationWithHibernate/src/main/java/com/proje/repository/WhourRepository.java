package com.proje.repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


import com.proje.model.Whour;

public interface WhourRepository {

	List<Whour> getWhourList(int doctor_id);
	
	boolean saveWhour(int doctor_id,String doctor_name,Date wdate,Date wtime);
	
	boolean deleteWhour(int id);
	
	boolean updateWhourStatus(int doctor_id, Date wdate, Date wtime) throws SQLException;
	
	void deleteWhourByDoctorID(int doctor_id);
}
