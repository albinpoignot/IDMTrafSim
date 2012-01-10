package trafsim.trafsim;

import trafsim.gui.CarGUI;

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
		
		/*
		 * To understand the following lines, please take a look on the IDM.
		 * 
		 * vpoint = a * { 1 - (v/v0)^delta - (([s_star(v, Delta v_alpha)] / [s_alpha]) ^2 }
		 * 
		 * Here :
		 * 	term1 = (v/v0)^delta
		 * 	delta_valpha = term2 = Delta v_alpha = v_alpha - v_(alpha-1)	
		 *  term3 = (v * term2) / square_root(a * b) = (v * delta_valpha) / square_root(a * b) 
		 * 	s_star = term4 = [s_star(v, Delta v_alpha)] = s0 + v_alpha * T + ( [v * Delta v_alpha] / [square_root(a * b)] )
		 *      = s0 + v_alpha * T + term3
		 * 	s_alpha = term5 = x_(alpha-1) - x_alpha - l_(alpha-1)
		 * 	term6 = (term4 / term5)^2 =  (s_star / s_alpha) ^ 2
		 * 	
		 *  vpoint = acceleration * { 1 - term1 - term6 }
		 */
		
		Float vpoint;
		Float term1, term3, term6;
		Float delta_valpha, s_star, s_alpha;
		
		for (Car car : carList) {
			
			if( car.getDesiredVelocity() != 0 )
			{
				
				// Calculate the new velocity of the current car
				
				term1 = (float) Math.pow(car.getVelocity() / car.getDesiredVelocity(), delta);
	
				if( carList.lastIndexOf(car) != (carList.size()-1)  ) // General formula
				{
					//System.out.println("velo " + carList.getNext(car).getVelocity() + "(index : " + carList.lastIndexOf(car) + ")" );
					
					delta_valpha = car.getVelocity() - carList.getNext(car).getVelocity() ;
					
					term3 = (float) ((car.getVelocity() * delta_valpha) / ( 2 * Math.sqrt(IDM.acceleration * IDM.brakingDeceleration)));
					s_star = IDM.minimumSpacing + car.getVelocity() * IDM.timeHeadway + term3;
					
					s_alpha = (float) carList.getNext(car).getPosition().getX() - car.getPosition().getX() - CarGUI.getLENGTH();
					
					term6 = (float) Math.pow(s_star / s_alpha, 2);
					
					vpoint = IDM.acceleration * ( 1 - term1 - term6 ) + car.getVelocity();
					
					if( vpoint < 0 )
					{
						//System.out.println("Voiture nouvelle vitesse " + vpoint + " " + term4 + " " + term5 );
					}
				}
				else // Free road behavior 1 car or first car 
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
			
			//System.out.println("   1--> " + car.getPosition().getX() );
			
			Coordinate nPos = new Coordinate( car.getPosition().getX() + (car.getVelocity() / 20), car.getPosition().getY());
			
			car.setPosition(nPos);
			
			//System.out.println("   2--> " + car.getPosition().getX() );
		}
	}
}
