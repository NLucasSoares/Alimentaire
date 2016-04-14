package simulation.world.animal.species;

import simulation.world.animal.diet.Diet;
import simulation.world.animal.need.Need;

/**
 * The class used for carnivorous animals
 * 
 * @author acamps
 */

public class Carnivorous extends AbstractAnimal {

	public Carnivorous( String name,
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
