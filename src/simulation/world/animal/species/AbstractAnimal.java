package simulation.world.animal.species;

import simulation.world.animal.diet.Diet;
import simulation.world.animal.need.Need;

/**
 * Abstract class from which every type of animal is created.
 * 
 * @author Clément Thévin
 * 
 */
public abstract class AbstractAnimal {
	/**
	 * Name of the species of course.
	 */
	private String name;

	/**
	 * Weight. Yeah. That was obvious. Affects predators' ability to hunt and
	 * kill this animal.
	 */
	private int weight;

	/**
	 * Size. Yeah, that, too, was obvious. Affects capabilities of this animal
	 * to reach food, for example.
	 */
	private int size;

	/**
	 * *cough cough* We shall do something about this one.
	 */
	private int agility;

	/**
	 * What this animal can and cannot eat. Ask a zebra to eat a lion, without
	 * this table, it'll just do so ! Now with the table, it will first check if
	 * it has the right to, then say "no sir, I cannot do that" or
	 * "yes sir, I will" accordingly. Genius, right ?
	 */
	private Diet diet;

	/**
	 * Picture to be displayed in order to identify this animal.
	 */
	// private IMAGE charset;

	/**
	 * What this animal basically needs to avoid diying from anything. Except
	 * predators and aging of course.
	 */
	private Need needDefinition;
	
	/**
	 * Construct Animal
	 */
	public AbstractAnimal( String name,
		int weight,
		int size,
		int agility,
		Diet diet,
		Need needDefinition )
	{
		
	}

	public String getName( )
	{
		return name;
	}
	
	public int getWeight( )
	{
		return weight;
	}
	
	public int getSize( )
	{
		return size;
	}
	
	public int getAgility( )
	{
		return agility;
	}
	
	public Diet getDiet( )
	{
		return diet;
	}

	public Need getNeedDefinition() {
		return needDefinition;
	}

}
