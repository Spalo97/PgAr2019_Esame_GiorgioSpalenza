package it.unibs.esame;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Supporto {

	private final static String ERRORE_MINIMO= "Attenzione: e' richiesto un valore maggiore o uguale a ";
	private final static String ERRORE_MASSIMO= "Attenzione: e' richiesto un valore minore o uguale a ";
	private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
	
	private static XMLInputFactory xmlif = null;
	private static XMLStreamReader xmlr = null;

	private static String base = "Xml/1) base.xml";
	
	private static Scanner lettore = creaScanner();
	
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
		Opzione opzione;
		aperturaXml(base);
		try {
			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) { 
				case XMLStreamConstants.START_DOCUMENT: break;
				case XMLStreamConstants.START_ELEMENT: 
					if(xmlr.getLocalName().equals("cell")) {
						casella=new Casella();
						casella.setId(Integer.parseInt(xmlr.getAttributeValue(0)));
						casella.setTipo(xmlr.getAttributeValue(1));
						caselle.add(casella);
					}else if(xmlr.getLocalName().equals("description")) {
						caselle.get(caselle.size()-1).setTesto(xmlr.getElementText());
					}else if(xmlr.getLocalName().equals("option")) {
						int destinazione=Integer.parseInt(xmlr.getAttributeValue(0));
						int puntiVita=0;
						if(xmlr.getAttributeCount()>1)
							puntiVita=Integer.parseInt(xmlr.getAttributeValue(1));
						opzione=new Opzione(destinazione,puntiVita,xmlr.getElementText());
						caselle.get(caselle.size()-1).addOpzione(opzione);
					}else {
						if(!xmlr.getLocalName().equals("rpg") && !xmlr.getLocalName().equals("map")) {
							Exception e=new Exception("Tag non valido");
							throw e;
						}
					}
				case XMLStreamConstants.END_ELEMENT: break;
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
		System.out.print(messaggio);
		return lettore.next();
	}
}
