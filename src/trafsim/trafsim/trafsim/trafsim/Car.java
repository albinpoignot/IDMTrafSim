package trafsim.trafsim;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;


/**
 * Representation of a car in the system
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */

public class Car extends Canvas
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public Car()
	{
		position = new Coordinate();
		velocity = new Float(0);
	}
	
	public Car( Integer x, Integer y, Float v )
	{
		position = new Coordinate( x, y );
		velocity = v;
	}
	
	public Car(GraphicsConfiguration config) 
	{
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) 
	{
		g.setColor(Color.RED);
		g.drawRect( position.getX(), position.getY(), 10, 10 );
		g.fillRect( position.getX(), position.getY(), 10, 10 );
	}
	
}
