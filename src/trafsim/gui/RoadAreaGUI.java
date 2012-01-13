/**
 * Class RoadAreaGUI
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 *
 */
package trafsim.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import trafsim.trafsim.Car;
import trafsim.trafsim.Coordinate;
import trafsim.trafsim.IDM;
import trafsim.trafsim.Road;

/**
 * Manage the draw of a road area. For now, there is only one road in
 * a road area.
 */
public class RoadAreaGUI extends JPanel implements ActionListener {

	/**
	 * Used in case of serialization. Useless here.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Height of the RoadAreaGUI
	 */
	private Integer height;
	
	/**
	 * Model of the RoadAreaGUI
	 */
	private IDM model;
	
	/**
	 * A street in the system
	 */
	private Road road;
	
	/**
	 * Timer for repainting.
	 */
	private Timer timer;
	
	/**
	 * A semaphore
	 */
	private final Semaphore sem = new Semaphore(1, false); 
	
	/**
	 * Width of the RoadAreaGUI
	 */
	private Integer width;

	/**
	 * Default constructor. Set the size of the road area and draw a black border. Create a new carList and a new road
	 * then initialize attributes with this new instances.
	 * @param model The model that should be used
	 */
	public RoadAreaGUI(IDM model) {
		width = 1020;
		height = 100;
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		road = new Road(new Coordinate(10f, 30f), 65, 1000, 25, model);
	}
	
	/**
	 * @return the model
	 */
	public IDM getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(IDM model) {
		this.model = model;
	}
	
	/**
	 * Return the preferred size for this JComponent
	 */
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	/**
	 * @return the road
	 */
	public Road getRoad() {
		return road;
	}

	/**
	 * @param road the road to set
	 */
	public void setRoad(Road road) {
		this.road = road;
	}

	/**
	 * @return the sem
	 */
	public Semaphore getSem() {
		return sem;
	}
	
	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	/**
	 * Automatically called by a Timer. Updates the cars's velocities, release semaphores then repaint the GUI.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {			
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sem.release();
		
		this.repaint();
	}
	
	/**
	 * Custom painting of the RoadAreaGUI : draw the road and all the cars
	 */
	@Override
	public void paintComponent(Graphics g) {
		road.getImage().paint(g);
		
		for (Car car : road.getCarList()) {
			car.getImage().paint(g);
		}
	}
	
	/**
	 * Set the model of the road in this road area
	 * @param model
	 */
	public void setRoadModel(IDM model) {
		road.setModel(model);
	}
	
	/**
	 * Update cars in the road. Actually, it calls the <code>updateCars()</code> method in class <code>Road</code>
	 */
	public void updateRoad() {
		road.updateCars();
	}
}
