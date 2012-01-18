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
 * Class IDM
 *
 * @author Albin Poignot, Julien Teruel
 * @version 1.0
 *
 */

package trafsim.trafsim;

import trafsim.gui.CarGUI;

/**
 * Representation of the Intelligent Driver Model in the system
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Intelligent_driver_model">Wikipedia link for IDM</a>
 */
public class IDM 
{
	/**
	 * Minimum spacing between all cars.
	 */
	private Float minimumSpacing = new Float(1);
	
	/**
	 * Time headway between all cars.
	 */
	private Float timeHeadway = new Float(1.5);
	
	/**
	 * Acceleration factor of all cars.
	 */
	private Float acceleration = new Float(0.3);
	
	/**
	 * Comfortable braking deceleration factor for all cars.
	 */
	private Float brakingDeceleration = new Float(3.0);
	
	/**
	 * Independant factor
	 */
	private Integer delta = 4;
	
	/**
	 * The desired velocity for all cars
	 */
	private Float desiredVelocity = 15f;
	
	/**
	 * Default constructor : do nothing. <b>YOU HAVE TO INITIALIZE ALL PROPERTIES MANUALLY</b> 
	 */
	public IDM() {
		// Do nothing
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
	 * @return the minimumSpacing
	 */
	public Float getMinimumSpacing() {
		return minimumSpacing;
	}

	/**
	 * @param minimumSpacing the minimumSpacing to set
	 */
	public void setMinimumSpacing(Float minimumSpacing) {
		this.minimumSpacing = minimumSpacing;
	}

	/**
	 * @return the timeHeadway
	 */
	public Float getTimeHeadway() {
		return timeHeadway;
	}

	/**
	 * @param timeHeadway the timeHeadway to set
	 */
	public void setTimeHeadway(Float timeHeadway) {
		this.timeHeadway = timeHeadway;
	}

	/**
	 * @return the acceleration
	 */
	public Float getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(Float acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * @return the brakingDeceleration
	 */
	public Float getBrakingDeceleration() {
		return brakingDeceleration;
	}

	/**
	 * @param brakingDeceleration the brakingDeceleration to set
	 */
	public void setBrakingDeceleration(Float brakingDeceleration) {
		this.brakingDeceleration = brakingDeceleration;
	}
	
	/**
	 * Update the velocity of the given car in the list. The details of the code is available in comments
	 * inside of the method itself.
	 * @param car The car to update
	 * @param carList The ListCar of the Car that should be updated
	 * @return The updated car
	 */
	public Car updateCarsVelocity(Car car, ListCar carList) {
		
		/*
		 * To understand the following lines, please take a look on the IDM.
		 * 
		 * vpoint = a * { 1 - (v/v0)^delta - (([s_star(v, Delta v_alpha)] / [s_alpha]) ^2 }
		 * 
		 * Here :
		 * 	term1 = (v/v0)^delta
		 * 	delta_valpha = term2 = Delta v_alpha = v_alpha - v_(alpha-1)	
		 *  term3 = (v * term2) / square_root(a * b) = (v * delta_valpha) / square_root(a * b) 
		 * 	s_star = term4 = [s_star(v, Delta v_alpha)] = s0 + v_alpha * T + ( [v * Delta v_alpha] / [square_root(a * b)] )
		 *      = s0 + v_alpha * T + term3
		 * 	s_alpha = term5 = x_(alpha-1) - x_alpha - l_(alpha-1)
		 * 	term6 = (term4 / term5)^2 =  (s_star / s_alpha) ^ 2
		 * 	
		 *  vpoint = acceleration * { 1 - term1 - term6 }
		 */
		
		Float vpoint;
		Float term1, term3, term6;
		Float delta_valpha, s_star, s_alpha;
		
		if( car.getDesiredVelocity() != 0 )
		{
			
			// Calculate the new velocity of the current car
			
			term1 = (float) Math.pow(car.getVelocity() / car.getDesiredVelocity(), delta);

			if( carList.lastIndexOf(car) != (carList.size()-1)  ) // General formula
			{	
				delta_valpha = car.getVelocity() - carList.getNext(car).getVelocity() ;
				
				term3 = (float) ((car.getVelocity() * delta_valpha) / ( 2 * Math.sqrt(this.acceleration * this.brakingDeceleration)));
				s_star = this.minimumSpacing + car.getVelocity() * this.timeHeadway + term3;
				
				s_alpha = (float) carList.getNext(car).getPosition().getX() - car.getPosition().getX() - CarGUI.getLENGTH();
				
				term6 = (float) Math.pow(s_star / s_alpha, 2);
				
				vpoint = this.acceleration * ( 1 - term1 - term6 ) + car.getVelocity();
			}
			else // Free road behavior 1 car or first car 
			{
				vpoint = this.acceleration * ( 1 - term1 ) + car.getVelocity();
			}
			
			// Finally, update the velocity of the current car with the found value 
			car.setVelocity(vpoint);
		}
		
		return car;
	}
	
	/**
	 * Update the position of a car
	 * @param car The Car that should be updated
	 */
	public Car updateCarPosition(Car car) {
		Coordinate nPos = new Coordinate( car.getPosition().getX() + (car.getVelocity() / 20), car.getPosition().getY() );
		car.setPosition(nPos);
		return car;
	}
	
}
