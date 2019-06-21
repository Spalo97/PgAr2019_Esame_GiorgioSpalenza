package it.unibs.esame;

import java.util.ArrayList;

public class Giocatore {
	
	private String nome;
	private int vita=100;
	private static ArrayList<Statistica> statistiche=new ArrayList<Statistica>();
	
	public Giocatore(String nome) {
		this.nome=nome;
	}
	
	/**
	 * Effetua un cambio valore sulla statistica desiderata
	 * @param statId Id statistica desiderata
	 * @param punti Valore intero da aggiungere(si sottrae se negativo)
	 */
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

	public void addStat(ArrayList<Statistica> statistiche) {
		this.statistiche=statistiche;
	}
	
	/**
	 * Stampa a video le statistiche del giocatore
	 */
	public void printStat() {
		System.out.println("Ecco le tue statistiche");
		System.out.println("-Vita: "+this.vita);
		for(int i=1;i<statistiche.size();i++) {
			System.out.println("-"+statistiche.get(i).getNome()+": "+statistiche.get(i).getValore());
		}
	}
}
