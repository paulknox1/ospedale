package com.perigea.hibernate.classes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity

public class Specializzazione {
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_spec")
	private Long idSpecializzazione;
	
	@Column(name = "nome")
	private String nomeSpecializzazione;
	
	
	@OneToMany(mappedBy="specializzazione", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)  //nome attributo nell'entity medico
	private List<Medico> medici;

	public Specializzazione() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Specializzazione(String nomeSpecializzazione) {
		this.nomeSpecializzazione = nomeSpecializzazione;
	}


	public void addMedico(Medico m) {
		medici.add(m);
		m.setSpecializzazione(this);
	}
	
	
	public void removeMedico(Medico m) {
		medici.remove(m);
		m.setSpecializzazione(this);
	}	

	public Long getIdSpecializzazione() {
		return idSpecializzazione;
	}
	
	public String getNomeSpecializzazione() {
		return nomeSpecializzazione;
	}
	
	public List<Medico> getMedici() {
		return medici;
	}
	
	public void setIdSpecializzazione(Long idSpecializzazione) {
		this.idSpecializzazione = idSpecializzazione;
	}
	
	public void setNomeSpecializzazione(String nomeSpecializzazione) {
		this.nomeSpecializzazione = nomeSpecializzazione;
	}
	
	public void setMedici(List<Medico> medici) {
		this.medici = medici;
	}

	@Override
	public String toString() {
		return "Specializzazione [idSpecializzazione=" + idSpecializzazione + ", nomeSpecializzazione="
				+ nomeSpecializzazione + "]";
	}
	
	
	
	
}
