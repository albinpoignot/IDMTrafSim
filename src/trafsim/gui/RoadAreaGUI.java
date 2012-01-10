/**
 * Principal file of the application
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
import javax.swing.RepaintManager;
import javax.swing.Timer;

import trafsim.trafsim.Car;
import trafsim.trafsim.Coordinate;
import trafsim.trafsim.IDM;
import trafsim.trafsim.ListCar;
import trafsim.trafsim.ListTrafficLight;
import trafsim.trafsim.Road;
import trafsim.trafsim.TrafficLight;

/**
 * No description
 *
 */
public class RoadAreaGUI extends JPanel implements ActionListener {

	/**
	 * Used in case of serialization. Useless here.
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Timer for repainting.
	 */
	private Timer timer;
	
	/**
	 * List of cars
	 */
	private ListCar carsList;
	
	/**
	 * List of traffic lights
	 */
	private ListTrafficLight trafficLightsList;
	
	/**
	 * A street in the system
	 */
	private Road street;
	
	/**
	 * A semaphore.
	 */
	private final Semaphore sem = new Semaphore( 1, false); 
	
	private Integer width;
	
	private Integer height;
	
	public Semaphore getSem() {
		return sem;
	}

	/**
	 * Default constructor. Create the graphic interface automatically, add cars and traffic lights and launch the system.
	 */
	public RoadAreaGUI() {
		
		width = 1020;
		height = 100;
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		RepaintManager currentManager = RepaintManager.currentManager(this);
		currentManager.setDoubleBufferingEnabled(true);
		
		carsList = new ListCar();
		street = new Road(new Coordinate(10f, 30f), 65, 1000, 25);
		
		// 5 cars + 3 traffic lights. No special behavior (such as high approaching rate)
		carsList.add(new Car( 12f, 66f, 0f, 15f ) ); // Add in cl[0]
		carsList.add(new Car( 32f, 66f, 5f, 15f ) ); // Add in cl[1]
		carsList.add(new Car( 52f, 66f, 3f, 15f ) ); // Add in cl[2]
		carsList.add(new Car( 84f, 66f, 8f, 15f ) ); // Add in cl[3]
		carsList.add(new Car( 100f, 66f, 5f, 15f ) ); // Add in cl[4]


		trafficLightsList = new ListTrafficLight();
		trafficLightsList.add( new TrafficLight( new Coordinate( 300f, 80f), carsList, sem ) );
		trafficLightsList.add( new TrafficLight( new Coordinate( 600f, 80f), carsList, sem ) );
		trafficLightsList.add( new TrafficLight( new Coordinate( 800f, 80f), carsList, sem ) );
		
		street.setCarList(carsList);
		
		this.timer = new Timer( 50, this );
		timer.setInitialDelay(0);
		timer.start();

	}
	
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	/**
	 * Automatically called by a Timer. Updates the cars's velocities, release semaphores then repaint the GUI.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		//System.out.println("Vitesse avant : " + cl.get(0).getVelocity() + " || Position avant (x) : " + cl.get(0).getPosition().getX());
			
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("Update velocity and position - Debut");
		
		IDM.updateCarsVelocity( this.carsList );
		IDM.updateCarsPosition( this.carsList );
		
		//System.out.println("Update velocity and position - FIN");
		
		sem.release();
		
		//System.out.println("Vitesse apres : " + cl.get(0).getVelocity() + " || Position après (x) : " + cl.get(0).getPosition().getX() );
		
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		street.getImage().paint(g);
		
		for (Car car : carsList) {
			car.getImage().paint(g);
		}
	}
}
