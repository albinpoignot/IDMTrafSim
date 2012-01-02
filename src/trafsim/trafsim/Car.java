package trafsim.trafsim;

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
	private Integer identifier;
	/**
	 * car's current velocity
	 */
	private Float velocity;
	/**
	 * car's current 2D coordinate
	 */
	private Coordinate position;
	/**
	 * car's graphical representation
	 */
	private JFrame image;

	/**
	 * @return the identifier
	 */
	public Integer getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the position
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}

	/**
	 * @return the velocity
	 */
	public Float getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(Float velocity) {
		this.velocity = velocity;
	}
	
	Car()
	{
		position = new Coordinate();
		velocity = new Float(0);
	}
	
}
