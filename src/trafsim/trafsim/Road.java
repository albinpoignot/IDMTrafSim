package trafsim.trafsim;

import trafsim.gui.RoadGUI;

/**
 * Representation of a road segment in the system
 * 
 * @author Albin Poignot, Julien Teruel
 * @version 0.1
 */
public class Road 
{
	/**
	 * Start position of this road segment
	 */
	private Coordinate position;
	
	/**
	 * Height of this road segment
	 */
	private Integer height ;
	
	/**
	 * Width of this road segment
	 */
	private Integer width;
	
	/**
	 * Graphical representation of the road segment
	 */
	private RoadGUI image;
	
	/**
	 * Speed limit on this road segment
	 */
	private Integer velocityLimit;
	
	/**
	 * List of the car on the road
	 */
	private ListCar carList;
	
	/**
	 * @return the positionStart
	 */
	public Coordinate getPositionStart() {
		return position;
	}

	/**
	 * @param positionStart the positionStart to set
	 */
	public void setPositionStart(Coordinate positionStart) {
		this.position = positionStart;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the image
	 */
	public RoadGUI getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(RoadGUI image) {
		this.image = image;
	}

	/**
	 * @return the velocityLimit
	 */
	public Integer getVelocityLimit() {
		return velocityLimit;
	}

	/**
	 * @param velocityLimit the velocityLimit to set
	 */
	public void setVelocityLimit(Integer velocityLimit) {
		this.velocityLimit = velocityLimit;
	}

	/**
	 * @return the carList
	 */
	public ListCar getCarList() {
		return carList;
	}

	/**
	 * @param carList the carList to set
	 */
	public void setCarList(ListCar carList) {
		this.carList = carList;
	}
	
	/**
	 * Overload Constructor
	 * @param position: Position
	 * @param height : Height
	 * @param width : Width
	 * @param speedLimit : speed limit on the segment
	 */
	public Road( Coordinate position, Integer height, Integer width, Integer speedLimit )
	{
		this.position = position;
		this.height = height;
		this.width = width;
		this.velocityLimit = speedLimit;
		
		this.image = new RoadGUI(position, height, width);
	}
	
}
