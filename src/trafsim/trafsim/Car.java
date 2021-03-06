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
 * Class Car
 *
 * @author Albin Poignot, Julien Teruel
 * @version 1.0
 *
 */

package trafsim.trafsim;

import trafsim.gui.CarGUI;


/**
 * Representation of a car in the system
 */

public class Car
{	
	/**
	 * Graphical representation of the car
	 */
	private CarGUI carGui;
	
	/**
	 * Desired velocity for all cars.
	 */
	private Float desiredVelocity;

	/**
	 * Car's unique ID
	 */
	private Integer identifier;
	
	/**
	 * Car's current velocity
	 */
	private Float velocity;
	
	/**
	 * Car's current 2D coordinate
	 */
	private Coordinate position;	
	
	/**
	 * Default constructor. Set a default position, an initial velocity of 0, a new 
	 * CarGUI and to finish a desired velocity of 0
	 */
	public Car() {
		position = new Coordinate();
		velocity = 0f;
		carGui = new CarGUI(position, CarGUI.getLENGTH(), CarGUI.getLENGTH());
		desiredVelocity = 0f;
	}
	
	/**
	 * Overload constructor to initialize attributes
	 * @param x The position of the car on the x axis
	 * @param y The position of the car on the y axis
	 * @param velocity The initial velocity
	 * @param desiredVelocity Desired velocity
	 */
	public Car( Float x, Float y, Float velocity, Float desiredVelocity ) {
		position = new Coordinate( x, y );
		this.velocity = velocity;
		this.desiredVelocity = desiredVelocity;
		carGui = new CarGUI(position, 20, 20);
	}
	
	/**
	 * @return the desiredVelocity
	 */
	public Float getDesiredVelocity() {
		return desiredVelocity;
	}

	/**
	 * @param desiredVelocity the desiredVelocity to set
	 */
	public void setDesiredVelocity(Float desiredVelocity) {
		this.desiredVelocity = desiredVelocity;
	}
	
	/**
	 * @return the identifier
	 */
	public Integer getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * @return the image
	 */
	public CarGUI getImage() {
		return carGui;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(CarGUI image) {
		this.carGui = image;
	}

	/**
	 * @return the position
	 */
	public Coordinate getPosition() {
		return position;
	}

	/**
	 * @param position the position to set. Also set the position of the CarGUI instance.
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
		this.carGui.setPosition(position);
	}

	/**
	 * @return the velocity
	 */
	public Float getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(Float velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Repaint the CarGUI associated instance
	 */
	public void paint() {
		carGui.repaint();
	}
	
}
