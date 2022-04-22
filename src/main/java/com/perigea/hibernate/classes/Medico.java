package com.perigea.hibernate.classes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import javax.persistence.JoinColumn;

@Entity
public class Medico {
	@Id
	@Column(name = "codice_fiscale")
	private String codiceFiscaleMedico;
	
	@Column(name = "nome")
	private String nomeMedico;
	
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name="medico_reparto",
		joinColumns={@JoinColumn(name="codice_fiscale")},
		inverseJoinColumns={@JoinColumn(name="codice_reparto")})
	
	private List<Reparto> reparti;
	
	public Medico() {
		// TODO Auto-generated constructor stub
	}
	
	public Medico(String codiceFiscaleMedico, String nomeMedico) {
		this.codiceFiscaleMedico = codiceFiscaleMedico;
		this.nomeMedico = nomeMedico;
	}
	
	
	public Medico(String codiceFiscaleMedico, String nomeMedico, Specializzazione specializzazione) {
		this.codiceFiscaleMedico = codiceFiscaleMedico;
		this.nomeMedico = nomeMedico;
		this.specializzazione = specializzazione;
	}

	@ManyToOne
	@JoinColumn(name="id_specializzazione")
	private Specializzazione specializzazione;
	
	@OneToOne(mappedBy="direttore", cascade = {CascadeType.MERGE})
	private Reparto reparto;
	
	public String getCodiceFiscaleMedico() {
		return codiceFiscaleMedico;
	}
	
	public String getNomeMedico() {
		return nomeMedico;
	}
	
	public List<Reparto> getReparti() {
		return reparti;
	}
	
	public Specializzazione getSpecializzazione() {
		return specializzazione;
	}
	
	public Reparto getReparto() {
		return reparto;
	}
	
	
	public void setCodiceFiscaleMedico(String codiceFiscaleMedico) {
		this.codiceFiscaleMedico = codiceFiscaleMedico;
	}
	
	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	
	public void setReparti(List<Reparto> reparti) {
		this.reparti = reparti;
	}
	
	public void setSpecializzazione(Specializzazione specializzazione) {
		this.specializzazione = specializzazione;
	}

	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}

	@Override
	public String toString() {
		return "Medico [codiceFiscaleMedico=" + codiceFiscaleMedico + ", nomeMedico=" + nomeMedico
				+ ", specializzazione=" + specializzazione + "]";
	}
	

	
}
