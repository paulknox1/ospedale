package com.perigea.hibernate.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.perigea.hibernate.classes.Medico;
import com.perigea.hibernate.classes.Reparto;
import com.perigea.hibernate.classes.RunnableSpecializzazione;
import com.perigea.hibernate.classes.Specializzazione;
import com.perigea.hibernate.dao.MedicoDao;
import com.perigea.hibernate.dao.RepartoDao;
import com.perigea.hibernate.dao.SpecializzazioneDao;

public class Application {
    private static MedicoDao jpaMedicoDao;
    private static RepartoDao jpaRepartoDao;
    private static SpecializzazioneDao jpaSpecializzazioneDao;
    
    public static void main(String[] args) {
    	//Istanze delle classi
    	jpaMedicoDao = new MedicoDao();
    	jpaSpecializzazioneDao = new SpecializzazioneDao();
    	jpaRepartoDao = new RepartoDao();

    	//Operazioni CRUD su MEDICO 
    	//Read, Update e Delete (ancora nessuna gestione sul cascade)
        Medico medico = getMedico("abcdef");
        System.out.println(medico);
        updateMedico(medico,"Francesco Rossi");
        //deleteMedico(getMedico("mnc32"));

        //Insert di medico con chiave esterna su specializzazione
        
        saveSpecializzazione(new Specializzazione("Neurologia"));
        Specializzazione s = getSpecializzazione(56L);
        //saveMedico(new Medico("mn952", "Monica Verdi", s));
        
        
		//Insert medico nella LISTA di specializzazione
		//prima trovo la lista medici, poi aggiungo nuovo medico con update 
		//on cascade sulla chiave esterna della specializzazione
        Medico med = new Medico();
        med.setCodiceFiscaleMedico("paperino55");
        med.setNomeMedico("Paperino");
        List<Medico> lista = getAllMedici();
        addListaMedico(s, med, lista);
        
        
        //Inserimento in reparto
		//Prima di tutto settare il direttore
		//Poi setto la lista dei reparti gestita su medico. in questo modo la join table dovrebbe aggiornarsi da sola
        Medico direttore = getMedico("p454fd");
        saveReparto(new Reparto("Neurologia", direttore));
        Reparto r =  getLastReparto();
        System.out.println(r);
        addListaReparto(direttore, r);
        
        
        /* Gestione thread


		EntityManager entityManager = Persistence.createEntityManagerFactory("jbd-pu1").createEntityManager();
		
        //Insert per specializzazione e medico una volta definita la prima 
        RunnableSpecializzazione  run1 = new RunnableSpecializzazione(entityManager,56L, "TEST 1" );
        run1.start();
        
        RunnableSpecializzazione  run2 = new RunnableSpecializzazione(entityManager,56L, "TEST 2" );
        run2.start();
        
        */
        
    }
    
    
    public static Reparto getLastReparto() {
    	Reparto r = jpaRepartoDao.getLastReparto();
    	return r;
    }
    
    
    public static void addListaReparto(Medico m, Reparto r) {
    	jpaRepartoDao.addListaReparto(m, r);
    }
    
    
    public static Medico getMedico(String id) {
        Medico medico = jpaMedicoDao.get(id);
        return medico;
    }

    public static Specializzazione getSpecializzazione(Long id) {
    	Specializzazione specializzazione = jpaSpecializzazioneDao.get(id);
        return specializzazione;
    }
  
    public static List<Medico> getAllMedici() {
        return jpaMedicoDao.getAll();
    }
    
    
    public static void updateMedico(Medico medico, String params) {
    	jpaMedicoDao.update(medico, params);
    }

    public static void updateSpecializzazione(Specializzazione specializzazione, String params) {
    	jpaSpecializzazioneDao.update(specializzazione, params);
    }
    
    public static void saveMedico(Medico medico) {
    	jpaMedicoDao.save(medico);
    }

    public static void saveReparto(Reparto reparto) {
    	jpaRepartoDao.save(reparto);
    }
    
    public static void saveSpecializzazione(Specializzazione specializzazione) {
    	jpaSpecializzazioneDao.save(specializzazione);
    }
    public static void addListaMedico(Specializzazione specializzazione, Medico medico, List<Medico> lista) {
    	jpaSpecializzazioneDao.saveMedico(specializzazione, medico, lista);
    }

    public static void deleteMedico(Medico medico) {
    	jpaMedicoDao.delete(medico);
    }
}
