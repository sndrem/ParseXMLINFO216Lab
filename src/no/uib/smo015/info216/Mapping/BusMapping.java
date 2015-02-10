package no.uib.smo015.info216.Mapping;

import java.util.List;

import no.uib.smo015.info216.Journey.Journey;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * Class to map the bus data to a rdf-model
 * @author Sindre Moldeklev
 * @version 1.0.0
 */
public class BusMapping {

	private Model busModel;
	private final String BUS = "http://sindreMoldeklev.no/ontologi/buss#";
	private Property id;
	private Property startPoint;
	private Property endPoint;
	private Property travelTime;
	private Property length;
	private List<Journey> myJourneys;

	/**
	 * Constructor for the busMapping class
	 * @param journeys The journeys you wish to map
	 */
	public BusMapping(List<Journey> journeys){
		busModel = ModelFactory.createDefaultModel();
		prefixMapping();
		myJourneys = journeys;
		createProperties();
		mapJourneys();
	}

	/**
	 * Method to write the model to the console in TURTLE
	 */
	public void writeModel(){
		busModel.write(System.out, "TURTLE");
	}

	/**
	 * Method to map the journeys to the model
	 */
	public void mapJourneys(){
		System.out.println("Størrelse på antall reiser: " + myJourneys.size());
		Resource res = null;
		for(int i = 0; i < myJourneys.size(); i++){
			Journey tempJourney = myJourneys.get(i);

			res = busModel.createResource(BUS + tempJourney.getId());
			res.addProperty(id, busModel.createTypedLiteral(tempJourney.getId())).addLiteral(startPoint, tempJourney.getStartPoint())
			.addLiteral(endPoint, tempJourney.getEndPoint())
			.addLiteral(length, tempJourney.getLength())
			.addLiteral(travelTime, tempJourney.getTravelTime());

			busModel.add(res, RDF.type, "busstur");
		}		
	}

	/**
	 * Method to set prefixes for the model
	 */
	private void prefixMapping(){
		busModel.setNsPrefix("buss", "http://sindreMoldeklev.no/ontologi/buss#");
		busModel.setNsPrefix("xml", "http://www.w3.org/2001/XMLSchema#");
		busModel.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
	}

	/**
	 * Method to create properties for the model
	 */
	private void createProperties(){
		id = busModel.createProperty(BUS + "id");
		startPoint = busModel.createProperty(BUS + "startPoint");
		endPoint = busModel.createProperty(BUS + "endPoint");
		travelTime = busModel.createProperty(BUS + "travelTime");
		length = busModel.createProperty(BUS + "length");


	}

}
