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
	
	/**
	 * @deprecated
	 */
	public void paintAllCars() {
		
		for (Car car : this) {
			car.paint();
		}
		
	}
	
}
