package it.unibs.esame;

import java.util.ArrayList;

public class MappaDiGioco {
	 
	private ArrayList<Casella> percorso=new ArrayList<Casella>();
	private Casella posizioneAttuale=null;
	
	public MappaDiGioco() {
		
	}
	
	public boolean setPercorso(ArrayList<Casella> percorso) {
		this.percorso=percorso;
		if(this.percorso.size()>0){
			posizioneAttuale=this.percorso.get(0);
			return true;
		}
		else {
			return false;
		}
	}
	
	public Casella getPozioneAttuale() {
		return posizioneAttuale;
	}
	
	public void setPosizioneAttuale(Casella nuovaPosizione) {
		this.posizioneAttuale=nuovaPosizione;
	}
}