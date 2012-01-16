/**
 * Class TrafficLight
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */

package trafsim.trafsim;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import trafsim.gui.CarGUI;

/**
 * Representation of a traffic light in the system
 */
public class TrafficLight extends Car implements ActionListener
{	
	/**
	 * Constant for "Updated" state
	 */
	public final static Integer UPDATED = 0;
	
	/**
	 * Constant for "Red" state
	 */
	public final static Integer RED = 1;
	
	/**
	 * Constant for "Orange" state
	 */
	public final static Integer ORANGE = 2;
	
	/**
	 * Constant for "Green" state
	 */
	public final static Integer GREEN = 3;
	
	/**
	 * Duration of the green light
	 */
	private static Integer greenGap = 10000;
	
	/**
	 * Duration of the red light
	 */
	private static Integer redGap = 30000;
	
	/**
	 * Duration of switching between green and red
	 */
	private static Integer switchGap = 2000;
	
	/**
	 * State of the traffic light for the Controller
	 */
	private Integer state;
	
	/**
	 * Real state of the traffic light
	 */
	private Integer realState;
	
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
	 * Overload constructor. Set the position, the carList and the semaphore attributes. Velocity and DesiredVelocity herited attributes are forced
	 * to value 0. Then automatically set the Timers to default values.
	 * @param position The position of traffic lights
	 */
	public TrafficLight(Coordinate position) {	
		this.setPosition(position);
		this.setVelocity(0f);
		this.setDesiredVelocity(0f);
		
		this.setImage(new CarGUI(position, 20, 20));
		this.getImage().setColor(Color.RED);
		
		this.timerRed = new Timer( redGap, this );
		this.timerRed.setInitialDelay( redGap );
		this.timerRed.start();
		state = TrafficLight.RED;
		realState = TrafficLight.RED;
		
		this.timerGreen = new Timer( greenGap, this );
		this.timerGreen.setInitialDelay(greenGap);
		
		this.timerSwitch = new Timer( switchGap, this );
		this.timerSwitch.setInitialDelay(switchGap);
	}

	/**
	 * Automatically called by Timers. It changes the <code>state</code> of the TrafficLight and manage
	 * the start/stop process of all timers.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if( arg0.getSource() == this.timerRed )
		{
			// Green
			timerRed.stop();
			timerGreen.start();	
			state = TrafficLight.GREEN;
			realState = TrafficLight.GREEN;
		}
		else if( arg0.getSource() == this.timerSwitch )
		{
			// Red
			timerSwitch.stop();
			timerRed.start();
			state = TrafficLight.RED;
			realState = TrafficLight.RED;
		}
		else if( arg0.getSource() == this.timerGreen )
		{
			// Orange
			timerGreen.stop();
			state = TrafficLight.ORANGE;
			realState = TrafficLight.ORANGE;
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
	 * Change the <code>state</code> of the TrafficLight to UPDATED's value if the argument is true
	 * @param b true if the trafficLight was updated on the screen, false if not.
	 */
	public void setUpdated(Boolean b) {
		if(b) {
			state = TrafficLight.UPDATED;
		}
	}
	
	/**
	 * Switch the state of the traffic light from RED to GREEN or from GREEN to ORANGE then RED
	 */
	public void switchState() {
		if(realState == TrafficLight.RED) {
			timerRed.stop();
			state = TrafficLight.GREEN;
			realState = TrafficLight.GREEN;
			timerGreen.start();
		}
		else if(realState == TrafficLight.GREEN) {
			timerGreen.stop();
			state = TrafficLight.ORANGE;
			realState = TrafficLight.ORANGE;
			timerSwitch.start();
		}
	}
	
}
