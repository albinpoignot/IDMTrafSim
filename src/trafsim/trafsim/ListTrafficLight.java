/**
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */

package trafsim.trafsim;

import java.util.ArrayList;

/**
 * Manage an ordered list of TrafficLights. Actually, do same kind of things that
 * ArrayList<TrafficLight> but allow to redefine some methods if needed. 
 *
 */
public class ListTrafficLight extends ArrayList<TrafficLight> {

	/**
	 * Automatically added for serializable methods (not used here)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public ListTrafficLight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param initialCapacity
	 */
	public ListTrafficLight(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns the TrafficLight in front of the TrafficLight given
	 * @param theTrafficLight The TrafficLight you want to know the next
	 * @return The TrafficLight in front of "theTrafficLight" on the road
	 * @deprecated
	 */
	public TrafficLight getNext(TrafficLight theTrafficLight) {

		Integer index = this.indexOf(theTrafficLight);
		
		if(index < this.size())
		{
			return this.get(index + 1);
		}
		else
		{
			return null;
		}
		//return this.get(this.indexOf(theTrafficLight));
	}
	
	/**
	 * Do nothing
	 * @deprecated
	 */
	public void paintAllTrafficLights() {
		
		/*for (TrafficLight TrafficLight : this) {
			//TrafficLight.paint();
		}*/
		
	}
}

