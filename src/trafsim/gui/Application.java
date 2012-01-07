/**
 * Principal file of the application
 *
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 *
 */
package trafsim.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
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
public class Application extends JFrame implements ActionListener {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JFrame frame;
	private Timer timer;
	private ListCar cl;
	private Road street;
	
	public Application() {
		
		// Init frame
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1100, 600);
		
		this.setIgnoreRepaint(true);
		RepaintManager currentManager = 
			  RepaintManager.currentManager(this);
			currentManager.setDoubleBufferingEnabled(false);
		
		/**************************
		 * TEST & DEBUG
		 **************************/
		cl = new ListCar();
		street = new Road(new Coordinate(10f, 30f), 65, 1000, 25);
		
		cl.add( new Car( 12f, 66f, 5f ) );  
		cl.add( new Car( 32f, 66f, 0f ) );  
		cl.add( new Car( 64f, 66f, 12f ) ); 
		
		
		street.setCarList(cl);
	
		this.repaint();
		this.setVisible(true);
		
		Timer timer = new Timer( 50, this);
		timer.setInitialDelay(0);
		timer.start();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		//System.out.println("Vitesse avant : " + cl.get(0).getVelocity() + " || Position avant (x) : " + cl.get(0).getPosition().getX());
		
		IDM.updateCarsVelocity( this.cl );
		IDM.updateCarsPosition( this.cl );
		
		//System.out.println("Vitesse apres : " + cl.get(0).getVelocity() + " || Position après (x) : " + cl.get(0).getPosition().getX() );
		
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		street.getImage().paint(g);
		
		for (Car car : cl)
		{
			car.getImage().paint(g);
		}
		g.dispose();
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
