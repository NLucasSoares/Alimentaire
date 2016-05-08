package simulation.world.animal.species;

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
	 * The size of animal (in cm)
	 */
	private int size;

	/**
	 * The ability of animal (between 0 and 100)
	 */
	private int agility;
	
	/**
	 * Maximum group size
	 */
	private int maximumDensity;

	/**
	 * Lifetime
	 */
	private int lifeTime;
	
	/**
	 * Reproduce time
	 */
	private int reproduceTime;
	
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
	 * @param lifeTime
	 * 		The life time of animal
	 * @param reproduceTime
	 * 		Time before able to reproduce
	 */
	public AbstractAnimal( String name,
		int weight,
		int size,
		int agility,
		Need needDefinition,
		int maximumDensity,
		int lifeTime,
		int reproduceTime )
	{
		// Save
		this.name = name;
		this.weight = weight;
		this.size = size;
		this.agility = agility;
		this.needDefinition = needDefinition;
		this.maximumDensity = maximumDensity;
		this.lifeTime = lifeTime;
		this.reproduceTime = reproduceTime;
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
	 * @return the need definition of the animal
	 */
	public Need getNeedDefinition()
	{
		return needDefinition;
	}
	
	/**
	 * @return the maximum density
	 */
	public int getMaximumDensity( )
	{
		return this.maximumDensity;
	}
	
	/**
	 * @return the life time
	 */
	public int getLifeTime( )
	{
		return this.lifeTime;
	}
	
	/**
	 * @return the reproduce time
	 */
	public int getReproduceTime( )
	{
		return this.reproduceTime;
	}
}
