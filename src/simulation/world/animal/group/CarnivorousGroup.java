package simulation.world.animal.group;

import simulation.math.angle.AngleMovement;
import simulation.world.animal.species.AbstractAnimal;

public class CarnivorousGroup extends Group
{
	/**
	 * Construct the group
	 * 
	 * @param animal
	 * 		The animal which group is composed of
	 * @param initialFellowCount
	 * 		The initial count of group fellows
	 * @param initialPosition
	 * 		The initial position
	 */
	public CarnivorousGroup( AbstractAnimal animal,
		int initialFellowCount,
		AngleMovement initialPosition )
	{
		// Parent constructor
		super( animal,
			initialFellowCount,
			initialPosition );
	}
	
	/**
	 * Construct the group
	 * 
	 * @param animal
	 * 		The animal which group is composed of
	 * @param initialFellowCount
	 * 		The initial count of ground fellows
	 */
	public CarnivorousGroup( AbstractAnimal animal,
		int initialFellowCount )
	{
		// Parent constructor
		super( animal,
			initialFellowCount );
	}
	
	

}
