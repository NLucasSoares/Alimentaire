package simulation.world.animal.group;

import simulation.world.animal.species.AbstractAnimal;

/**
 * A group of animals, as described by animal, which is a species, and
 * groupState, which is the current state of this group.
 * 
 * @author SOARES Lucas
 * 
 */

public class Group {
	private AbstractAnimal animal;
	private GroupState groupState;
	private int maximumFellowNumber;

	public Group(AbstractAnimal animal, int maximumFellowNumber) {
	}
}
