/** 
 * Copyright 2012 Albin Poignot & Julien Teruel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * ---------------------------------------------
 *
 * Class Controller
 *
 * @author Albin Poignot, Julien Teruel
 * @version 1.0
 *
 */

package trafsim.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import trafsim.gui.RoadAreaGUI;
import trafsim.trafsim.Car;
import trafsim.trafsim.Coordinate;
import trafsim.trafsim.IDM;
import trafsim.trafsim.ListCar;
import trafsim.trafsim.ListTrafficLight;
import trafsim.trafsim.TrafficLight;

/**
 * Manage the relationship between GUI and mathematics model.
 *
 */
public class Controller implements ActionListener {

	/**
	 * The RoadAreaGUI instance associated to this Controller
	 */
	private RoadAreaGUI roadAreaGui;
	
	/**
	 * Timer to manage the RoadArea
	 */
	private Timer timer;
	
	/**
	 * Timer to add Car
	 */
	private Timer timerAddCar;
	
	/**
	 * A traffic lights list
	 */
	private ListTrafficLight trafficLightsList;
	
	/**
	 * Overload Constructor. Initialize the timer then create a RoadAreaGUI. The timer is set to 
	 * make a top every 50 milliseconds and the initial delay is 0. It also sets a Timer which add a new
	 * every 2 seconds.
	 */
	public Controller(IDM model) {
		timer = new Timer( 50, this );
		timer.setInitialDelay(0);
		
		timerAddCar = new Timer( 7000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//roadAreaGui.getRoad().addCar(new Car( 12f, 66f, 0f, roadAreaGui.getModel().getDesiredVelocity() ));
				/*Double delay = new Double(expLaw(1) * 20000);
				System.out.println("New delay : " + delay.intValue());
				timerAddCar.setDelay(delay.intValue());
				timerAddCar.restart();*/
			}
		} );
		timerAddCar.setInitialDelay(0);

		roadAreaGui = new RoadAreaGUI(model);
		trafficLightsList = new ListTrafficLight();	
	}
	
	/**
	 * @param model The model to set
	 */
	public void setModel(IDM model) {
		roadAreaGui.setModel(model);
	}
	
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
	 * Update the velocity of all cars then update their position. It also manage traffic lights. This function should
	 * not be called manually.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {	
		try {
			roadAreaGui.getSem().acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		roadAreaGui.updateRoad();
		
		roadAreaGui.getSem().release();
		
		for (TrafficLight tl : trafficLightsList) {
			
			if(tl.getState() == TrafficLight.RED)
			{
				try {
					insertTrafficLight(tl);
					tl.setUpdated(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(tl.getState() == TrafficLight.GREEN)
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
	
	/**
	 * Add cars in the road of the RoadAreaGUI of this instance
	 */
	private void addCars() {
		// 5 cars + 3 traffic lights. No special behavior (such as high approaching rate)
		//roadAreaGui.getRoad().addCar(new Car( 12f, 66f, 0f, roadAreaGui.getModel().getDesiredVelocity() ));
		/*roadAreaGui.getRoad().addCar(new Car( 32f, 66f, 5f, roadAreaGui.getModel().getDesiredVelocity() ));
		roadAreaGui.getRoad().addCar(new Car( 52f, 66f, 3f, roadAreaGui.getModel().getDesiredVelocity() ));
		roadAreaGui.getRoad().addCar(new Car( 84f, 66f, 8f, roadAreaGui.getModel().getDesiredVelocity() ));
		roadAreaGui.getRoad().addCar(new Car( 100f, 66f, 5f, roadAreaGui.getModel().getDesiredVelocity() ));*/
	}
	
	/**
	 * Add a Car in a system
	 */
	public void addCar() {
		roadAreaGui.getRoad().addCar(0, new Car( 12f, 66f, 0f, roadAreaGui.getModel().getDesiredVelocity() ));
	}
	
	/**
	 * Add traffic lights in the road of the RoadAreaGUI of this instance
	 */
	public void addTrafficLights() {
		trafficLightsList.add( new TrafficLight( new Coordinate(300f, 80f) ) );
		trafficLightsList.add( new TrafficLight( new Coordinate(600f, 80f) ) );
		trafficLightsList.add( new TrafficLight( new Coordinate(800f, 80f) ) );
	}
	
	/**
	 * Returns a random number from a Poisson law having a exponential distribution 
	 * between 0.0 and 1.0 
	 * @param lambda Parameter of the distribution
	 * @return a random number from an exponential distribution
	 */
	private double expLaw(double lambda) { 
		Random rand = new Random(); 
		Double db = rand.nextDouble();
		return Math.log(1 - db) / (-lambda);
		//return - (1 / lambda) * Math.log( 1 - rand.nextDouble() );*/
	}
	
	/**
	 * Inserts the TrafficLight to the correct position in its carList property
	 * @throws Exception Throws an exception when the traffic light can't be inserted in the carList
	 */
	private void insertTrafficLight(TrafficLight tl) throws Exception {
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
		
		roadAreaGui.getSem().release();
	}
	
	/**
	 * Pause the simulation by stopping the timer. All configurations and cars are kept.
	 */
	public void pauseSimulation() {
		timer.stop();
	}
	
	/**
	 * Remove the cars in the road of the RoadAreaGUI of this instance
	 */
	public void removeCars() {
		roadAreaGui.getRoad().setCarList(new ListCar());
	}
	
	/**
	 * Remove all the traffic lights in the road of the RoadAreaGUI of this instance
	 */
	public void removeTrafficLights() {
		trafficLightsList = new ListTrafficLight();
	}
	
	/**
	 * Removes the TrafficLight from the carList
	 * @throws Exception Throws an exception when the traffic light can't be removed 
	 * from the carList
	 */
	private void removeTrafficLight(TrafficLight tl) throws Exception {
		roadAreaGui.getSem().acquire();
		roadAreaGui.getRoad().removeCar(tl);
		roadAreaGui.getSem().release();
	}
	
	/**
	 * Repaint the road area
	 */
	public void repaint() {
		roadAreaGui.repaint();
	}
	
	/**
	 * Resume the simulation by starting over the timer. The configuration is the same than
	 * before the pause.
	 */
	public void resumeSimulation() {
		timer.start();
	}
	
	/**
	 * Start the simulation. Add cars, add traffic lights then starts the timer.
	 */
	public void startSimulation() {
		addCars();
		addTrafficLights();
		timer.start();
		timerAddCar.start();
	}
	
	/**
	 * Stop the simulation. Stop the timer, remove all cars and all traffic lights then repaint the road area.
	 */
	public void stopSimulation() {
		timer.stop();
		timerAddCar.stop();
		removeCars();
		removeTrafficLights();
		roadAreaGui.repaint();
	}
	
	/**
	 * Switch the state of all traffic lights from GREEN to RED, or from RED to GREEN
	 */
	public void switchTrafficLightsState() { 
		for (TrafficLight tl : trafficLightsList) {
			tl.switchState();
		}
	}

}
