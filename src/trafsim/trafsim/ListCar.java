package trafsim;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Manage an ordered list of Cars. Actually, do same kind of things that
 * ArrayList<Car> but allow to redefine some methods if needed. 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
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
	
}
