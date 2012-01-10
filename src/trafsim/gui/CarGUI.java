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
	 * Overload constructor to initialize attributes. The color is set to a random one.
	 * @param position Initial position of the car
	 * @param height Height of the car
	 * @param width Width of the car
	 */
	public CarGUI( Coordinate position, Integer height, Integer width ) {
		
		// Init attributes
		this.position = position;
		this.height = height;
		this.width = width;
		
		// Init some properties of the draw
		setSize(this.width, this.height); 
		//setBackground(Color.RED);
		
		Random randomGenerator = new Random();
		int red = randomGenerator.nextInt(255);
		int green = randomGenerator.nextInt(255);
		int blue = randomGenerator.nextInt(255);

		color = new Color(red,green,blue);
	}

	
	/**
	 * @return the carList
	 */
	/*public ListCar getCarList() {
		return carList;
	}*/

	/**
	 * @param carList the carList to set
	 */
	/*public void setCarList(ListCar carList) {
		this.carList = carList;
	}*/
	
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
		/*g.setColor(Color.RED);
		//System.out.println("     Position in the CarGUI : " + position.getX());
		//g.drawRect( Math.round(position.getX()), Math.round(position.getY()), 10, 10 );
		g.fillRect( Math.round(position.getX()), Math.round(position.getY()), 10, 10 );*/
		
	} 
	
	/**
	 * Draw the car.
	 * @see javax.swing.JFrame#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {

		g.setColor(color);
		//g.setColor(Color.RED);
		//System.out.println("     Position in the CarGUI : " + position.getX());
		//g.drawRect( Math.round(position.getX()), Math.round(position.getY()), 10, 10 );
		g.fillRect( Math.round(position.getX()), Math.round(position.getY()), 10, 10 );
	}

}
