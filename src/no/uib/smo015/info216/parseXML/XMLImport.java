package no.uib.smo015.info216.parseXML;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import no.uib.smo015.info216.Journey.Journey;
import no.uib.smo015.info216.Mapping.BusMapping;

/**
 * Main class for the bus data
 * @author Sindre
 *
 */
public class XMLImport {

	public static void main(String[] args) {
		XMLParser p = new XMLParser();
		URL url = null;
		try {
			url = new URL("http://www.reisetider.no/xml/strekninger.xml");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File newDestination = new File("XML/strekninger.xml");

		p.getDocumentFromURL(url, newDestination);

		p.createJourneys();

		List<Journey> t = p.getJourneyList();

		System.out.println("Størrelse på reiselisten: " + t.size() + " reiser registrert");


		BusMapping mapping = new BusMapping(t);

		mapping.writeModel();
	}

}
