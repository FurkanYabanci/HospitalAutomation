package com.proje.jpafactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public interface JpaFactory {

	EntityManager getEntityManager();

	EntityTransaction getTransaction();
}
