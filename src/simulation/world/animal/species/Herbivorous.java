package simulation.world.animal.species;

import simulation.world.animal.diet.Diet;
import simulation.world.animal.need.Need;

/**
 * The class used for herbivorous animals
 * 
 * @author Aurele Camps
 */

public class Herbivorous extends simulation.world.animal.species.AbstractAnimal {

	public Herbivorous( String name,
		int weight,
		int size,
		int agility,
		Diet diet,
		Need needDefinition )
	{
		super( name,
			weight,
			size,
			agility,
			diet,
			needDefinition );
	}

}
