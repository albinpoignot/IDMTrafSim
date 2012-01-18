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
 * Class RoadAreaGUI
 *
 * @author Albin Poignot, Julien Teruel
 * @version 1.0
 *
 */

package trafsim.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.Semaphore;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import trafsim.trafsim.Car;
import trafsim.trafsim.Coordinate;
import trafsim.trafsim.IDM;
import trafsim.trafsim.Road;

/**
 * Manage the draw of a road area. For now, there is only one road in
 * a road area.
 */
public class RoadAreaGUI extends JPanel {

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
