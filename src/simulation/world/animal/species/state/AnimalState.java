package simulation.world.animal.species.state;

import java.util.ArrayList;

import simulation.constant.MathConstant;
import simulation.constant.SimulationConstant;
import simulation.math.point.Point;
import simulation.world.animal.group.Group;
import simulation.world.animal.need.state.NeedState;

/**
 * Current state of a given animal.
 * 
 * @author Clément Thévin
 * 
 */
public class AnimalState {

	/**
	 * Reference to the group this animal belongs to.
	 */
	private Group groupReference;
	
	/**
	 * Current state of the needs of this animal.
	 */
	private NeedState needState;
	
	/**
	 * Current position of this animal on the world grid.
	 */
	private simulation.math.point.Point<Integer> position;
	
	/**
	 * The birth date
	 */
	private int birthDate;

	/**
	 * The influence level
	 */
	private int influenceLevel;

	public AnimalState(Group groupReference,
			NeedState needState, simulation.math.point.Point<Integer> position) {
	}
}
