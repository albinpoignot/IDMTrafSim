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
	private Integer x;
	/**
	 *  The position on the Y-Axis
	 */
	private Integer y;
	
	/**
	 * @return x the current position on the X-axis
	 */
	public Integer getX() {
		return x;
	}

	/**
	 * @param x : the new value for the x
	 */
	public void setX(Integer x) {
		this.x = x;
	}

	/**
	 * @return the current y value
	 */
	public Integer getY() {
		return y;
	}

	/**
	 * @param y : the new value for y
	 */
	public void setY(Integer y) {
		this.y = y;
	}

	
	/**
	 * Default constructor set x and y to 0.0
	 */
	public Coordinate()
	{
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructor overload, to set x and y to specified values
	 * @param x : the position on the X_axis
	 * @param y : the position on the Y-axis
	 */
	public Coordinate(Integer x, Integer y)
	{
		this.x = x;
		this.y = y;
	}
}
