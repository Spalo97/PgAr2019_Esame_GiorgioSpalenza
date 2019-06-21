package it.unibs.esame;

import java.util.ArrayList;

public class Giocatore {
	
	private String nome;
	private int vita=100;
	private static ArrayList<Statistica> statistiche=new ArrayList<Statistica>();
	
	public Giocatore(String nome) {
		this.nome=nome;
	}
	
	public void setStat(int statId,int punti) {
		if(statId==-1)
			this.vita=this.vita+punti;
		else
			this.statistiche.get(statId).setValore(punti);
	}
	
	public int getVita() {
		return this.vita;
	}
	
	public String getNome() {
		return this.nome;
	}
}
