package no.uib.smo015.info216.parseXML;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import no.uib.smo015.info216.Journey.Journey;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class used to represent a parser for a document
 * @author Sindre
 *
 */

public class XMLParser {

	private Document doc;
	private List<Journey> journeyList;

	/**
	 * Constructor for the XMLParser class
	 */
	public XMLParser() {
		journeyList = new ArrayList<>();
	}

	/**
	 * Method used to retrieve a document from a given url
	 * @param url The url wit the xml data
	 * @param newDestination The file name for the new file
	 */
	public void getDocumentFromURL(URL url, File newDestination){
		try {
			FileUtils.copyURLToFile(url, newDestination);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder = null;
		try {
			dbuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		try {
			setDoc(dbuilder.parse(newDestination));
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		System.out.println("Root node: " + getDoc().getDocumentElement().getNodeName());
	}

	public NodeList getChildNodes(){
		return doc.getElementsByTagName("strekning");
	}

	/**
	 * Method used to populate the list of journeys based on the xml data
	 */
	public void createJourneys(){
		NodeList nlist = getChildNodes();

		for(int i = 0; i < nlist.getLength(); i++){
			Node node = nlist.item(i);

			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element elem = (Element) node;

				NodeList childNodes = elem.getChildNodes();

				Map<String, String> journeyInfo = new HashMap<>();

				String label = "";

				for(int k = 0; k < childNodes.getLength(); k++){
					label = childNodes.item(k).getNodeName();
					journeyInfo.put(label, elem.getElementsByTagName(label).item(0).getTextContent());
				}		
				getJourneyList().add((new Journey(journeyInfo.get("id"), journeyInfo.get("beskrivelse"), journeyInfo.get("normalreisetid"), journeyInfo.get("lengde"))));
			}
		}

	}


	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public List<Journey> getJourneyList() {
		return journeyList;
	}

	public void setJourneyList(List<Journey> journeyList) {
		this.journeyList = journeyList;
	}

}







