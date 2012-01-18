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
 * Class ListCar
 *
 * @author Albin Poignot, Julien Teruel
 * @version 1.0
 *
 */

package trafsim.trafsim;

import java.util.ArrayList;

/**
 * Manage an ordered list of Cars. Actually, do same kind of things that
 * ArrayList<Car> but allow to redefine some methods if needed.
 */
public class ListCar extends ArrayList<Car> {

	/**
	 * Automatically added for serializable methods (not used here)
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Return the car in front of the given car
	 * @param theCar The car you want to know the next
	 * @return The Car in front of "theCar" on the road or null if there is no car in front
	 */
	public Car getNext(Car theCar) {

		Integer index = this.indexOf(theCar);
		
		if(index < this.size())
		{
			return this.get(index + 1);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Display debug informations on the console : <br />
	 * "Car #x, velocity max = y"
	 */
	public void display()
	{
		for( int i = 0; i < this.size(); i++ )
		{
			System.out.println("Car #" + i + ", velocity max = " + this.get(i).getDesiredVelocity() ); 
		}
	}

}
