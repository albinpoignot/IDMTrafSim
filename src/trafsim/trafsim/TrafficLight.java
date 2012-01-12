package trafsim.trafsim;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.Timer;

import trafsim.gui.CarGUI;

/**
 * Representation of a traffic light in the system
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */
public class TrafficLight extends Car implements ActionListener
{
	/**
	 * List Car
	 */
	private ListCar carList;
	
	/**
	 * Duration of the green light
	 */
	private static Integer greenGap = 10000;
	
	/**
	 * Duration of the red light
	 */
	private static Integer redGap = 30000;
	//private static Integer redGap = 5000;
	
	/**
	 * Duration of switching between green and red
	 */
	private static Integer switchGap = 2000;
	
	/**
	 * State of the traffic light. 0 = updated, 1 = red, 2 = orange, 3 = green.
	 */
	private Integer state;
	
	/**
	 * Waiting timer when traffic light is red
	 */
	private Timer timerRed;
	
	/**
	 * Waiting timer when traffic light is green
	 */
	private Timer timerGreen;
	
	/**
	 * Waiting timer for the switch from Green to Red
	 */
	private Timer timerSwitch;
	
	/**
	 * Semaphore
	 */
	//private final Semaphore sem;
	
	/**
	 * Overload constructor. Set the position, the carList and the semaphore attributes. Velocity and DesiredVelocity herited attributes are forced
	 * to value 0. Then automatically set the Timers to default values.
	 * @param position The position of traffic lights
	 */
	//public TrafficLight(Coordinate position, ListCar cl, Semaphore sem ) 
	public TrafficLight(Coordinate position)
	{	
		this.setPosition(position);
		this.setVelocity(0f);
		this.setDesiredVelocity(0f);
		
		this.setImage(new CarGUI(position, 20, 20));
		this.getImage().setColor(Color.RED);
		
		this.timerRed = new Timer( redGap, this );
		this.timerRed.setInitialDelay( redGap );
		this.timerRed.start();
		state = 1;
		
		this.timerGreen = new Timer( greenGap, this );
		this.timerGreen.setInitialDelay(greenGap);
		
		this.timerSwitch = new Timer( switchGap, this );
		this.timerSwitch.setInitialDelay(switchGap);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if( arg0.getSource() == this.timerRed )
		{
			// Green
			timerRed.stop();
			timerGreen.start();	
			state = 3;
		}
		else if( arg0.getSource() == this.timerSwitch )
		{
			// Red
			timerSwitch.stop();
			timerRed.start();
			state = 1;
		}
		else if(arg0.getSource() == this.timerGreen )
		{
			// Orange
			timerGreen.stop();
			state = 2;
			timerSwitch.start();
		}
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * Inserts the TrafficLight to the correct position in its carList property
	 * @throws Exception Throws an exception when the traffic light can't be inserted in the carList
	 */
	/*private void insertTrafficLight() throws Exception 
	{
		sem.acquire(); 
		int index = 0;
		
		for( int i = 0; i < carList.size(); i ++ )
		{
			if( carList.get(i).getPosition().getX() < this.getPosition().getX() )
			{
				index++;
			}
		}
		carList.add( index, this );
		
		//System.out.println("Ajout en position " + index);
		
		sem.release();
	}*/
	
	/**
	 * Removes the TrafficLight from its carList property
	 * @throws Exception Throws an exception when the traffic light can't be removed from the carList
	 */
	/*private void removeTrafficLight() throws Exception
	{
		this.sem.acquire();
		
		carList.remove(this);
		
		this.sem.release();
	}*/

	/**
	 * Automatically called by a Timer. Insert or remove the TrafficLight in its carList when it's time to do it.<br />
	 * <i>Process : wait redGap => become green => wait greenGap => wait switchGap => become red => start over</i>
	 */
	/*@Override
	public void actionPerformed(ActionEvent arg0)  // TODO Changer graphisme
	{
		if( arg0.getSource() == this.timerRed )
		{
			timerRed.stop();
			timerGreen.start();
			try {
				removeTrafficLight();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if( arg0.getSource() == this.timerSwitch )
		{
			timerSwitch.stop();
			timerRed.start();
		}
		else if(arg0.getSource() == this.timerGreen )
		{
			timerGreen.stop();
			try {
				insertTrafficLight();
			} catch (Exception e) {
				e.printStackTrace();
			}
			timerSwitch.start();
		}
	}*/
	
	public void setUpdated(Boolean b) {
		if(b) {
			state = 0;
		}
	}
	
}
