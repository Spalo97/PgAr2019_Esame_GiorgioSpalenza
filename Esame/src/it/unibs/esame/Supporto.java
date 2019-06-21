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
	
	private static String base = "Xml/1)base.xml";
	
	public Supporto() {
		
	}
	/**
	 * Metodo che effettua un parsing del file Xml e crea oggetti di tipo
	 * Casella, inserendoli in un ArrayList che poi viene ritornato.
	 * @return caselle
	 */
	public static ArrayList<Casella> importaMappa(){
		ArrayList<Casella> caselle = new ArrayList<Casella>();
		Casella casella;
		Opzione opzione;
		boolean description=false;
		boolean option=false;
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
						if(xmlr.getAttributeCount()>2) 
							casella.setOpzioni(Integer.parseInt(xmlr.getAttributeValue(2)));
						caselle.add(casella);
					}else if(xmlr.getLocalName().equals("description")) {
						description=true;
					}else if(xmlr.getLocalName().equals("option")) {
						option=true;
						int destinazione=Integer.parseInt(xmlr.getAttributeValue(0));
						int puntiVita=0;
						if(xmlr.getAttributeCount()>1)
							puntiVita=Integer.parseInt(xmlr.getAttributeValue(1));
						opzione=new Opzione(destinazione,puntiVita);
					}
				case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
					System.out.println("END-Tag " + xmlr.getLocalName()); break;
					case XMLStreamConstants.COMMENT:
					System.out.println("// commento " + xmlr.getText()); break; // commento: ne stampa il contenuto
				case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
					if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
						System.out.println("-> " + xmlr.getText());
					break;
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
