package com.proje.jpafactory.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.proje.jpafactory.JpaFactory;

public class JpaFactoryImpl implements JpaFactory{

private EntityManager entityManager = null;
	
	
	@Override
	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
		this.entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}

	@Override
	public EntityTransaction getTransaction() {
		EntityTransaction transaction = this.entityManager.getTransaction();
		return transaction;
	}
}
