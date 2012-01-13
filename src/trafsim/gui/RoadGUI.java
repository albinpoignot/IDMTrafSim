/**
 * Class RoadGUI
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 * 
 */
package trafsim.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import trafsim.trafsim.Coordinate;
import trafsim.trafsim.ListCar;

/**
 * Graphic manipulation of a street. <strong>Only for drawing it !</strong>
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
	 * Overload constructor to initialize attributes
	 * @param position Position of the road
	 * @param height Height of the road
	 * @param width Width of the road
	 */
	public RoadGUI( Coordinate position, Integer height, Integer width ) {
		this.position = position;
		this.height = height;
		this.width = width;

		setSize(width, height); 
		setBackground(Color.darkGray);
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
	
	/* (non-javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		
		update(g);

	}
	
	/**
	 * Draw the road (background, border and middle line)
	 * @see java.awt.Canvas#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics g) {
		
		// Draw the road
		g.setColor(Color.DARK_GRAY);
		g.fillRect(Math.round(position.getX()), Math.round(position.getY()), width - 1, height - 1);
				
		// Draw the border
		g.setColor(Color.BLUE);
		g.drawRect(Math.round(position.getX()), Math.round(position.getY()), width - 1, height - 1);
		
		// Draw the middle line
		g.setColor(Color.WHITE);
		g.fillRect(Math.round(position.getX()) + 1, Math.round(position.getY()) + 30, width - 5, 3);
		
	}

}
