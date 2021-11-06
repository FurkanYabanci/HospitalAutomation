package com.proje.repository.impl;



import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.proje.jpafactory.JpaFactory;
import com.proje.jpafactory.impl.JpaFactoryImpl;
import com.proje.model.Worker;
import com.proje.repository.WorkerRepository;

public class WorkerRepositoryImpl implements WorkerRepository {

	private JpaFactory jpaFactory = new JpaFactoryImpl();
	
	private EntityManager entityManager = jpaFactory.getEntityManager();
	
	private EntityTransaction transaction = entityManager.getTransaction();

	@Override
	public boolean saveWorker(int user_id, int clinic_id) {
		this.transaction.begin();
		int count = 0;
		boolean key = false;
		if (count == 0) {
			Worker w = new Worker();
			w.setClinic_id(clinic_id);
			w.setUser_id(user_id);
			this.entityManager.persist(w);
			key = true;
		}
		this.transaction.commit();
		if (key) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteWorkerByDoctorID(int user_id) {
		this.transaction.begin();
		TypedQuery<Worker> typedQuery = (TypedQuery<Worker>) entityManager.createQuery("SELECT W FROM Worker W WHERE W.user_id = :user_id");
		typedQuery.setParameter("user_id", user_id);
		List<Worker> workers = typedQuery.getResultList();
		for (Worker worker : workers) {
			this.entityManager.remove(worker);
		}
		this.transaction.commit();
		
	}


}
