package trafsim;

import javax.swing.JFrame;

/**
 * Representation of a road segment in the system
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */
public class Road 
{
	/**
	 * Start position of this road segment
	 */
	private Coordinate positionDepart;
	/**
	 * End position of this road segment
	 */
	private Coordinate positionFin;
	/**
	 * Graphical representation of the road segment
	 */
	private JFrame image;
	/**
	 * Speed limit on this road segment
	 */
	private float velocityLimit;
	
	/**
	 * Overload Constructor
	 * @param start : Start position
	 * @param end : End position
	 * @param speedLimit : speed limit on the segment
	 */
	Road( Coordinate start, Coordinate end, float speedLimit )
	{
		positionDepart = start;
		positionFin = end;
		velocityLimit = speedLimit;
	}
	
}
