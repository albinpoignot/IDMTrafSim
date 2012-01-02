package trafsim.trafsim;

/**
 * Representation of 2D coordinate in the system
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
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

	
	/**
	 * Default constructor set x and y to 0.0
	 */
	Coordinate()
	{
		this.x = (float) 0.0;
		this.y = (float)0.0;
	}
	
	/**
	 *  Constructor overload, to set x and y to specified values
	 * @param x : the position on the X_axis
	 * @param y : the position on the Y-axis
	 */
	Coordinate(Float x, Float y)
	{
		this.x = x;
		this.y = y;
	}
}
