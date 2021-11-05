package com.proje.repository;

import java.util.List;

import com.proje.model.Clinic;

public interface ClinicRepository {

	List<Clinic> getClinicList();

	boolean saveClinic(String name);

	boolean deleteClinic(int id);

	boolean updateClinic(int id, String name);
}
