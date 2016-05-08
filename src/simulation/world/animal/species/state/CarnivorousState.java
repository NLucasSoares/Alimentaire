package simulation.world.animal.species.state;

import simulation.math.point.Point;
import simulation.world.animal.group.Group;
import simulation.world.animal.species.AbstractAnimal;

public class CarnivorousState extends AnimalState
{
	/**
	 * Currently aimed animal
	 */
	private AnimalState huntedAnimal;
	
	/**
	 * Construct the animal state
	 * 
	 * @param groupReference
	 * 		The callback to the group
	 * @param animal
	 * 		The animal definition
	 * @param currentTurn
	 * 		The current turn of simulation
	 */
	public CarnivorousState( Group groupReference,
		Point<Double> initialPosition,
		AbstractAnimal animal,
		long currentTurn )
	{
		// Parent constructor
		super( groupReference,
			initialPosition,
			animal,
			currentTurn );
		
		// Init
		this.huntedAnimal = null;
	}
	
	/**
	 * Remove aim hunting
	 */
	public void removeAimHunting( )
	{
		this.huntedAnimal = null;
	}
	
	/**
	 * Hunt
	 * 
	 * @param animal
	 * 		The animal to hunt
	 */
	public void hunt( AnimalState animal )
	{
		// Hunt
		this.huntedAnimal = animal;
	}
	
	/**
	 * @return is hunting an animal
	 */
	public boolean isHunting( )
	{
		return this.huntedAnimal != null;
	}
	
	/**
	 * Update
	 */
	public void update( )
	{
		// Parent update
		super.update( );

		// If hunting
		if( this.isHunting( ) )
		{
			
		}

		// Update health
		super.getHealthState( ).addProteinConsumption( this.isHunting( ) ? 4 : 0 );
	}

}
