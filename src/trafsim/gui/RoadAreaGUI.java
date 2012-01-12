/**
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
 * Manage the draw of a road area
 *
 */
public class RoadAreaGUI extends JPanel implements ActionListener {

	/**
	 * Used in case of serialization. Useless here.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of cars
	 */
	//private ListCar carsList;
	
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
	 * List of traffic lights
	 */
	private ListTrafficLight trafficLightsList;
	
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
		
		//RepaintManager currentManager = RepaintManager.currentManager(this);
		//currentManager.setDoubleBufferingEnabled(true);
		
		//carsList = new ListCar();
		road = new Road(new Coordinate(10f, 30f), 65, 1000, 25, model);
		trafficLightsList = new ListTrafficLight();
		
		//addCars();
		
		//road.setCarList(new ListCar());
		
		/*this.timer = new Timer( 50, this );
		timer.setInitialDelay(0);*/
		//timer.start();

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
	
	public void setRoadModel(IDM model) {
		road.setModel(model);
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
			e.printStackTrace();
		}
		
		//System.out.println("Update velocity and position - Debut");
		
		/*model.updateCarsVelocity( this.carsList );
		model.updateCarsPosition( this.carsList );*/
		/*model.updateCarsVelocity( road.getCarList() );
		model.updateCarsPosition( road.getCarList() );*/
		
		//System.out.println("Update velocity and position - FIN");
		
		sem.release();
		
		//System.out.println("Vitesse apres : " + cl.get(0).getVelocity() + " || Position après (x) : " + cl.get(0).getPosition().getX() );
		
		this.repaint();
	}
	
	/**
	 * Custom painting of the RoadAreaGUI : draw the road and all the cars
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		road.getImage().paint(g);
		
		for (Car car : road.getCarList()) {
			car.getImage().paint(g);
		}
	}

	/*public void startSimulation() {
		addCars();
		timer.start();
	}
	
	public void stopSimulation() {
		timer.stop();
		removeCars();
		this.repaint();
	}*/
	
	
	
	public void addCars() {
		// 5 cars + 3 traffic lights. No special behavior (such as high approaching rate)
		/*carsList.add(new Car( 12f, 66f, 0f, model.getDesiredVelocity() ) ); // Add in cl[0]
		carsList.add(new Car( 32f, 66f, 5f, model.getDesiredVelocity() ) ); // Add in cl[1]
		carsList.add(new Car( 52f, 66f, 3f, model.getDesiredVelocity() ) ); // Add in cl[2]
		carsList.add(new Car( 84f, 66f, 8f, model.getDesiredVelocity() ) ); // Add in cl[3]
		carsList.add(new Car( 100f, 66f, 5f, model.getDesiredVelocity() ) ); // Add in cl[4]

		trafficLightsList.add( new TrafficLight( new Coordinate( 300f, 80f), carsList, sem ) );
		trafficLightsList.add( new TrafficLight( new Coordinate( 600f, 80f), carsList, sem ) );
		trafficLightsList.add( new TrafficLight( new Coordinate( 800f, 80f), carsList, sem ) );*/
	}
	
	public void removeCars() {
		/*carsList = new ListCar();
		trafficLightsList = new ListTrafficLight();*/
	}
	
	public void updateRoad() {
		road.updateCars();
	}
}
