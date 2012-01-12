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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import trafsim.trafsim.IDM;

/**
 * No description
 *
 */
public class Application {
	
	private RoadAreaGUI road;
	
	private JPanel controlsArea;
	
	private JFrame frame;
	
	private JTextField txtAcceleration;
	private JTextField txtBrakingDeceleration;
	private JTextField txtDesiredVelocity;
	private JTextField txtMinimumSpacing;
	private JTextField txtTimeHeadway;
	
	private JButton btnStart;
	private JButton btnStop;
	
	private IDM model;



	/**
	 * Default constructor. Create the graphic interface automatically, add cars and traffic lights and launch the system.
	 */
	public Application() {
		
		frame = new JFrame("IDM Implementation");
		
		model = new IDM();
		
		// Init frame
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1200, 600);

		// Road
		road = new RoadAreaGUI();
		road.setModel(model);
		
		// Controls
		controlsArea = new JPanel(new GridLayout(7, 2));
		controlsArea.setBorder(new EmptyBorder(30, 30, 30, 30));
		
		txtAcceleration = new JTextField(30);
		txtAcceleration.setText("0.3");
		
		txtBrakingDeceleration = new JTextField(30);
		txtBrakingDeceleration.setText("3.0");
		
		txtDesiredVelocity = new JTextField(30);
		txtDesiredVelocity.setText("15");
		
		txtMinimumSpacing = new JTextField(30);
		txtMinimumSpacing.setText("1.0");
		
		txtTimeHeadway = new JTextField(30);
		txtTimeHeadway.setText("1.5");
		
		
		// Velocity
		controlsArea.add(new JLabel("Desired velocity"), 0);
		controlsArea.add(txtDesiredVelocity,1);
		
		// Float minimumSpacing
		controlsArea.add(new JLabel("Minimum spacing"), 2);
		controlsArea.add(txtMinimumSpacing, 3);
		
		// Float timeHeadway
		controlsArea.add(new JLabel("Time headway"));
		controlsArea.add(txtTimeHeadway);
		
		// Float acceleration
		controlsArea.add(new JLabel("Acceleration"));
		controlsArea.add(txtAcceleration);
		
		// Float brakingDeceleration
		controlsArea.add(new JLabel("Braking deceleration"));
		controlsArea.add(txtBrakingDeceleration);
		
		// Separators
		controlsArea.add(new JLabel("--------"));
		controlsArea.add(new JLabel("--------"));
		
		// Buttons
		btnStart = new JButton("Start");
		btnStop = new JButton("Stop");
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				model = new IDM();
				model.setAcceleration(Float.parseFloat(txtAcceleration.getText()));
				model.setBrakingDeceleration(Float.parseFloat(txtBrakingDeceleration.getText()));
				model.setDesiredVelocity(Float.parseFloat(txtDesiredVelocity.getText()));
				model.setMinimumSpacing(Float.parseFloat(txtMinimumSpacing.getText()));
				model.setTimeHeadway(Float.parseFloat(txtTimeHeadway.getText()));
				
				road.setModel(model);
				road.startSimulation();
				
			}
			
		});
		
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				road.stopSimulation();
			}
			
		});
		
		controlsArea.add(btnStart);
		controlsArea.add(btnStop);
		
		frame.setLayout(new FlowLayout());
		frame.add(road);
		frame.add(controlsArea);
		
		road.repaint();
		
		frame.setVisible(true);

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
