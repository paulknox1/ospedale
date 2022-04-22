package com.perigea.hibernate.dao;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.perigea.hibernate.classes.Medico;
import com.perigea.hibernate.classes.Reparto;

public class RepartoDao extends Dao {
	public List<Reparto> getAll() {
		Query query = getEntityManager().createNativeQuery("SELECT  * FROM Reparto", Reparto.class);
		@SuppressWarnings("unchecked")
		List<Reparto> lista = query.getResultList();
		return lista;
	}
	
	public Reparto getLastReparto() {
		Query query = getEntityManager().createNativeQuery("SELECT  * FROM Reparto where codice_reparto IN (SELECT max(codice_reparto) FROM REPARTO)", Reparto.class);
		Reparto toReturn = (Reparto) query.getResultList().get(0);
		return toReturn;
	}
    public Reparto get(String id) {
    	return (Reparto) getEntityManager().find(Reparto.class, id);
    }
    
    public void addListaReparto(Medico m, Reparto r) {
    	Query query = getEntityManager().createNativeQuery("SELECT  * FROM Reparto where codice_reparto=:codice", Reparto.class);
    	query.setParameter("codice", r.getCodiceReparto());
    	List<Reparto> lista = query.getResultList();
    	m.setReparti(lista);
    	executeInsideTransaction(entityManager -> entityManager.merge(m));
    }
    

	public void save(Reparto reparto) {
		executeInsideTransaction(entityManager -> entityManager.persist(reparto));
	}

	public void update(Reparto reparto, String parameters) {
		reparto.setNomeReparto(parameters);
		executeInsideTransaction(entityManager -> entityManager.merge(reparto));
	}

	public void delete(Reparto Reparto) {
		executeInsideTransaction(entityManager -> entityManager.remove(Reparto));
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
