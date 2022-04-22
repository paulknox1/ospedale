package com.perigea.hibernate.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Dao {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jbd-pu1");
	private static EntityManager entityManager = emf.createEntityManager();
   
    public EntityManager getEntityManager() {
    	return entityManager;
    }

}
