package simulation.world.animal.species;

import simulation.constant.SimulationConstant;
import simulation.world.animal.need.Need;

/**
 * The class used for herbivorous animals
 * 
 * @author Aurele Camps
 */
public class Herbivorous extends simulation.world.animal.species.AbstractAnimal
{
	/**
	 * Round taken calculation basis for eating
	 */
	public static final double DIVISION_FACTOR_ROUND_EATING_HERBIVOROUS = 10.0d;
	
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
	 * @param lifeTime
	 * 		The life time of animal
	 */
	public Herbivorous( String name,
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
	
	/**
	 * Calculate the rounds count for leaf feeding
	 * 
	 * @return the rounds count
	 */
	public int calculateRoundTakenForLeafEating( )
	{
		return SimulationConstant.TURN_TAKEN_HERBIVOROUS_PLANT_FEEDING
			+ (int)( Herbivorous.DIVISION_FACTOR_ROUND_EATING_HERBIVOROUS
						- ( super.getAgility( ) / Herbivorous.DIVISION_FACTOR_ROUND_EATING_HERBIVOROUS ) );
	}
	
}
