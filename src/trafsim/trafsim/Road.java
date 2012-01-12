package trafsim.trafsim;

import java.util.ListIterator;

import javax.swing.text.html.HTMLDocument.Iterator;

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
	 * Model of circulation in this road
	 */
	private IDM model;
	
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
		this.image.setCarList(carList);
	}
	
	/**
	 * Overload Constructor
	 * @param position Position of the road
	 * @param height Height of the road
	 * @param width Width or the road
	 * @param speedLimit speed limit on the segment
	 */
	public Road( Coordinate position, Integer height, Integer width, Integer speedLimit, IDM model )
	{
		this.position = position;
		this.height = height;
		this.width = width;
		this.velocityLimit = speedLimit;
		this.carList = new ListCar();
		this.model = model;
		
		this.image = new RoadGUI(position, height, width);
	}
	
	/**
	 * Add a car at the end of the carList attribute
	 * @param car The car to add
	 */
	public void addCar(Car car) {
		carList.add(car);
	}
	
	public void addCar(Integer index, Car car) {
		carList.add(index, car);
	}
	
	public Integer getNumberOfCars() {
		return carList.size();
	}
	
	public Car getCarById(Integer i) {
		return carList.get(i);
	}
	
	/**
	 * Remove a car of the carList attribute
	 * @param car The car to remove
	 */
	public void removeCar(Car car) {
		carList.remove(car);
	}

	/**
	 * @return the model
	 */
	public IDM getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(IDM model) {
		this.model = model;
	}
	
	public void updateCars() {
		java.util.Iterator<Car> itr = carList.iterator();
		Car car;
		
		while(itr.hasNext()) {// (Car car : carList) {
			car = itr.next();
			model.updateCarsVelocity(car, carList);
			model.updateCarsPosition(car);
			if(car.getPosition().getX() > this.width) {
				itr.remove();//removeCar(car);
			}
		}
		
		
	}
}
