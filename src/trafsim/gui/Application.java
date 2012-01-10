/**
 * Principal file of the application
 *
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 *
 */
package trafsim.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * No description
 *
 */
public class Application {
	
	private RoadAreaGUI road;
	
	private JPanel controlsArea;
	
	private JFrame frame;
	
	private JTextField textField;


	/**
	 * Default constructor. Create the graphic interface automatically, add cars and traffic lights and launch the system.
	 */
	public Application() {
		
		frame = new JFrame("IDM Implementation");
		
		// Init frame
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1200, 600);

		// Road
		road = new RoadAreaGUI();
		
		// Controls
		controlsArea = new JPanel(new GridLayout());
		controlsArea.setBorder(new EmptyBorder(30, 30, 30, 30));
		
		textField = new JTextField(30);
		
		// Velocity
		controlsArea.add(new JLabel("Desired velocity"));
		controlsArea.add(textField);
		
		// 
		
		frame.setLayout(new FlowLayout());
		frame.add(road);
		frame.add(controlsArea);
		
		road.repaint();
		
		frame.setVisible(true);
		
		/*this.add(textField);
		textField.setLocation(400, 200);*/
		
		/*this.repaint();
		
		
		this.createBufferStrategy(2);
		
		this.timer = new Timer( 50, this );
		timer.setInitialDelay(0);
		timer.start();*/

	}
	
	/**
	 * Automatically called by a Timer. Updates the cars's velocities, release semaphores then repaint the GUI.
	 */
	/*@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		//System.out.println("Vitesse avant : " + cl.get(0).getVelocity() + " || Position avant (x) : " + cl.get(0).getPosition().getX());
			
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("Update velocity and position - Debut");
			IDM.updateCarsVelocity( this.carsList );
			IDM.updateCarsPosition( this.carsList );
			//System.out.println("Update velocity and position - FIN");
			sem.release();
		//System.out.println("Vitesse apres : " + cl.get(0).getVelocity() + " || Position après (x) : " + cl.get(0).getPosition().getX() );
		
		this.repaint();
	}*/
	
	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	/*@Override
	public void paint(Graphics g)
	{
		//super.paint(g);
		
		//street.getImage().paint(g);
		
		//for (Car car : cl)
		//{
			//car.getImage().paint(g);
		//}
		
		update(g);
	}*/
	
	/*@Override
	public void paintComponents(Graphics g)
	{
		update(g);
	}*/
	
	/* (non-Javadoc)
	 * @see javax.swing.JFrame#update(java.awt.Graphics)
	 * @see <a href="http://content.gpwiki.org/index.php/Java:Tutorials:Double_Buffering">Game Programing Wiki</a>
	 */
	/*@Override
	public void update(Graphics g) {
		
		
		BufferStrategy bf = this.getBufferStrategy();
	 
		//System.out.println(bf.toString());
		
		try {
			g = bf.getDrawGraphics();
			
			street.getImage().paint(g);
			
			for (Car car : carsList) {
				car.getImage().paint(g);
			}
			
			//textField.paint(g);
//			this.paintComponents(g);
			
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
	}*/
		

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
