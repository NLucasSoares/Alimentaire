package simulation.world.animal.species.state;

import simulation.math.point.Point;
import simulation.world.animal.group.Group;
import simulation.world.animal.need.state.NeedState;
import simulation.world.animal.species.AbstractAnimal;

/**
 * Current state of a given animal.
 * 
 * @author SOARES Lucas 
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
	private Point<Integer> position;
	
	/**
	 * The birth date (in turns)
	 */
	private int birthDate;

	/**
	 * Construct the animal state
	 * 
	 * @param groupReference
	 * 		The callback to the group
	 * @param animal
	 * 		The animal definition
	 * @param currentTurn
	 * 		The current turn of simulation
	 * 
	 */
	public AnimalState( Group groupReference,
		AbstractAnimal animal,
		int currentTurn )
	{
		// Save
		this.groupReference = groupReference;
		
		// Init
		this.needState = new NeedState( animal.getNeedDefinition( ) );
		this.position = new Point<Integer>( 0,
			0 );
		this.birthDate = currentTurn;
	}
}
