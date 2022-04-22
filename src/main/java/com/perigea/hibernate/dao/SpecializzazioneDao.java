	package com.perigea.hibernate.dao;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.perigea.hibernate.classes.Medico;
import com.perigea.hibernate.classes.Specializzazione;

public class SpecializzazioneDao extends Dao{
	
	public List<Specializzazione> getAll() {
		Query query = getEntityManager().createNativeQuery("SELECT  * FROM Specializzazione");
		@SuppressWarnings("unchecked")
		List<Specializzazione> lista = query.getResultList();
		return lista;
	}

    public void saveMedico(Specializzazione s, Medico medico,  List<Medico> lista) {
    	System.out.println(lista.get(0));
    	s.setMedici(lista);
    	s.addMedico(medico);
    	executeInsideTransaction(entityManager -> entityManager.merge(s));
    }
    
    public Specializzazione get(Long id) {
    	return (Specializzazione) getEntityManager().find(Specializzazione.class, id);
    }

	public void save(Specializzazione specializzazione) {
		executeInsideTransaction(entityManager -> entityManager.persist(specializzazione));
	}

	public void update(Specializzazione specializzazione, String parameters) {
		specializzazione.setNomeSpecializzazione(parameters);
		executeInsideTransaction(entityManager -> entityManager.merge(specializzazione));
	}

	public void delete(Specializzazione specializzazione) {
		executeInsideTransaction(entityManager -> entityManager.remove(specializzazione));
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
