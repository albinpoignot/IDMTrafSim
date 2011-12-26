package trafsim;

import javax.swing.JFrame;

/**
 * Representation of a car in the system
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */

public class Car 
{
	/**
	 * car's unique ID
	 */
	private int identifier;
	/**
	 * car's current velocity
	 */
	private float velocity;
	/**
	 * car's current 2D coordinate
	 */
	private Coordinate position;
	/**
	 * car's graphical representation
	 */
	private JFrame image;
	
	
	Car()
	{
		position = new Coordinate();
		velocity = 0;
	}
	
}
