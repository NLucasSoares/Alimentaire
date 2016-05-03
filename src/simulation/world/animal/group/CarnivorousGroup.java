package simulation.world.animal.group;

import simulation.math.angle.AngleMovement;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.environment.Map;

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
		AngleMovement initialPosition,
		Map map )
	{
		// Parent constructor
		super( animal,
			initialFellowCount,
			initialPosition,
			map );
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
		int initialFellowCount,
		Map map )
	{
		// Parent constructor
		super( animal,
			initialFellowCount,
			map );
	}
	
	

}
