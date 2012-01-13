/**
 * Class Coordinate
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 * 
 */

package trafsim.trafsim;

/**
 * Representation of 2D coordinate in the system
 */
public class Coordinate 
{
	/**
	 *  The position on the X-axis
	 */
	private Float x;
	/**
	 *  The position on the Y-Axis
	 */
	private Float y;
	
	/**
	 * Default constructor. Sets x and y to 0.0
	 */
	public Coordinate()	{
		this.x = 0f;
		this.y = 0f;
	}
	
	/**
	 * Overload constructor to set x and y to specified values
	 * @param x : the position on the X_axis
	 * @param y : the position on the Y_axis
	 */
	public Coordinate(Float x, Float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return x the current position on the X-axis
	 */
	public Float getX() {
		return x;
	}

	/**
	 * @param x : the new value for the x
	 */
	public void setX(Float x) {
		this.x = x;
	}

	/**
	 * @return the current y value
	 */
	public Float getY() {
		return y;
	}

	/**
	 * @param y : the new value for y
	 */
	public void setY(Float y) {
		this.y = y;
	}
}
