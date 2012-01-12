/**
 * description
 *
 * @author
 * @version
 *
 */
package trafsim.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import trafsim.gui.RoadAreaGUI;
import trafsim.trafsim.Car;
import trafsim.trafsim.Coordinate;
import trafsim.trafsim.IDM;
import trafsim.trafsim.ListCar;
import trafsim.trafsim.ListTrafficLight;
import trafsim.trafsim.TrafficLight;

/**
 * description
 *
 */
public class Controller implements ActionListener {

	/**
	 * Timer to manage the RoadArea
	 */
	private Timer timer;
	
	/**
	 * The RoadAreaGUI instance associated to this Controller
	 */
	private RoadAreaGUI roadAreaGui;
	
	/**
	 * @return the roadAreaGui
	 */
	public RoadAreaGUI getRoadAreaGui() {
		return roadAreaGui;
	}

	/**
	 * @param roadAreaGui the roadAreaGui to set
	 */
	public void setRoadAreaGui(RoadAreaGUI roadAreaGui) {
		this.roadAreaGui = roadAreaGui;
	}

	/**
	 * A traffic lights list
	 */
	private ListTrafficLight trafficLightsList;
	
	/**
	 * Default constructor. Initialize the timer then create a RoadAreaGUI. The timer is set to 
	 * make a top every 50 milliseconds and the initial delay is 0.
	 */
	public Controller(IDM model) {
		
		timer = new Timer( 50, this );
		timer.setInitialDelay(0);

		roadAreaGui = new RoadAreaGUI(model);
		trafficLightsList = new ListTrafficLight();
		
	}

	/**
	 * Catch an event from the timer. Update the velocity of all cars then update their position.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			roadAreaGui.getSem().acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*ListCar cl = roadAreaGui.getModel().updateCarsVelocity(roadAreaGui.getRoad().getCarList());
		cl = roadAreaGui.getModel().updateCarsPosition(cl);
		
		roadAreaGui.getRoad().setCarList(cl);*/
	//	System.out.println("Size of carList " + roadAreaGui.getRoad().getNumberOfCars());
		roadAreaGui.updateRoad();
		
		//System.out.println("Update velocity and position - FIN");
		
		roadAreaGui.getSem().release();
		
		//System.out.println("Vitesse apres : " + cl.get(0).getVelocity() + " || Position après (x) : " + cl.get(0).getPosition().getX() );
		
		for (TrafficLight tl : trafficLightsList) {
			
			if(tl.getState() == 1)
			{
				try {
					insertTrafficLight(tl);
					tl.setUpdated(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(tl.getState() == 3)
			{
				try {
					removeTrafficLight(tl);
					tl.setUpdated(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		roadAreaGui.repaint();
	}
	
	public void startSimulation() {
		addCars();
		addTrafficLights();
		timer.start();
	}
	
	public void stopSimulation() {
		timer.stop();
		removeCars();
		removeTrafficLights();
		roadAreaGui.repaint();
	}
	
	public void pauseSimulation() {
		timer.stop();
	}
	
	public void resumeSimulation() {
		timer.start();
	}
	
	
	/**
	 * Add cars in the road of the RoadAreaGUI of this instance
	 */
	public void addCars() {
		// 5 cars + 3 traffic lights. No special behavior (such as high approaching rate)
		roadAreaGui.getRoad().addCar(new Car( 12f, 66f, 0f, roadAreaGui.getModel().getDesiredVelocity() ));
		roadAreaGui.getRoad().addCar(new Car( 32f, 66f, 5f, roadAreaGui.getModel().getDesiredVelocity() ));
		roadAreaGui.getRoad().addCar(new Car( 52f, 66f, 3f, roadAreaGui.getModel().getDesiredVelocity() ));
		roadAreaGui.getRoad().addCar(new Car( 84f, 66f, 8f, roadAreaGui.getModel().getDesiredVelocity() ));
		roadAreaGui.getRoad().addCar(new Car( 100f, 66f, 5f, roadAreaGui.getModel().getDesiredVelocity() ));
		
		/*carsList.add(new Car( 12f, 66f, 0f, model.getDesiredVelocity() ) ); // Add in cl[0]
		carsList.add(new Car( 32f, 66f, 5f, model.getDesiredVelocity() ) ); // Add in cl[1]
		carsList.add(new Car( 52f, 66f, 3f, model.getDesiredVelocity() ) ); // Add in cl[2]
		carsList.add(new Car( 84f, 66f, 8f, model.getDesiredVelocity() ) ); // Add in cl[3]
		carsList.add(new Car( 100f, 66f, 5f, model.getDesiredVelocity() ) ); // Add in cl[4]*/
		
	}
	
	/**
	 * Add traffic lights in the road of the RoadAreaGUI of this instance
	 */
	public void addTrafficLights() {
		/*trafficLightsList.add( new TrafficLight( new Coordinate(300f, 80f) ) );
		trafficLightsList.add( new TrafficLight( new Coordinate( 600f, 80f) ) );
		trafficLightsList.add( new TrafficLight( new Coordinate( 800f, 80f) ) );*/
		
		/*for (TrafficLight tl : trafficLightsList) {
			try {
				insertTrafficLight(tl);
			}
			catch(Exception e) {
				
			}	
		}*/
	}
	
	/**
	 * Remove the cars in the road of the RoadAreaGUI of this instance
	 */
	public void removeCars() {
		roadAreaGui.getRoad().setCarList(new ListCar());
	}
	
	/**
	 * Remove the traffic lights in the road of the RoadAreaGUI of this instance
	 */
	public void removeTrafficLights() {
		trafficLightsList = new ListTrafficLight();
	}
	
	public void setModel(IDM model) {
		roadAreaGui.setModel(model);
	}
	
	
	/**
	 * Inserts the TrafficLight to the correct position in its carList property
	 * @throws Exception Throws an exception when the traffic light can't be inserted in the carList
	 */
	private void insertTrafficLight(TrafficLight tl) throws Exception 
	{
		roadAreaGui.getSem().acquire(); 
		int index = 0;
		
		for( int i = 0; i < roadAreaGui.getRoad().getNumberOfCars(); i ++ )
		{
			if( roadAreaGui.getRoad().getCarById(i).getPosition().getX() < tl.getPosition().getX() )
			{
				index++;
			}
		}
		
		roadAreaGui.getRoad().addCar( index, tl );
		
		//System.out.println("Ajout en position " + index);
		
		roadAreaGui.getSem().release();
	}
	
	/**
	 * Removes the TrafficLight from its carList property
	 * @throws Exception Throws an exception when the traffic light can't be removed from the carList
	 */
	private void removeTrafficLight(TrafficLight tl) throws Exception
	{
		roadAreaGui.getSem().acquire();
		
		roadAreaGui.getRoad().removeCar(tl);
		
		roadAreaGui.getSem().release();
	}
	
	public void repaint() {
		roadAreaGui.repaint();
	}
}
