package it.unibs.esame;

public class Giocatore {
	
	private String nome;
	private int vita=100;
	
	public Giocatore(String nome) {
		if(nome.equals("")) {
			this.nome="Giocatore";
		}else {
			this.nome=nome;
		}
	}
	
	public void setVita(int puntiVita) {
		this.vita=this.vita+puntiVita;
	}
	
	public int getVita() {
		return this.vita;
	}
	
	public String getNome() {
		return this.nome;
	}
}
