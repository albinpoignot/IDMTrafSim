/**
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */
package trafsim.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;

import trafsim.trafsim.Coordinate;

/**
 * Graphic manipulation of a street. <strong>Only for drawing it !</strong>
 *
 */
public class RoadGUI extends Canvas {

	/**
	 * Used in case of serialization. Useless here.
	 */
	private static final long serialVersionUID = 1L;

	
	//private ListCar carList;
	private Coordinate position;
	private Integer height;
	private Integer width;
	
	/**
	 * Default constructor
	 */
	public RoadGUI() {
		
		// Init some properties of the draw
		setSize(width, height); 
		setBackground(Color.darkGray);
		
	}
	
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
	 * Draw all the car in the carList attribute
	 */
	public void paint(Graphics g) {
		
		// Draw the border
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width - 1, height - 1); // draw border
	    
		// Draw the middle line
		g.setColor(Color.WHITE);
		g.drawRect(2, 30, width - 5, 3);
		g.fillRect(2, 30, width - 5, 3);
		
		g.setColor(Color.RED);
	    /*for (Car car : carList) {
			g.drawOval(Integer.parseInt(car.getPosition().getX()), 10, 10, 10);
		}*/
	} 

}
