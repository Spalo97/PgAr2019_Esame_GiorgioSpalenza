package it.unibs.esame;

public class Opzione {

	private String testo;
	private int idSuccessivo;
	private int puntiVita;
	
	public Opzione(int idSuccessivo,int puntiVita) {
		this.puntiVita=puntiVita;
		this.idSuccessivo=idSuccessivo;
	}

	public String getTesto() {
		return testo;
	}

	public int getIdSuccessivo() {
		return idSuccessivo;
	}
	
	public int getPuntiVita() {
		return this.puntiVita;
	}
}
