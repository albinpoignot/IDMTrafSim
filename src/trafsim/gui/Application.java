/**
 * Principal file of the application
 *
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 *
 */
package trafsim.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import trafsim.trafsim.Car;
import trafsim.trafsim.Coordinate;
import trafsim.trafsim.IDM;
import trafsim.trafsim.ListCar;
import trafsim.trafsim.Road;


/**
 * No description
 *
 */
public class Application implements ActionListener {

	private JPanel panel;
	private JFrame frame;
	private Timer timer;
	private ListCar cl;
	
	public Application() {
		
		// Init frame
		frame = new JFrame("IDM Traffic Simulation");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// Init panel
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		
		/**************************
		 * TEST & DEBUG
		 **************************/
		cl = new ListCar();
		Road street = new Road(new Coordinate(0f, 0f), 65, 1000, 25);
		cl.add( new Car( 0f, 50f, new Float( 14 ) ) );
		
		panel.add(street.getImage());
		frame.add(cl.get(0).getImage());
		// TODO Create graphic elements
		
		
		// Add panel to the frame and display them
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		street.getImage().repaint();
		
		Timer timer = new Timer( 20, this);
		timer.setInitialDelay(0);
		timer.start(); 
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		//System.out.println("Vitesse avant : " + cl.get(0).getVelocity() + " || Position avant (x) : " + cl.get(0).getPosition().getX());
		IDM.updateCarsVelocity( this.cl );
		IDM.updateCarsPosition( this.cl );
		this.cl.paintAllCars();
		//System.out.println("Vitesse apres : " + cl.get(0).getVelocity() + " || Position après (x) : " + cl.get(0).getPosition().getX() );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Application app = new Application();
		System.out.println("Application launched");

	}

}
