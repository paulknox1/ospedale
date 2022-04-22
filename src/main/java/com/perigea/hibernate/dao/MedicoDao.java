package com.perigea.hibernate.dao;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.perigea.hibernate.classes.Medico;

public class MedicoDao extends Dao {
	
	public List<Medico> getAll() {
		Query query = getEntityManager().createNativeQuery("SELECT  * FROM medico", Medico.class);
		@SuppressWarnings("unchecked")
		List<Medico> listaMedici = query.getResultList();
		return listaMedici;
	}

	
    public Medico get(String id) {
    	return (Medico) getEntityManager().find(Medico.class, id);
    }
   
	
    public void save(Medico medico) {
		executeInsideTransaction(entityManager -> entityManager.persist(medico));
	}

	public void update(Medico medico, String parameters) {
		medico.setNomeMedico(parameters);
		executeInsideTransaction(entityManager -> entityManager.merge(medico));
	}

	public void delete(Medico medico) {
		executeInsideTransaction(entityManager -> entityManager.remove(medico));
	}

	private void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			action.accept(getEntityManager());
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}
	}
}
