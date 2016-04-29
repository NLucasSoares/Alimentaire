package simulation.world.animal.species.state;

import simulation.math.point.Point;
import simulation.world.animal.group.Group;
import simulation.world.animal.species.AbstractAnimal;

public class CarnivorousState extends AnimalState
{
	/**
	 * Construct the animal state
	 * 
	 * @param groupReference
	 * 		The callback to the group
	 * @param animal
	 * 		The animal definition
	 * @param currentTurn
	 * 		The current turn of simulation
	 */
	public CarnivorousState( Group groupReference,
		Point<Double> initialPosition,
		AbstractAnimal animal,
		int currentTurn )
	{
		// Parent constructor
		super( groupReference,
			initialPosition,
			animal,
			currentTurn );
	}

}