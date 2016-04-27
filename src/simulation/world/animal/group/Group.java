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
	 * Construct the group
	 * 
	 * @param animal
	 * 		The animal which group is composed of
	 * @param initialFellowCount
	 * 		The initial count of group fellows
	 */
	public Group( AbstractAnimal animal,
		int initialFellowCount )
	{
		// Save
		this.animal = animal;
		
		// Init
		this.groupState = new GroupState( initialFellowCount,
			this.animal );
	}
	
	/**
	 * @return the animal for this group
	 */
	public AbstractAnimal getAnimal( )
	{
		return this.animal;
	}
	
	/**
	 * @return the group state
	 */
	public GroupState getState( )
	{
		return this.groupState;
	}
	
}
