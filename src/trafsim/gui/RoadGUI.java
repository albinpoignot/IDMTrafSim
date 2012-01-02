/**
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */
package trafsim.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;

//import trafsim.trafsim.Car;
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

	
	private ListCar carList;
	private static Integer height = 65;
	private static Integer width = 1000;
	
	/**
	 * Default constructor
	 */
	public RoadGUI(ListCar carList) {

		// Initialization of attributes
		this.setCarList(carList);
		
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
	 * Draw all the car in the carList attribute
	 */
	public void paint(Graphics g) {
		
		// Draw the border
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width - 1, height - 1); // draw border
	    
		// Draw the middle line
		g.setColor(Color.WHITE);
		g.drawRect(0, 30, width - 1, 5);
		
		g.setColor(Color.RED);
	    /*for (Car car : carList) {
			g.drawOval(Integer.parseInt(car.getPosition().getX()), 10, 10, 10);
		}*/
	} 

}
