package com.proje.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.proje.jpafactory.JpaFactory;
import com.proje.jpafactory.impl.JpaFactoryImpl;
import com.proje.model.User;
import com.proje.model.UserType;
import com.proje.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

	private JpaFactory jpaFactory = new JpaFactoryImpl();

	private EntityManager entityManager = jpaFactory.getEntityManager();

	private EntityTransaction transaction = entityManager.getTransaction();

	@Override
	public List<User> getUserList() {
		this.transaction.begin();
		TypedQuery<User> typedQuery = (TypedQuery<User>) entityManager.createQuery("SELECT U FROM User U");
		List<User> users = typedQuery.getResultList();
		for (User user : users) {
			this.entityManager.refresh(user);
		}
		this.transaction.commit();
		return users;
	}

	@Override
	public List<User> getDoctorList() {
		this.transaction.begin();
		TypedQuery<User> typedQuery = (TypedQuery<User>) entityManager
				.createQuery("SELECT U FROM User U WHERE U.type = 'doktor'");
		List<User> users = typedQuery.getResultList();
		for (User user : users) {
			this.entityManager.refresh(user);
		}
		this.transaction.commit();
		return users;
	}
	@Override
	public boolean saveDoctor(String name, String tcno, String password) {
		this.transaction.begin();
		int key = 0;
		int count = 0;
		TypedQuery<User> typedQuery = (TypedQuery<User>) entityManager
				.createQuery("SELECT U FROM User U WHERE U.tcno = :tcno");
		typedQuery.setParameter("tcno", tcno);
		List<User> users = typedQuery.getResultList();
		for (User user : users) {
			count++;
		}
		if (count == 0) {
			User user = new User();
			user.setName(name);
			user.setTcno(tcno);
			user.setPassword(password);
			user.setType(UserType.doktor);
			this.entityManager.persist(user);
			key = 1;
		}
		this.transaction.commit();
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteDoctor(int id) {
		this.transaction.begin();
		int key = 0;
		TypedQuery<User> typedQuery = (TypedQuery<User>) entityManager
				.createQuery("SELECT U FROM User U WHERE U.id = :id");
		typedQuery.setParameter("id", id);
		this.entityManager.remove(typedQuery.getSingleResult());
		key = 1;
		this.transaction.commit();
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateDoctor(int id, String name, String tcno, String password) {
		this.transaction.begin();
		int key = 0;
		TypedQuery<User> typedQuery = (TypedQuery<User>) entityManager
				.createQuery("SELECT U FROM User U WHERE U.id = :id");
		typedQuery.setParameter("id", id);
		User user = typedQuery.getSingleResult();
		user.setName(name);
		user.setTcno(tcno);
		user.setPassword(password);
		user.setType(UserType.doktor);
		this.entityManager.merge(user);
		key = 1;
		this.transaction.commit();
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}
}
