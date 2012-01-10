package trafsim.trafsim;

import trafsim.gui.CarGUI;


/**
 * Representation of a car in the system
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */

public class Car
{	
	/**
	 * Desired velocity for all cars.
	 */
	private Float desiredVelocity = 15f;
	
	public Float getDesiredVelocity() {
		return desiredVelocity;
	}

	public void setDesiredVelocity(Float desiredVelocity) {
		this.desiredVelocity = desiredVelocity;
	}

	/**
	 * Car's unique ID
	 */
	private Integer identifier;
	/**
	 * Car's current velocity
	 */
	private Float velocity;
	/**
	 * Car's current 2D coordinate
	 */
	private Coordinate position;

	/**
	 * Graphical representation of the car
	 */
	private CarGUI image;

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
		this.image.setPosition(position);
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
	
	/**
	 * @return the image
	 */
	public CarGUI getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(CarGUI image) {
		this.image = image;
	}
	
	/**
	 * Default constructor
	 */
	public Car()
	{
		position = new Coordinate();
		velocity = new Float(0);
		image = new CarGUI(position, 20, 20);
	}
	
	/**
	 * Overload constructor to initialize attributes
	 * @param x The position of the car on the x axis
	 * @param y The position of the car on the y axis
	 * @param v The initial velocity
	 * @param dv Desired velocity
	 */
	public Car( Float x, Float y, Float v, Float dv )
	{
		position = new Coordinate( x, y );
		velocity = v;
		desiredVelocity = dv;
		image = new CarGUI(position, 20, 20);
	}
	
	/**
	 * Repaint the CarGUI associated instance
	 */
	public void paint()
	{
		image.repaint();
	}
	
}
