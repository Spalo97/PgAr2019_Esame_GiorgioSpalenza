package it.unibs.esame;

public class GiocoDiRuoloMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MappaDiGioco mappa=new MappaDiGioco();
		Giocatore giocatore=new Giocatore(Supporto.leggiStringa("Benvenuto giocatore, inserisci il tuo nome: "));
		try {
			boolean stato=mappa.setPercorso(Supporto.importaMappa());
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
							op.setIdSuccessivo(evento.getDestinazione());
						}
					}
					Opzione opzioneScelta=posizione.getOpzioni().get(mostraOpzioni(posizione,giocatore.getNome()));
					int idNuovaPosizione=opzioneScelta.getIdSuccessivo();
					if(opzioneScelta.getPunti()>0)
						System.out.println("Hai guadagnato "+opzioneScelta.getPunti()+" punti "+Supporto.getNomeStatistica(opzioneScelta.getStatId()));
					else if(opzioneScelta.getPunti()<0)
						System.out.println("Hai perso "+opzioneScelta.getPunti()+" punti "+Supporto.getNomeStatistica(opzioneScelta.getStatId()));
					giocatore.setStat(opzioneScelta.getStatId(),opzioneScelta.getPunti());
					posizione=mappa.setPosizioneAttuale(idNuovaPosizione);
					if(posizione==null) {
						Exception e=new Exception("Sei finito in una città inesistente! Mappa Errata!");
						throw e;
					}
				}
				if(giocatore.getVita()>0)
					System.out.println(posizione.getTesto());
				else
					System.out.println("Sei morto! Riprova.");
			}else {
				Exception e=new Exception("Inserire una mappa con delle caselle");
				throw e;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int mostraOpzioni(Casella posizione,String nomeGiocatore) {
		if(posizione.getOpzioni().size()>0) {
			int opzioneSelezionata = -1;
			String testoOpzioni=nomeGiocatore.concat(", cosa scegli?");
			if(posizione.getOpzioni().size()==1) {
				System.out.println("Hai un unica opzione:");
				System.out.println(posizione.getOpzioni().get(0).getTesto());
				opzioneSelezionata=0;
			}else {
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
}
