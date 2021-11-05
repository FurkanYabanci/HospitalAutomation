package com.proje.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.proje.jpafactory.JpaFactory;
import com.proje.jpafactory.impl.JpaFactoryImpl;
import com.proje.model.Clinic;
import com.proje.repository.ClinicRepository;

public class ClinicRepositoryImpl implements ClinicRepository {

	private JpaFactory jpaFactory = new JpaFactoryImpl();

	private EntityManager entityManager = jpaFactory.getEntityManager();

	private EntityTransaction transaction = entityManager.getTransaction();

	@Override
	public List<Clinic> getClinicList() {
		this.transaction.begin();
		TypedQuery<Clinic> typedQuery = (TypedQuery<Clinic>) entityManager.createQuery("SELECT C FROM Clinic C");
		List<Clinic> clinics = typedQuery.getResultList();
		for (Clinic clinic : clinics) {
			this.entityManager.refresh(clinic);
		}
		this.transaction.commit();
		return clinics;
	}
	
	@Override
	public boolean saveClinic(String name) {
		this.transaction.begin();
		int key = 0;
		Clinic clinic = new Clinic();
		clinic.setName(name);
		this.entityManager.persist(clinic);
		key = 1;
		this.transaction.commit();
		if (key == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteClinic(int id) {
		this.transaction.begin();
		int key = 0;
		TypedQuery<Clinic> typedQuery = (TypedQuery<Clinic>) entityManager.createQuery("SELECT C FROM Clinic C WHERE C.id = :id");
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
	public boolean updateClinic(int id, String name) {
		Clinic clinic = this.entityManager.find(Clinic.class, id);
		clinic.setName(name);
		int key = 0;
		this.transaction.begin();
		this.entityManager.merge(clinic);
		this.transaction.commit();
		this.entityManager.close();
		key = 1;
		if (key == 1) {
			return true;
		}else {
			return false;
		}
	}

	

}
