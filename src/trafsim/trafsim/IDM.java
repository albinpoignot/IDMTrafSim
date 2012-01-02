package trafsim.trafsim;

/**
 * Representation of the Intelligent Driver Model in the system
 * 
 * @see <a href="http://en.wikipedia.org/wiki/Intelligent_driver_model">Wikipedia link for IDM</a>
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */
public class IDM 
{
	/**
	 * Desired velocity for all cars.
	 */
	private static Float desiredVelocity ;
	/**
	 * Minimum spacing between all cars.
	 */
	private static Float minimumSpacing;
	/**
	 * Time headway between all cars.
	 */
	private static Float timeHeadway;
	/**
	 * Acceleration factor of all cars.
	 */
	private static Float acceleration;
	/**
	 * Comfortable braking deceleration factor for all cars.
	 */
	private static Float brakingDeceleration;
	
	/**
	 * Independant factor
	 */
	private static Integer delta = 4;
	
	/**
	 * Update the velocity of each car in the car list
	 * 
	 * @param carList The ListCar of the Car that should be updated
	 */
	private static void updateCarsVelocity( ListCar carList ) {
		
		// TODO In term5 : set a correct value for the car lenght (15 for now because no CarGUI class exists)
		
		/*
		 * To understand the following lines, please take a look on the IDM.
		 * 
		 * vpoint = a * { 1 - (v/v0)^delta - ([s_star(v, Delta v_alpha)] / [s_alpha] ^2 }
		 * 
		 * Here :
		 * 	term1 = (v/v0)^delta
		 * 	term2 = Delta v_alpha = v_alpha - v_(alpha-1)	
		 *  term3 = v * term2 / square_root(a * b)
		 * 	term4 = [s_star(v, Delta v_alpha)] = s0 + v_alpha * T + ( [v * Delta v_alpha] / [square_root(a * b)] )
		 * 	term5 = x_(alpha-1) - x_alpha - l_(alpha-1)
		 * 	term6 = (term4 / term5)^2
		 * 	
		 *  vpoint = acceleration * { 1 - term1 - term6 }
		 */
		
		Float vpoint;
		Float term1, term2, term3, term4, term5, term6;
		
		for (Car car : carList) {
			
			// Calculate the new velocity of the current car
			
			term1 = (float) Math.pow(car.getVelocity() * IDM.desiredVelocity, 2);
			term2 = car.getVelocity() - carList.getNext(car).getVelocity();
			term3 = (float) (car.getVelocity() * term2 / Math.sqrt(IDM.acceleration * IDM.brakingDeceleration));
			term4 = IDM.minimumSpacing + car.getVelocity() * IDM.timeHeadway + term3;
			term5 = (float) (carList.getNext(car).getPosition().getX() - car.getPosition().getX() - 15);
			term6 = (float) Math.pow(term4 / term5, 2);
			vpoint = IDM.acceleration * ( 1 - term1 - term6 );
			
			// Finally, update the velocity of the current car with the found value 
			car.setVelocity(vpoint);

		}
	}
	
	/**
	 * Update the position of each car in the car list
	 * 
	 * @param carList The ListCar of the Car that should be updated
	 */
	private static void updateCarsPosition( ListCar carList ) {
		
	}
	
	/**
	 * NOT IMPLEMTED YET
	 */
	private static void updateSystem() {
		
		// TODO Find correct arguments, and what to do here...
		
		/* 		1. For each road :
		 *			1.1 IDM.updateCars(road)
		 *		...
		 */
	}
}
