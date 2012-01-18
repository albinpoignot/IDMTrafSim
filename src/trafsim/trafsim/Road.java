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
 * Class Road
 *
 * @author Albin Poignot, Julien Teruel
 * @version 1.0
 *
 */

package trafsim.trafsim;

import trafsim.gui.RoadGUI;

/**
 * Representation of a road segment in the system
 */
public class Road 
{	
	/**
	 * List of the car on the road
	 */
	private ListCar carList;
	
	/**
	 * Height of this road segment
	 */
	private Integer height ;
	
	/**
	 * Graphical representation of the road segment
	 */
	private RoadGUI image;
	
	/**
	 * Model of circulation in this road
	 */
	private IDM model;
	
	/**
	 * Start position of this road segment
	 */
	private Coordinate position;
	
	/**
	 * Speed limit on this road segment
	 */
	private Integer velocityLimit;
	
	/**
	 * Width of this road segment
	 */
	private Integer width;
	
	/**
	 * Overload Constructor to initialize attributes.
	 * @param position Position of the road
	 * @param height Height of the road
	 * @param width Width or the road
	 * @param speedLimit speed limit on the segment
	 */
	public Road( Coordinate position, Integer height, Integer width, Integer speedLimit, IDM model ) {
		this.position = position;
		this.height = height;
		this.width = width;
		this.velocityLimit = speedLimit;
		this.carList = new ListCar();
		this.model = model;
		
		this.image = new RoadGUI(position, height, width);
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
		this.image.setCarList(carList);
	}
	
	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @return the image
	 */
	public RoadGUI getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(RoadGUI image) {
		this.image = image;
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
	 * @return the positionStart
	 */
	public Coordinate getPositionStart() {
		return position;
	}

	/**
	 * @param positionStart the positionStart to set
	 */
	public void setPositionStart(Coordinate positionStart) {
		this.position = positionStart;
	}
	
	/**
	 * @return the velocityLimit
	 */
	public Integer getVelocityLimit() {
		return velocityLimit;
	}

	/**
	 * @param velocityLimit the velocityLimit to set
	 */
	public void setVelocityLimit(Integer velocityLimit) {
		this.velocityLimit = velocityLimit;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	/**
	 * Add a car at the end of the carList attribute
	 * @param car The car to add
	 */
	public void addCar(Car car) {
		carList.add(car);
	}
	
	/**
	 * Add a car at the <i>index</i> position in the carList attribute
	 * @param index The index where add the car
	 * @param car The car to add
	 */
	public void addCar(Integer index, Car car) {
		carList.add(index, car);
	}
	
	/**
	 * Get the number of cars in this Road
	 * @return The number of cars in this road : actually, the size of the carList attribute
	 */
	public Integer getNumberOfCars() {
		return carList.size();
	}
	
	/**
	 * Return the Car in the <i>index</i> position on the road
	 * @param index The position of the wanted Car
	 * @return The wanted Car
	 */
	public Car getCarById(Integer index) {
		return carList.get(index);
	}
	
	/**
	 * Remove a car of the carList attribute
	 * @param car The car to remove
	 */
	public void removeCar(Car car) {
		carList.remove(car);
	}
	
	/**
	 * Update the list of car in this road : change their velocity and their position
	 * using the <code>model</code> and delete cars which aren't in the road anymore.
	 */
	public void updateCars() {
		java.util.Iterator<Car> itr = carList.iterator();
		Car car;
		
		while(itr.hasNext()) {
			car = itr.next();
			model.updateCarsVelocity(car, carList);
			model.updateCarPosition(car);
			if(car.getPosition().getX() > this.width) {
				itr.remove();
			}
		}		
	}
}
