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
import java.awt.GraphicsConfiguration;
import java.util.Random;

import trafsim.trafsim.Coordinate;

/**
 * description
 *
 */
public class CarGUI extends Canvas {

	
	private Coordinate position;
	private Integer height;
	private Integer width;
	private Color color;
	
	/**
	 * Default constructor
	 */
	public CarGUI() {
		
		// Init some properties of the draw
		setSize(width, height); 
		setBackground(Color.RED);
		
		
		
	}
	
	/**
	 * Overload constructor to initialize attributes
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
		setSize(width, height); 
		setBackground(Color.RED);
		
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

	/**
	 * Draw all the car in the carList attribute
	 */
	@Override
	public void paint(Graphics g) {
		
		update(g);
		/*g.setColor(Color.RED);
		//System.out.println("     Position in the CarGUI : " + position.getX());
		//g.drawRect( Math.round(position.getX()), Math.round(position.getY()), 10, 10 );
		g.fillRect( Math.round(position.getX()), Math.round(position.getY()), 10, 10 );*/
		
	} 
	
	@Override
	public void update(Graphics g) {

		g.setColor(color);
		//g.setColor(Color.RED);
		//System.out.println("     Position in the CarGUI : " + position.getX());
		//g.drawRect( Math.round(position.getX()), Math.round(position.getY()), 10, 10 );
		g.fillRect( Math.round(position.getX()), Math.round(position.getY()), 10, 10 );
	}

}
