/**
 * description
 *
 * @author
 * @version
 *
 */
package trafsim.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import trafsim.trafsim.Coordinate;

/**
 * description
 *
 */
public class CarGUI extends Canvas {

	
	/**
	 * Used in case of serialization. Useless here.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Length of all cars
	 */
	private static Integer LENGTH = 10;

	/**
	 * Position of the car
	 */
	private Coordinate position;
	
	/**
	 * Height of the car
	 */
	private Integer height;
	
	/**
	 * Width of the car
	 */
	private Integer width;
	
	/**
	 * Color of the car
	 */
	private Color color;
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}


	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	/**
	 * @return the lENGTH
	 */
	public static Integer getLENGTH() {
		return LENGTH;
	}

	/**
	 * Overload constructor to initialize attributes. The color is set to a random one.
	 * @param position Initial position of the car
	 * @param height Height of the car
	 * @param width Width of the car
	 */
	public CarGUI( Coordinate position, Integer height, Integer width ) {
		
		this.position = position;
		this.height = height;
		this.width = width;
		
		setSize(this.width, this.height); 
		
		Random randomGenerator = new Random();
		int red = randomGenerator.nextInt(255);
		int green = randomGenerator.nextInt(255);
		int blue = randomGenerator.nextInt(255);

		color = new Color(red,green,blue);
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

	/* (non-javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		
		update(g);
		
	} 
	
	/**
	 * Draw the car
	 * @see javax.swing.JFrame#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		g.setColor(color);
		g.fillRect( Math.round(position.getX()), Math.round(position.getY()), LENGTH, LENGTH );
	}

}
