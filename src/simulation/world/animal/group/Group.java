package simulation.world.animal.group;

import simulation.world.animal.species.AbstractAnimal;

/**
 * A group of animals, as described by animal, which is a species, and
 * groupState, which is the current state of this group.
 * 
 * @author SOARES Lucas
 */
public class Group
{
	/**
	 * The description of animals in the group
	 */
	private AbstractAnimal animal;
	
	/**
	 * The state of the group
	 */
	private GroupState groupState;
	
	/**
	 * The maximum number of fellow in group
	 */
	private int maximumFellowNumber;

	public Group( AbstractAnimal animal,
		int maximumFellowNumber )
	{
		// Save
		this.animal = animal;
		this.maximumFellowNumber = maximumFellowNumber;
		
		// Init
		this.groupState = new GroupState( this.maximumFellowNumber );
	}
}
