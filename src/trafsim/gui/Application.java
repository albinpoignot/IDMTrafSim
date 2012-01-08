/**
 * Principal file of the application
 *
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 *
 */
package trafsim.gui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.RepaintManager;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import trafsim.trafsim.Car;
import trafsim.trafsim.Coordinate;
import trafsim.trafsim.IDM;
import trafsim.trafsim.ListCar;
import trafsim.trafsim.ListTrafficLight;
import trafsim.trafsim.Road;
import trafsim.trafsim.TrafficLight;

/**
 * No description
 *
 */
public class Application extends JFrame implements ActionListener {

	/**
	 * Used in case of serialization. Useless here.
	 */
	private static final long serialVersionUID = 1L;
	
	/*private JPanel panel;
	private JFrame frame;*/
	
	/**
	 * Timer for repainting.
	 */
	private Timer timer;
	
	/**
	 * List of cars
	 */
	private ListCar carsList;
	
	/**
	 * List of traffic lights
	 */
	private ListTrafficLight trafficLightsList;
	
	/**
	 * A street in the system
	 */
	private Road street;
	
	/**
	 * A semaphore.
	 */
	private final Semaphore sem = new Semaphore( 1, false); 
	
	public Semaphore getSem() {
		return sem;
	}
	
	private JTextField textField;


	/**
	 * Default constructor. Create the graphic interface automatically, add cars and traffic lights and launch the system.
	 */
	public Application() {
		
		// Init frame
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1100, 600);
		
		this.setIgnoreRepaint(true);
		RepaintManager currentManager = RepaintManager.currentManager(this);
		currentManager.setDoubleBufferingEnabled(false);
		
		/**************************
		 * TEST & DEBUG
		 **************************/
		carsList = new ListCar();
		street = new Road(new Coordinate(10f, 30f), 65, 1000, 25);
		
		/* 5 cars + 3 traffic lights. No special behavior (such as high approaching rate) */
		carsList.add(new Car( 12f, 66f, 0f, 15f ) ); // Add in cl[0]
		carsList.add(new Car( 32f, 66f, 5f, 15f ) ); // Add in cl[1]
		carsList.add(new Car( 52f, 66f, 3f, 15f ) ); // Add in cl[2]
		carsList.add(new Car( 84f, 66f, 8f, 15f ) ); // Add in cl[3]
		carsList.add(new Car( 100f, 66f, 5f, 15f ) ); // Add in cl[4]


		trafficLightsList = new ListTrafficLight();
		trafficLightsList.add( new TrafficLight( new Coordinate( 300f, 80f), carsList, sem ) );
		trafficLightsList.add( new TrafficLight( new Coordinate( 600f, 80f), carsList, sem ) );
		trafficLightsList.add( new TrafficLight( new Coordinate( 800f, 80f), carsList, sem ) );
		
		street.setCarList(carsList);
		
		
		textField = new JTextField(30);
		//textField.setLocation(100, 90);
		
		//this.add(textField);
		this.setLayout(new FlowLayout());
		this.add(textField);
		textField.setLocation(400, 200);
		
		this.repaint();
		this.setVisible(true);
		
		this.createBufferStrategy(2);
		
		this.timer = new Timer( 50, this );
		timer.setInitialDelay(0);
		timer.start();

	}
	
	/**
	 * Automatically called by a Timer. Updates the cars's velocities, release semaphores then repaint the GUI.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		//System.out.println("Vitesse avant : " + cl.get(0).getVelocity() + " || Position avant (x) : " + cl.get(0).getPosition().getX());
			
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Update velocit� et position - Debut");
			IDM.updateCarsVelocity( this.carsList );
			IDM.updateCarsPosition( this.carsList );
			//System.out.println("Update velocit� et position - FIN");
			sem.release();
		//System.out.println("Vitesse apres : " + cl.get(0).getVelocity() + " || Position après (x) : " + cl.get(0).getPosition().getX() );
		
		this.repaint();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) 
	{
		//super.paint(g);

		/*
		street.getImage().paint(g);
		
		for (Car car : cl)
		{
			car.getImage().paint(g);
		}*/
		
		update(g);
		
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.JFrame#update(java.awt.Graphics)
	 * @see <a href="http://content.gpwiki.org/index.php/Java:Tutorials:Double_Buffering">Game Programing Wiki</a>
	 */
	@Override
	public void update(Graphics g) {
		
		
		BufferStrategy bf = this.getBufferStrategy();
	 
		//System.out.println(bf.toString());
		
		try {
			g = bf.getDrawGraphics();
			
			street.getImage().paint(g);
			
			for (Car car : carsList) {
				car.getImage().paint(g);
			}
			
			textField.paint(g);
			
		}
		catch (Exception e) {
			// Do nothing
		} finally {
			// It is best to dispose() a Graphics object when done with it.
			g.dispose();
		}

		if(bf != null)
		{
			// Shows the contents of the backbuffer on the screen.
			bf.show();
		}
	 
        //Tell the System to do the Drawing now, otherwise it can take a few extra ms until 
        //Drawing is done which looks very jerky
        Toolkit.getDefaultToolkit().sync();
	}
		

	/**
	 * Should not be called !
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Application app = new Application();
		System.out.println("Application launched");

	}

}
