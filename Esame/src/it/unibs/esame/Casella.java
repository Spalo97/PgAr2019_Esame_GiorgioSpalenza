package it.unibs.esame;

import java.util.ArrayList;

public class Casella {

	private int id;
	private String testo;
	private ArrayList<Opzione> opzioni=new ArrayList<Opzione>();
	private int costo;
	private String tipo;
	
	public Casella() {		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public ArrayList<Opzione> getOpzioni() {
		return opzioni;
	}

	public void addOpzione(Opzione opzione) {
		opzioni.add(opzione);
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
