package simulation.world.animal.species;

import simulation.world.animal.diet.Diet;
import simulation.world.animal.need.Need;

/**
 * The class used for herbivorous animals
 * 
 * @author Aurele Camps
 */
public class Herbivorous extends simulation.world.animal.species.AbstractAnimal
{
	/**
	 * Construct Herbivorous Animal
	 * 
	 * @param name
	 * 		The name of the animal
	 * @param weight
	 * 		The weight of the animal
	 * @param size
	 * 		The size of the animal
	 * @param agility
	 * 		The agility of the animal
	 * @param diet
	 * 		The diet of the animal
	 * @param needDefinition
	 * 		The need of the animal
	 */
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
