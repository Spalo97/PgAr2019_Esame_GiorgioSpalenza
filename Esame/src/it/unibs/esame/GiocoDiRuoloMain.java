package it.unibs.esame;

public class GiocoDiRuoloMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MappaDiGioco mappa=new MappaDiGioco();
		try {
			boolean stato=mappa.setPercorso(Supporto.importaMappa());
			if(stato=true) {
				Casella posizione=mappa.getPozioneAttuale();
				while(!posizione.getTipo().equals("end")) {
					int idNuovaPosizione=mostraOpzioni(posizione);
				}
			}else {
				Exception e=new Exception("Inserire una mappa con delle caselle");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int mostraOpzioni(Casella posizione) {
		if(posizione.getOpzioni().size()>0) {
			int opzioneSelezionata = -1;
			if(posizione.getOpzioni().size()==1) {
				System.out.println("Hai un unica opzione:");
				System.out.println(posizione.getOpzioni().get(0));
			}else {
				System.out.println("Scegliere tra le seguenti opzioni:");
				for(int i=0;i<posizione.getOpzioni().size();i++) {
					System.out.println("["+i+"] - "+posizione.getOpzioni().get(i));
				}
			}
			return opzioneSelezionata;
		}else {
			return -1;
		}
	}
}
