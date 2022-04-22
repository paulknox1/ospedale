package com.perigea.hibernate.classes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.perigea.hibernate.main.Application;

public class RunnableSpecializzazione implements Runnable {
	private Thread t;
	private String name;
	private EntityManager entityManager;
	private Long id;
	
	public RunnableSpecializzazione(EntityManager entityManager, Long id, String name) {
		this.id = id;
		this.name = name;
		this.entityManager=entityManager;
		System.out.println("Creating " + name);
	}

	@Override
	public void run() {
		//entityManager = Persistence.createEntityManagerFactory("jbd-pu1").createEntityManager();
		//
		EntityTransaction transaction = null;
		try {
			System.out.println("Running " + name);
			transaction = entityManager.getTransaction();
			transaction.begin();
			Specializzazione s = Application.getSpecializzazione(id);
			Thread.sleep(1000);
			entityManager.merge(s);
			transaction.commit();
			System.out.println("completed " + name);
		} catch (Exception e) {
			System.out.println(e);
			transaction.rollback();
		} finally {
			entityManager.close();
		}

	}

	public void start() {
		System.out.println("Starting " + name);
		if (t == null) {
			t = new Thread(this, name);
			t.start();
		}
	}

}
