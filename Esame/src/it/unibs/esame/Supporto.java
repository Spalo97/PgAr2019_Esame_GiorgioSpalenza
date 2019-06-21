package it.unibs.esame;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Supporto {
	
	private static XMLInputFactory xmlif = null;
	private static XMLStreamReader xmlr = null;
	
	private static String base = "Xml/1) base.xml";
	
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
}
