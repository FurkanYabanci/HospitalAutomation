package com.proje.repository.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.proje.jpafactory.JpaFactory;
import com.proje.jpafactory.impl.JpaFactoryImpl;
import com.proje.model.Status;
import com.proje.model.User;
import com.proje.model.UserType;
import com.proje.model.Whour;
import com.proje.repository.WhourRepository;

public class WhourRepositoryImpl implements WhourRepository {

	private JpaFactory jpaFactory = new JpaFactoryImpl();

	private EntityManager entityManager = jpaFactory.getEntityManager();

	private EntityTransaction transaction = entityManager.getTransaction();

	@Override
	public List<Whour> getWhourList(int doctor_id) {
		this.transaction.begin();
		TypedQuery<Whour> typedQuery = (TypedQuery<Whour>) entityManager
				.createQuery("SELECT W FROM Whour W WHERE W.status = 'active' AND W.doctor_id = :doctor_id");
		typedQuery.setParameter("doctor_id", doctor_id);
		List<Whour> whours = typedQuery.getResultList();
		for (Whour whour : whours) {
			this.entityManager.refresh(whour);
		}
		this.transaction.commit();
		return whours;
	}

	@Override
	public boolean saveWhour(int doctor_id, String doctor_name, Date wdate, Date wtime) {
		this.transaction.begin();
		int key = 0;
		Whour whour = new Whour();
		whour.setDoctor_id(doctor_id);
		whour.setDoctor_name(doctor_name);
		whour.setWdate(wdate);
		whour.setWtime(wtime);
		whour.setStatus(Status.active);
		this.entityManager.persist(whour);
		key = 1;
		this.transaction.commit();
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteWhour(int id) {
		this.transaction.begin();
		int key = 0;
		this.entityManager.remove(this.entityManager.find(Whour.class, id));
		key = 1;
		this.transaction.commit();
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateWhourStatus(int doctor_id, Date wdate, Date wtime) throws SQLException {
		this.transaction.begin();
		int key = 0;
		TypedQuery<Whour> typedQuery = (TypedQuery<Whour>) entityManager
				.createQuery("SELECT W FROM Whour W WHERE W.doctor_id = :doctor_id AND W.wdate = :wdate AND W.wtime = :wtime");
		typedQuery.setParameter("doctor_id", doctor_id);
		typedQuery.setParameter("wdate", wdate);
		typedQuery.setParameter("wtime", wtime);
		List<Whour> whours = typedQuery.getResultList();
		for (Whour whour : whours) {
			whour.setStatus(Status.passive);
		}
		key = 1;
		this.transaction.commit();
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteWhourByDoctorID(int doctor_id) {
		this.transaction.begin();
		TypedQuery<Whour> typedQuery = (TypedQuery<Whour>) entityManager.createQuery("SELECT W FROM Whour W WHERE W.doctor_id = :doctor_id");
		typedQuery.setParameter("doctor_id", doctor_id);
		List<Whour> whours = typedQuery.getResultList();
		for (Whour whour : whours) {
			this.entityManager.remove(whour);
		}
		this.transaction.commit();
	}

}
