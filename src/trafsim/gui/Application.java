/**
 * Principal file of the application
 *
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 *
 */
package trafsim.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import trafsim.trafsim.ListCar;


/**
 * No description
 *
 */
public class Application {

	private JPanel panel;
	private JFrame frame;
	
	
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
		ListCar cl = new ListCar();
		RoadGUI street = new RoadGUI(cl);
		
		panel.add(street);
		
		// TODO Create graphic elements
		
		
		// Add panel to the frame and display them
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		
		street.repaint();
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
