package it.unibs.esame;
/**
 * Classe main del programma Gioco Di Ruolo- Si occupa dello scorrimento del programma, richiamando le altre classi quando gli servono
 * @author giorgio
 *
 */
public class GiocoDiRuoloMain {
	
	/**
	 * Main del programma, fa scorrere le istuzioni e lancia un'Exception in caso qualcosa vada storto. 
	 * Caso più probabile il file Xml non è formattato correttamente.
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MappaDiGioco mappa=new MappaDiGioco();
		Giocatore giocatore=new Giocatore(Supporto.leggiStringa("Benvenuto giocatore, inserisci il tuo nome: "));
		int scelta=sceltaMappa();
		try {
			boolean stato=mappa.setPercorso(Supporto.importaMappa(scelta));
			giocatore.addStat(Supporto.getStatistiche());
			giocatore.printStat();
			if(stato==true) {
				Casella posizione=mappa.getPozioneAttuale();
				while(!posizione.getTipo().equals("end") && giocatore.getVita()>0) {
					if(posizione.getTipo().equals("effect")){
						System.out.println("Attenzione, la prossima scelta porterà conseguenze...");
					}
					if(posizione.getTipo().equals("random")){
						System.out.println("Attenzione, la prossima scelta porterà conseguenze...");
						Casella evento=Supporto.getEventoRandom();
						for(Opzione op:evento.getOpzioni()) {
							op.setIdSuccessivo(posizione.getDestinazione());
							posizione.addOpzione(op);
							posizione.setTesto(evento.getTesto());
						}
					}
					Opzione opzioneScelta=posizione.getOpzioni().get(mostraOpzioni(posizione,giocatore.getNome()));
					int idNuovaPosizione=opzioneScelta.getIdSuccessivo();
					if(Supporto.getNomeStatistica(opzioneScelta.getStatId())!=null) {
						if(opzioneScelta.getPunti()>0)
							System.out.println("Hai guadagnato "+opzioneScelta.getPunti()+" punti "+Supporto.getNomeStatistica(opzioneScelta.getStatId()));
						else if(opzioneScelta.getPunti()<0)
							System.out.println("Hai perso "+opzioneScelta.getPunti()+" punti "+Supporto.getNomeStatistica(opzioneScelta.getStatId()));
					}else {
						if(opzioneScelta.getPunti()>0)
							System.out.println("Hai guadagnato "+opzioneScelta.getPunti()+" punti vita");
						else if(opzioneScelta.getPunti()<0)
							System.out.println("Hai perso "+opzioneScelta.getPunti()+" punti vita");
					}
					giocatore.setStat(opzioneScelta.getStatId(),opzioneScelta.getPunti());
					posizione=mappa.setPosizioneAttuale(idNuovaPosizione);
					if(posizione==null) {
						Exception e=new Exception("Sei finito in una città inesistente! Mappa Errata!");
						throw e;
					}
				}
				if(giocatore.getVita()>0) {
					System.out.println(posizione.getTesto());
					System.out.println("Ecco le tue statistiche");
					giocatore.printStat();
				}
				else {
					System.out.println("Sei morto! Riprova.");
				}
			}else {
				Exception e=new Exception("Inserire una mappa con delle caselle");
				throw e;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Mostra al giocatore le opzioni che ha disponibili, o procede del caso ne abbia solamente una.
	 * 
	 * @param posizione Posizione attuale del giocatore all'interno del percorso sulla mappa
	 * @param nomeGiocatore Serve per chiedergli cosa vuole fare
	 * @return l'opzione scelta dal giocatore. Non può arrivare a ritornare -1 grazie al metodo Supporto.leggiIntero();
	 */
	public static int mostraOpzioni(Casella posizione,String nomeGiocatore) {
		if(posizione.getOpzioni().size()>0) {
			int opzioneSelezionata = -1;
			String testoOpzioni=nomeGiocatore.concat(", cosa scegli?");
			if(posizione.getOpzioni().size()==1) {
				System.out.println("Hai un unica opzione:");
				System.out.println(posizione.getOpzioni().get(0).getTesto());
				opzioneSelezionata=0;
			}else {
				System.out.println(posizione.getTesto());
				System.out.println("Scegliere tra le seguenti opzioni:");
				for(int i=0;i<posizione.getOpzioni().size();i++) {
					System.out.println("["+i+"] - "+posizione.getOpzioni().get(i).getTesto());
				}
				opzioneSelezionata=Supporto.leggiIntero(testoOpzioni,0,posizione.getOpzioni().size()-1);
			}
			return opzioneSelezionata;
		}else {
			return -1;
		}
	}
	/**
	 * Selezione fra le mappe disponibili
	 * @return int che servira a support per aprire la mappa giusta
	 */
	public static int sceltaMappa() {
		System.out.println("[0] 1) base.xml");
		System.out.println("[1] 2) D1.xml");
		return Supporto.leggiIntero("Quale mappa si vuole utilizzare?",0,1);
	}
}
