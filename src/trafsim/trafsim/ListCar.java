/**
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */

package trafsim.trafsim;

import java.util.ArrayList;

/**
 * Manage an ordered list of Cars. Actually, do same kind of things that
 * ArrayList<Car> but allow to redefine some methods if needed. 
 *
 */
public class ListCar extends ArrayList<Car> {

	/**
	 * Automatically added for serializable methods (not used here)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public ListCar() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param initialCapacity
	 */
	public ListCar(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Return the car in front of the car given
	 * @param theCar The car you want to know the next
	 * @return The Car in front of "theCar" on the road
	 */
	public Car getNext(Car theCar) {
		/*
		 * LONG VERSION ---
		 * Integer index = this.indexOf(theCar);
		 * Car inFront = this.get(index - 1);
		 * return inFront;  
		 */
		
		return this.get(this.indexOf(theCar));
	}
	
}
