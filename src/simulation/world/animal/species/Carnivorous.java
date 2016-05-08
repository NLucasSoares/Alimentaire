package simulation.world.animal.species;

import simulation.world.animal.need.Need;

/**
 * The class used for carnivorous animals
 * 
 * @author CAMPS Aurèle
 */
public class Carnivorous extends AbstractAnimal
{
	/**
	 * Construct a Carnivorous Animal
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
	 * @param maximumDensity
	 * 		The maximum fellows in the group
	 * @param lifeTime
	 * 		The life time of animal
	 */
	public Carnivorous( String name,
		int weight,
		int size,
		int agility,
		Need needDefinition,
		int maximumDensity,
		int lifeTime,
		int reproduceTime )
	{
		super( name,
			weight,
			size,
			agility,
			needDefinition,
			maximumDensity,
			lifeTime,
			reproduceTime );
	}
}
