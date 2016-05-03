package simulation.world.animal.species;

import simulation.constant.SimulationConstant;
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
	 * @param maximumDensity
	 * 		The maximum fellows number
	 */
	public Herbivorous( String name,
		int weight,
		int size,
		int agility,
		Diet diet,
		Need needDefinition,
		int maximumDensity )
	{
		super( name,
			weight,
			size,
			agility,
			diet,
			needDefinition,
			maximumDensity );
	}
	
	/**
	 * Calculate the rounds count for leaf feeding
	 * 
	 * @return the rounds count
	 */
	public int calculateRoundTakenForLeafEating( )
	{
		return SimulationConstant.TURN_TAKEN_HERBIVOROUS_PLANT_FEEDING + (int)( 10.0d - ( super.getAgility( ) / 10.0d ) );
	}
	
}
