package simulation.world.animal.species;

import simulation.world.animal.diet.Diet;
import simulation.world.animal.need.Need;

/**
 * An animal definition
 * 
 * @author SOARES Lucas
 */
public abstract class AbstractAnimal
{
	/**
	 * The name
	 */
	private String name;

	/**
	 * The weight
	 */
	private int weight;

	/**
	 * The size of animal, for need calculation
	 */
	private int size;

	/**
	 * The ability of animal
	 */
	private int agility;

	/**
	 * The diet of an animal
	 */
	private Diet diet;

	/**
	 * 
	 */
	// private IMAGE charset;

	/**
	 * The needs of the animal
	 */
	private Need needDefinition;
	
	/**
	 * Construct Animal
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
	public AbstractAnimal( String name,
		int weight,
		int size,
		int agility,
		Diet diet,
		Need needDefinition )
	{
		
	}

	/**
	 * @return the name of the animal
	 */
	public String getName( )
	{
		return name;
	}
	
	/**
	 * @return the weight of the animal
	 */
	public int getWeight( )
	{
		return weight;
	}
	
	/**
	 * @return the size of the animal
	 */
	public int getSize( )
	{
		return size;
	}
	
	/**
	 * @return the agility of the animal
	 */
	public int getAgility( )
	{
		return agility;
	}
	
	/**
	 * @return the diet of the animal
	 */
	public Diet getDiet( )
	{
		return diet;
	}

	/**
	 * @return the need definition of the animal
	 */
	public Need getNeedDefinition()
	{
		return needDefinition;
	}
}
