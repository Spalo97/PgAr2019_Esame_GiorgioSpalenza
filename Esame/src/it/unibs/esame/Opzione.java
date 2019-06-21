package it.unibs.esame;

public class Opzione {

	private String testo;
	private int idSuccessivo;
	private int punti;
	private int statId;
	
	public Opzione(int idSuccessivo,int puntiVita,String testo, int statId) {
		this.punti=puntiVita;
		this.idSuccessivo=idSuccessivo;
		this.testo=testo;
		this.statId=statId;
	}

	public String getTesto() {
		return testo;
	}

	public int getIdSuccessivo() {
		return idSuccessivo;
	}
	
	public int getPunti() {
		return this.punti;
	}
	
	public int getStatId() {
		return statId;
	}

	public void setIdSuccessivo(int destinazione) {
		this.idSuccessivo=destinazione;
	}
}
