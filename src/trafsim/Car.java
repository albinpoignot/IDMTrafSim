/**
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */

package trafsim;

/**
 * Class representing a Car in the system
 * 
 */
public class Car {
	
	/**
	 * Desired velocity for all cars.
	 */
	private static Integer desiredVelocity;
	
	/**
	 * Minimum spacing between all cars.
	 */
	private static Integer minimumSpacing;
	
	/**
	 * Time headway between all cars.
	 */
	private static Integer timeHeadway;
	
	/**
	 * Acceleration factor of all cars.
	 */
	private static Integer acceleration;
	
	/**
	 * Comfortable braking deceleration factor for all cars.
	 */
	private static Integer brakingDeceleration;
	
	/**
	 * Id of the car. It can not be changed.
	 */
	private Integer identifier;
	
	/**
	 * Position of the car.
	 */
	private Integer position;
	
	/**
	 * Velocity of the car.
	 */
	private Integer velocity;
	
	
	// TODO : Add the graphic representation of the Car
	
	
	/**
	 * @return the desiredVelocity
	 */
	public static Integer getDesiredVelocity() {
		return desiredVelocity;
	}

	/**
	 * @param desiredVelocity the desiredVelocity to set
	 */
	public static void setDesiredVelocity(Integer desiredVelocity) {
		Car.desiredVelocity = desiredVelocity;
	}

	/**
	 * @return the minimumSpacing
	 */
	public static Integer getMinimumSpacing() {
		return minimumSpacing;
	}

	/**
	 * @param minimumSpacing the minimumSpacing to set
	 */
	public static void setMinimumSpacing(Integer minimumSpacing) {
		Car.minimumSpacing = minimumSpacing;
	}

	/**
	 * @return the timeHeadway
	 */
	public static Integer getTimeHeadway() {
		return timeHeadway;
	}

	/**
	 * @param timeHeadway the timeHeadway to set
	 */
	public static void setTimeHeadway(Integer timeHeadway) {
		Car.timeHeadway = timeHeadway;
	}

	/**
	 * @return the acceleration
	 */
	public static Integer getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public static void setAcceleration(Integer acceleration) {
		Car.acceleration = acceleration;
	}

	/**
	 * @return the brakingDeceleration
	 */
	public static Integer getBrakingDeceleration() {
		return brakingDeceleration;
	}

	/**
	 * @param brakingDeceleration the brakingDeceleration to set
	 */
	public static void setBrakingDeceleration(Integer brakingDeceleration) {
		Car.brakingDeceleration = brakingDeceleration;
	}

	/**
	 * @return the identifier
	 */
	public Integer getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * @return the velocity
	 */
	public Integer getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity the velocity to set
	 */
	public void setVelocity(Integer velocity) {
		this.velocity = velocity;
	}


	/**
	 * Constructor
	 * 
	 */
	public Car() {
		position = 0;
		velocity = 0;
	}

}