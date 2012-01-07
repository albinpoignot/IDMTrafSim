/**
 * Principal file of the application
 *
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 *
 */
package trafsim.gui;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import trafsim.gui.RoadGUI;

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
	private ListTrafficLight tl;
	private Road street;
	private final Semaphore sem = new Semaphore( 1, false); 
	
	public Semaphore getSem() {
		return sem;
	}


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
		
		cl.add(new Car( 12f, 66f, 0f, 15f ) );
		cl.add(new Car( 32f, 66f, 5f, 15f ) );
		cl.add(new Car( 52f, 66f, 3f, 15f ) );
		cl.add(new Car( 84f, 66f, 8f, 15f ) );
		cl.add(new Car( 100f, 66f, 5f, 15f ) );
		
		street.setCarList(cl);
		
		this.repaint();
		this.setVisible(true);
		
		this.createBufferStrategy(2);
		
		this.timer = new Timer( 50, this );
		timer.setInitialDelay(0);
		timer.start();
		
		tl = new ListTrafficLight();
		tl.add( new TrafficLight( new Coordinate( 300f, 80f), cl, sem ) );
		tl.add( new TrafficLight( new Coordinate( 600f, 80f), cl, sem ) );
		tl.add( new TrafficLight( new Coordinate( 800f, 80f), cl, sem ) );
	}
	
	
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
			//System.out.println("Update velocité et position - Debut");
			IDM.updateCarsVelocity( this.cl );
			IDM.updateCarsPosition( this.cl );
			//System.out.println("Update velocité et position - FIN");
			sem.release();
		//System.out.println("Vitesse apres : " + cl.get(0).getVelocity() + " || Position aprÃ¨s (x) : " + cl.get(0).getPosition().getX() );
		
		this.repaint();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);

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
			
			for (Car car : cl) {
				car.getImage().paint(g);
			}
			
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
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Application app = new Application();
		System.out.println("Application launched");

	}

}
