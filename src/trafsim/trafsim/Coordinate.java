/** 
 * Copyright 2012 Albin Poignot & Julien Teruel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * ---------------------------------------------
 *
 * Class Coordinate
 *
 * @author Albin Poignot, Julien Teruel
 * @version 1.0
 *
 */

package trafsim.trafsim;

/**
 * Representation of 2D coordinate in the system
 */
public class Coordinate 
{
	/**
	 *  The position on the X-axis
	 */
	private Float x;
	/**
	 *  The position on the Y-Axis
	 */
	private Float y;
	
	/**
	 * Default constructor. Sets x and y to 0.0
	 */
	public Coordinate()	{
		this.x = 0f;
		this.y = 0f;
	}
	
	/**
	 * Overload constructor to set x and y to specified values
	 * @param x : the position on the X_axis
	 * @param y : the position on the Y_axis
	 */
	public Coordinate(Float x, Float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return x the current position on the X-axis
	 */
	public Float getX() {
		return x;
	}

	/**
	 * @param x : the new value for the x
	 */
	public void setX(Float x) {
		this.x = x;
	}

	/**
	 * @return the current y value
	 */
	public Float getY() {
		return y;
	}

	/**
	 * @param y : the new value for y
	 */
	public void setY(Float y) {
		this.y = y;
	}
}
