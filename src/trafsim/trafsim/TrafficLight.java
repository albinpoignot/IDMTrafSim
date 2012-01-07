package trafsim.trafsim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Representation of a traffic light in the system
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */
public class TrafficLight implements ActionListener
{
	/**
	 * Position in the system
	 */
	private Coordinate position;
	
	/**
	 * The Car object used to simulate a stopped car in order to force the other cars to stop 
	 */
	private Car car;
	
	/**
	 * List Car
	 */
	private ListCar cl;
	
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
	private static Integer switchGap = 2000 ;
	
	private Timer timerR;
	private Timer timerG;
	private Timer timerS;
	private final Semaphore sem;
	
	/**
	 * Default constructor
	 * @param position The position of traffic lights
	 */
	
	public TrafficLight(Coordinate position, ListCar cl, Semaphore sem ) 
	{
		this.position = position;
		this.cl = cl;	
		this.car = new Car( position.getX(), position.getY(), 0f, 0f );
		this.sem = sem;
		
		try {
			insererFeu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.timerR = new Timer( redGap, this );
		this.timerR.setInitialDelay( redGap );
		this.timerR.start();
		
		this.timerG = new Timer( greenGap, this );
		this.timerG.setInitialDelay(greenGap);
		
		this.timerS = new Timer( switchGap, this );
		this.timerS.setInitialDelay(switchGap);
	}
	
	private void insererFeu() throws Exception 
	{
		sem.acquire(); 
		//System.out.println("Insertion feu - DEBUT");
		int index = 0;
		
		for( int i = 0; i < cl.size(); i ++ )
		{
			if( cl.get(i).getPosition().getX() < this.position.getX() )
			{
				index++;
			}
		}
		//System.out.println("Je rajoute le feu à l'index : " + index );
		cl.add(index, this.car );
		//System.out.println("Insertion feu - FIN");
		sem.release();
	}
	
	private void enleverFeu() throws Exception
	{
		this.sem.acquire();
		//System.out.println("Enleve feu - DEBUT");
		for( int i = 0; i < cl.size(); i ++ )
		{
			if( cl.get(i) == car )
			{
				//System.out.println("J'enelve le feu à l'index : " + i );
				cl.remove(i);
			}
		}
		//System.out.println("Enleve feu - FIN");
		this.sem.release();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)  // Todo Changer graphisme
	{
		if( arg0.getSource() == this.timerR )
		{
			//System.out.println( "Feu rouge fin ");
			timerR.stop();
			timerG.start();
			try {
				enleverFeu();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( arg0.getSource() == this.timerS )
		{
			//System.out.println( "Switch fin ");
			timerS.stop();
			timerR.start();
		}
		else if(arg0.getSource() == this.timerG )
		{
			//System.out.println( "Feu vert fin ");
			timerG.stop();
			try {
				insererFeu();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timerS.start();
		}
	}
	
}
