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
 * Class ListTrafficLight
 *
 * @author Albin Poignot, Julien Teruel
 * @version 1.0
 *
 */

package trafsim.trafsim;

import java.util.ArrayList;

/**
 * Manage an ordered list of TrafficLights. Actually, do same kind of things that
 * ArrayList<TrafficLight> but allow to redefine some methods if needed.
 */
public class ListTrafficLight extends ArrayList<TrafficLight> {

	/**
	 * Automatically added for serializable methods (not used here)
	 */
	private static final long serialVersionUID = 1L;
	
}

