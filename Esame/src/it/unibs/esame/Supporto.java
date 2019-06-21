package it.unibs.esame;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Supporto {

	private final static String ERRORE_MINIMO= "Attenzione: e' richiesto un valore maggiore o uguale a ";
	private final static String ERRORE_MASSIMO= "Attenzione: e' richiesto un valore minore o uguale a ";
	private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
	private final static String ERRORE_STRINGA_VUOTA= "Attenzione: devi inserire un nome";
	
	private static XMLInputFactory xmlif = null;
	private static XMLStreamReader xmlr = null;

	private static String base = "Xml/2) D1.xml";
	
	private static Scanner lettore = creaScanner();
	
	private static ArrayList<Statistica> statistiche=new ArrayList<Statistica>(); 
	
	private static ArrayList<Casella> eventiRandom=new ArrayList<Casella>();
	public Supporto() {
		
	}
	/**
	 * Metodo che effettua un parsing del file Xml e crea oggetti di tipo
	 * Casella, inserendoli in un ArrayList che poi viene ritornato.
	 * @return caselle
	 * @throws Exception 
	 */
	public static ArrayList<Casella> importaMappa() throws Exception{
		ArrayList<Casella> caselle = new ArrayList<Casella>();
		Casella casella;
		Casella evento;
		boolean ev=false;
		Opzione opzione;
		aperturaXml(base);
		try {
			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) { 
				case XMLStreamConstants.START_DOCUMENT: break;
				case XMLStreamConstants.START_ELEMENT:
					switch(xmlr.getLocalName()) {
					case "events":
						ev=true;
						break;
					case "event":
						evento=new Casella();
						evento.setId(Integer.parseInt(xmlr.getAttributeValue(0)));
						evento.setTipo("random");
						eventiRandom.add(evento);
						break;
					case "cell":
						casella=new Casella();
						casella.setId(Integer.parseInt(xmlr.getAttributeValue(0)));
						casella.setTipo(xmlr.getAttributeValue(1));
						for(int i=0;i<xmlr.getAttributeCount();i++) {
							if(xmlr.getAttributeLocalName(i).equals("destination")) {
								casella.setDestinazione(Integer.parseInt(xmlr.getAttributeValue(i)));
							}
						}
						caselle.add(casella);
						break;
					case "description":
						if(ev)
							eventiRandom.get(eventiRandom.size()-1).setTesto(xmlr.getElementText());
						else
							caselle.get(caselle.size()-1).setTesto(xmlr.getElementText());
						break;
					case "option":
						if(ev) {
							int destinazione=-1;
							int statId=-1;
							int delta=0;
							for(int i=0;i<xmlr.getAttributeCount();i++) {
								switch(xmlr.getAttributeLocalName(i)) {
								case "destination":
									destinazione=Integer.parseInt(xmlr.getAttributeValue(i));
									break;
								case "statId":
									statId=Integer.parseInt(xmlr.getAttributeValue(i));
									break;
								case "delta":
									delta=Integer.parseInt(xmlr.getAttributeValue(i));
									break;
								case "lifepoints":
									delta=Integer.parseInt(xmlr.getAttributeValue(i));
									break;
								}
							}
							opzione=new Opzione(destinazione,delta,xmlr.getElementText(),statId);
							eventiRandom.get(eventiRandom.size()-1).addOpzione(opzione);
						}else {	
							int destinazione=-1;
							int statId=-1;
							int delta=0;
							for(int i=0;i<xmlr.getAttributeCount();i++) {
								switch(xmlr.getAttributeLocalName(i)) {
								case "destination":
									destinazione=Integer.parseInt(xmlr.getAttributeValue(i));
									break;
								case "statId":
									statId=Integer.parseInt(xmlr.getAttributeValue(i));
									break;
								case "delta":
									delta=Integer.parseInt(xmlr.getAttributeValue(i));
									break;
								case "lifepoints":
									delta=Integer.parseInt(xmlr.getAttributeValue(i));
									break;
								}
							}
							opzione=new Opzione(destinazione,delta,xmlr.getElementText(),statId);
							caselle.get(caselle.size()-1).addOpzione(opzione);
						}
						break;
					case "character":
						break;
					case "defstat":
						int id=Integer.parseInt(xmlr.getAttributeValue(0));
						String nome=xmlr.getAttributeValue(1);
						int valore=Integer.parseInt(xmlr.getAttributeValue(2));
						statistiche.add(new Statistica(id,nome,valore));
						break;
					default:
						if(!xmlr.getLocalName().equals("rpg") && !xmlr.getLocalName().equals("map")
								&& !xmlr.getLocalName().equals("event") && !xmlr.getLocalName().equals("events")) {
								String value=xmlr.getLocalName();
								Exception e=new Exception("Tag non valido");
								throw e;
							}
						break;
					}
					break;
				case XMLStreamConstants.END_ELEMENT: 
					if(xmlr.getLocalName().equals("events"))
						ev=false;
					break;
				case XMLStreamConstants.COMMENT: break;
				case XMLStreamConstants.CHARACTERS:break;
				}
				xmlr.next();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return caselle;	
	}
		
	private static void aperturaXml(String fileSelezionato) {
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(fileSelezionato, new FileInputStream(fileSelezionato));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
	}
		
	public static int leggiIntero (String messaggio, int minimo, int massimo){
		boolean finito = false;
		int valoreLetto = 0;
		do{
			System.out.print(messaggio);
			try{
				valoreLetto = lettore.nextInt();
				if (valoreLetto >= minimo && valoreLetto<= massimo)
			    	finito = true;
			    else
			    	if (valoreLetto < minimo)
			    		System.out.println(ERRORE_MINIMO + minimo);
			    	else
			    		System.out.println(ERRORE_MASSIMO + massimo);
			}
			catch (InputMismatchException e){
				System.out.println(ERRORE_FORMATO);
				String daButtare = lettore.next();
			}
		} while (!finito);
		return valoreLetto;
		}
	private static Scanner creaScanner (){
		Scanner creato = new Scanner(System.in);
		creato.useDelimiter(System.getProperty("line.separator"));
		return creato;
	}
	
	public static String leggiStringa (String messaggio){
		boolean finito=false;
		String lettura = null;
		do{
			System.out.print(messaggio);
			lettura = lettore.next();
			lettura = lettura.trim();
			if (lettura.length() > 0)
				finito=true;
			else
				System.out.println(ERRORE_STRINGA_VUOTA);
		} while (!finito);	   
		return lettura;
	}
	public static Casella getEventoRandom() {
		Random random = new Random();
		return eventiRandom.get(random.nextInt(eventiRandom.size()));
	}
	public static String getNomeStatistica(int statId) {
		for(Statistica stat:statistiche) {
			if(stat.getId()==statId)
				return stat.getNome();
		}
		return null;
	}
}
