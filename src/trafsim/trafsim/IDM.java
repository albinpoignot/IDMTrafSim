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
	//private static Float desiredVelocity = new Float(36);
	/**
	 * Minimum spacing between all cars.
	 */
	private static Float minimumSpacing = new Float(1);
	/**
	 * Time headway between all cars.
	 */
	private static Float timeHeadway = new Float(1.5);
	/**
	 * Acceleration factor of all cars.
	 */
	private static Float acceleration = new Float(0.3);
	/**
	 * Comfortable braking deceleration factor for all cars.
	 */
	private static Float brakingDeceleration = new Float(3.0);
	
	/**
	 * Independant factor
	 */
	private static Integer delta = 4;
	
	/**
	 * Update the velocity of each car in the car list
	 * 
	 * @param carList The ListCar of the Car that should be updated
	 */
	public static void updateCarsVelocity( ListCar carList ) {
		
		// TODO In term5 : set a correct value for the car length (15 for now because no CarGUI class exists)
		
		/*
		 * To understand the following lines, please take a look on the IDM.
		 * 
		 * vpoint = a * { 1 - (v/v0)^delta - (([s_star(v, Delta v_alpha)] / [s_alpha]) ^2 }
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
			
			if( car.getDesiredVelocity() != 0 )
			{
				// Calculate the new velocity of the current car
				
				term1 = (float) Math.pow(car.getVelocity() / car.getDesiredVelocity(), delta);  // Delta et non 2
	
				if( carList.lastIndexOf(car) != (carList.size()-1)  ) // Other case, voir si besoin d'un autre pour high approaching rate
				{
					term2 = car.getVelocity() - carList.getNext(car).getVelocity();
					term3 = (float) (car.getVelocity() * term2 / ( 2 * Math.sqrt(IDM.acceleration * IDM.brakingDeceleration)));
					term4 = IDM.minimumSpacing + car.getVelocity() * IDM.timeHeadway + term3;
					term5 = (float) carList.getNext(car).getPosition().getX() - car.getPosition().getX() - 10;
					term6 = (float) Math.pow(term4 / term5, 2);
					vpoint = IDM.acceleration * ( 1 - term1 - term6 ) + car.getVelocity();
					
					if( carList.lastIndexOf(car) == 1 )
					{
						//System.out.println( term1 + " - " + term2 + " - " + term3 + " - " + term4 + " - " + term5 + " - " + term6 );
					}
					
					//System.out.println( "Vitesse apres : " + car.getVelocity() + " . " + carList.getNext(car).getPosition().getX() + "." + car.getPosition().getX()  );
				}
				else // Free road Behaviour 1 car or first car 
				{
					vpoint = IDM.acceleration * ( 1 - term1 ) + car.getVelocity();
				}
				
				// Finally, update the velocity of the current car with the found value 
				car.setVelocity(vpoint);
			}
		}
	}
	
	/**
	 * Update the position of each car in the car list
	 * 
	 * @param carList The ListCar of the Car that should be updated
	 */
	public static void updateCarsPosition( ListCar carList ) 
	{
		for (Car car : carList) 
		{
			//System.out.println("  Velocity : " + car.getVelocity() + " || rounded value : " + Math.round(car.getPosition().getX() + car.getVelocity()));
			
			// TODO Remove the debug line...
			//System.out.println("   1--> " + car.getPosition().getX() );
			Coordinate nPos = new Coordinate( car.getPosition().getX() + (car.getVelocity() / 20), car.getPosition().getY()); // Debug
			//Coordinate nPos = new Coordinate(car.getPosition().getX() + car.getVelocity(), car.getPosition().getY()); // Prod
			car.setPosition(nPos);
			//System.out.println("   2--> " + car.getPosition().getX() );
		}
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
