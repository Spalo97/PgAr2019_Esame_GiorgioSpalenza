package it.unibs.esame;

public class Casella {

	private int id;
	private String testo;
	private Opzione[] opzioni;
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

	public Opzione[] getCaselleSuccessive() {
		return opzioni;
	}

	public void setOpzioni(int n) {
		this.opzioni=new Opzione[n];
	}
	
	public void addOpzione(Opzione opzione) {
		opzioni[opzioni.length]=opzione;
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
