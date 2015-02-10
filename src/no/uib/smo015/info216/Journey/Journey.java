package no.uib.smo015.info216.Journey;

import no.uib.smo015.info216.Mapping.BusUtil;

/**
 * Class representing a journey as described from the XML document
 * @author Sindre
 *
 */
public class Journey {

	private String id;
	private String startPoint;
	private String endPoint;
	private String travelTime;
	private String length;

	/**
	 * Constructor for the Journey class
	 * @param id
	 * @param desc
	 * @param travelTime
	 * @param length
	 */
	public Journey(String id, String desc, String travelTime, String length){
		this.id = id;
		startPoint = BusUtil.getStartPoint(desc);
		endPoint = BusUtil.getEndPoint(desc);
		this.travelTime = travelTime;
		this.length = length;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	/**
	 * @return the travelTime
	 */
	public String getTravelTime() {
		return travelTime;
	}

	/**
	 * @param travelTime the travelTime to set
	 */
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
	}

	/**
	 * toString method used to give some useful information about a journey
	 */
	public String toString(){
		String info = "Journey id: " + id + " travels from " + getStartPoint() + " to " + getEndPoint() + " and uses approx. " + travelTime + " min" 
				+ " and the length is " + length + " meters";
		return info;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

}
