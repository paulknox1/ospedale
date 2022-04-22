package com.perigea.hibernate.classes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity

public class Reparto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codice_reparto")
	private Long codiceReparto;
	@Column(name = "nome")
	private String nomeReparto;

	@ManyToMany(mappedBy = "reparti")
	private List<Medico> medici;
	
	
	@OneToOne @JoinColumn(name="id_direttore")
	private Medico direttore;
	

	public Long getCodiceReparto() {
		return codiceReparto;
	}
	

	public String getNomeReparto() {
		return nomeReparto;
	}
	
	public List<Medico> getMedici() {
		return medici;
	}
	
	
	public Reparto() {
		// TODO Auto-generated constructor stub
	}
	
	public Reparto(String nomeReparto, Medico direttore) {
		this.nomeReparto = nomeReparto;
		this.direttore = direttore;
	}


	public Medico getDirettore() {
		return direttore;
	}
	
	public void setNomeReparto(String nomeReparto) {
		this.nomeReparto = nomeReparto;
	}
	
	public void setCodiceReparto(Long codiceReparto) {
		this.codiceReparto = codiceReparto;
	}
	
	public void setMedici(List<Medico> medici) {
		this.medici = medici;
	}
	
	public void setDirettore(Medico direttore) {
		this.direttore = direttore;
	}


	@Override
	public String toString() {
		return "Reparto [codiceReparto=" + codiceReparto + ", nomeReparto=" + nomeReparto 
				+ "]";
	}
	
	
	
	
}




