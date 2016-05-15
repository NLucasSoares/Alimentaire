package simulation.world.animal.species.state;

import simulation.constant.SimulationConstant;
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
	 * Hunt current hunted animal
	 */
	private void hunt( )
	{
		// Check if animal hunted is alived
		if( !this.huntedAnimal.isAlive( ) )
			// Stop hunt
			this.stopHunt( );
		else
		{
			// If reached, attack
			if( super.getRange( ).contains( this.huntedAnimal.getRange( ).getPosition( ) ) )
			{
				// Stop moving
				super.stopMoving( );
				
				// Attack
				this.huntedAnimal.beAttacked( 1 );
				
				// If animal killed, eat
				if( !this.huntedAnimal.isAlive( ) )
					this.getHealthState( ).eatAnimal( ( this.huntedAnimal.getAnimal( ).getWeight( ) * this.huntedAnimal.getAnimal( ).getSize( ) ) );
			}
			// Not reached
			else
			{
				// Aim
				super.aimPosition( this.huntedAnimal.getRange( ).getPosition( ) );
			
				// Go
				super.startMoving( );
			}
		}
	}
	
	/**
	 * Stop hunt
	 */
	private void stopHunt( )
	{
		// Stop moving
		super.stopMoving( );

		// Lose aim
		this.huntedAnimal = null;
		
		// Quit hunting
		return;
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
			if( super.getGroup( ).getMap( ).getState( ).getHerbivorousGroupCount( ) >= SimulationConstant.MINIMUM_GROUP_COUNT_MAP )
				this.hunt( );
			else
				this.stopHunt( );

		// Update health
		super.getHealthState( ).addProteinConsumption( this.isHunting( ) ? 4 : 1 );
	}

}
