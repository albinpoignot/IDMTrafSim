package trafsim;

import javax.swing.JFrame;

/**
 * Representation of a traffic light in the system
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */
public class TrafficLight
{
	/**
	 * Position in the system
	 */
	private Coordinate position;
	/**
	 * Graphical representation 
	 */
	private JFrame image;
	/**
	 * Duration of the green light
	 */
	private int greenGap;
	/**
	 * Duration of the red light
	 */
	private int redGap;
	/**
	 * Duration of switching between green and red
	 */
	private int switchGap;
	
}
