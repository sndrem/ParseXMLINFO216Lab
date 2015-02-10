package no.uib.smo015.info216.Mapping;

/**
 * A helping class used to split the description of a journey
 * into start point and end point
 * @author Sindre
 *
 */
public class BusUtil {

	/**
	 * Consctructor which does not do anything
	 */
	public BusUtil(){

	}

	/**
	 * Method to return the start point of a journey
	 * The method splits the string with a -
	 * @param desc
	 * @return the start point
	 */
	public static String getStartPoint(String desc){
		String[] route = desc.split("-");
		return route[0];
	}

	/**
	 * Method to return the end point of a journey
	 * The method splits the string with a -
	 * @param desc
	 * @return the end point of the journey
	 */
	public static String getEndPoint(String desc){
		String[] route = desc.split("-");
		return route[1];
	}

}
