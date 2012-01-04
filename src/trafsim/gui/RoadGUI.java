/**
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */
package trafsim.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;

import trafsim.trafsim.Car;
import trafsim.trafsim.Coordinate;
import trafsim.trafsim.ListCar;

/**
 * Graphic manipulation of a street. <strong>Only for drawing it !</strong>
 *
 */
public class RoadGUI extends Canvas {

	/**
	 * Used in case of serialization. Useless here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * List of the cars on the road
	 */
	private ListCar carList;
	
	/**
	 * Position of the road
	 */
	private Coordinate position;
	
	/**
	 * Height of the road
	 */
	private Integer height;
	
	/**
	 * Width of the road
	 */
	private Integer width;
	
	/**
	 * Default constructor
	 */
	public RoadGUI() {
		
		// Init some properties of the draw
		setSize(width, height); 
		setBackground(Color.darkGray);
		
	}
	
	/**
	 * Overload constructor to init attributes
	 * @param position Position of the road
	 * @param height Height of the road
	 * @param width Width of the road
	 */
	public RoadGUI( Coordinate position, Integer height, Integer width ) {
		
		// Init attributes
		this.position = position;
		this.height = height;
		this.width = width;
		
		// Init some properties of the draw
		setSize(width, height); 
		setBackground(Color.darkGray);
	}

	/**
	 * @param config
	 */
	public RoadGUI(GraphicsConfiguration config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return the carList
	 */
	public ListCar getCarList() {
		return carList;
	}

	/**
	 * @param carList the carList to set
	 */
	public void setCarList(ListCar carList) {
		this.carList = carList;
	}
	
	/**
	 * Draw all the cars in the carList attribute
	 */
	public void paint(Graphics g) {
		
		// Draw the road
		g.setColor(Color.DARK_GRAY);
		g.fillRect(Math.round(position.getX()), Math.round(position.getY()), width - 1, height - 1);
				
		// Draw the border
		g.setColor(Color.BLUE);
		g.drawRect(Math.round(position.getX()), Math.round(position.getY()), width - 1, height - 1);
		
		// Draw the middle line
		g.setColor(Color.WHITE);
		g.fillRect(Math.round(position.getX()) + 1, Math.round(position.getY()) + 30, width - 5, 3);
		
		// Draw the cars
		/*g.setColor(Color.RED);
	    
		/*for (Car car : carList) {
			//car.paint();
			g.setColor(Color.RED);
			System.out.println("     Position of the Car : " + car.getPosition().getX());
			//g.drawRect( Math.round(car.getPosition().getX()), Math.round(car.getPosition().getY()), 10, 10 );
			g.fillRect( Math.round(car.getPosition().getX()), Math.round(car.getPosition().getY()), 10, 10 );
		}*/

	}

}
