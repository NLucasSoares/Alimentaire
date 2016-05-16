package simulation.world.animal.group;

import java.util.Iterator;

import simulation.constant.SimulationConstant;
import simulation.gui.object.Hexagon;
import simulation.math.angle.AngleMovement;
import simulation.world.animal.move.AimedPosition;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.animal.species.state.AnimalState;
import simulation.world.animal.species.state.CarnivorousState;
import simulation.world.environment.Map;
import simulation.world.environment.MapState;

public class CarnivorousGroup extends Group
{
	/**
	 * Aimed herbivorous group
	 */
	private HerbivorousGroup aimedGroup;
	
	/**
	 * Aimed position
	 */
	private AimedPosition aimedPosition;
	
	/**
	 * Construct the group
	 * 
	 * @param animal
	 * 		The animal which group is composed of
	 * @param initialFellowCount
	 * 		The initial count of group fellows
	 * @param initialPosition
	 * 		The initial position
	 */
	public CarnivorousGroup( AbstractAnimal animal,
		int initialFellowCount,
		AngleMovement initialPosition,
		Map map )
	{
		// Parent constructor
		super( animal,
			initialFellowCount,
			initialPosition,
			map );
		
		// Init
		this.aimedGroup = null;
		this.aimedPosition = new AimedPosition( );
	}
	
	/**
	 * Construct the group
	 * 
	 * @param animal
	 * 		The animal which group is composed of
	 * @param initialFellowCount
	 * 		The initial count of ground fellows
	 */
	public CarnivorousGroup( AbstractAnimal animal,
		int initialFellowCount,
		Map map )
	{
		// Parent constructor
		super( animal,
			initialFellowCount,
			map );
		
		// Init
		this.aimedGroup = null;
		this.aimedPosition = new AimedPosition( );
	}
	
	/**
	 * Update
	 */
	@Override
	public void update( MapState state )
	{
		// Parent update
		super.update( state );
		
		// If something random is aimed
		if( this.aimedPosition.isAimingSomething( ) )
		{
			// Check if reached goal
			if( this.aimedPosition.isReachedPosition( super.getPosition( ) ) )
			{
				// Stop moving
				super.stopMoving( );
				
				// Remove aim
				this.aimedPosition.removeAim( );
			}
			else
			{
				// Aim
				super.aimPosition( this.aimedPosition.getPosition( ) );

				// Start moving
				super.startMoving( );
			}
		}
		
		// Hunt herbivorous
		if( this.aimedGroup == null )
		{
			// Check population
			if( super.getMap( ).getState( ).getHerbivorousGroupCount( ) >= SimulationConstant.MINIMUM_GROUP_COUNT_MAP )
			{
				// Look for herbivorous
				for( Iterator<Group> it = super.getMap( ).getState( ).getAnimalGroup( ); it.hasNext( ); )
				{
					// Get group
					Group group = it.next( );
					
					// Check if herbivorous
					if( group instanceof HerbivorousGroup )
						if( group.getGroupRange( ) != null )
							if( group.getGroupRange( ).intersects( super.getGroupRange( ) ) )
							{
								// Remember this group
								this.aimedGroup = (HerbivorousGroup)group;
								
								// Remove random aim
								this.aimedPosition.removeAim( );

								// Aim the group
								super.aimAnimalGroup( group );
								
								// Start moving
								super.startMoving( );
							}
				}
			}
			
			// If nothing found
			if( this.aimedGroup == null )
			{
				if( !this.aimedPosition.isAimingSomething( ) )
				{
					// Remember the aim
					this.aimedPosition.setNewAimedPoint( Hexagon.getRandomPosition( Map.SIZE_PIXEL_BY_SIZE_UNIT ) );
	
					// Remove aim on object
					super.removeAimOnObject( );
				}
			}
		}
		else
		{
			// Check if hunted group still exists
			if( this.aimedGroup.getAnimalState( ).size( ) > 0
				// Check if it still enough groups on map
				&& super.getMap( ).getState( ).getHerbivorousGroupCount( ) >= SimulationConstant.MINIMUM_GROUP_COUNT_MAP )
			{
				// Choose animal to hunt
				for( Iterator<AnimalState> it = super.getAnimalState( ).iterator( ); it.hasNext( ); )
				{
					// Get animal
					CarnivorousState carnivorous = (CarnivorousState)it.next( );
					
					// If carnivorous isn't hunting for now
					if( !carnivorous.isHunting( )
						&& ( ( ( carnivorous.getHealthState( ).getProtein( ) / carnivorous.getAnimal( ).getNeedDefinition( ).getProtein( ) ) * 100.0d ) <= SimulationConstant.FLOOR_FOR_CARNIVOROUS_START_HUNT ) )
					{
						// Check for herbivorous meeting
						for( Iterator<AnimalState> it2 = this.aimedGroup.getAnimalState( ).iterator( ); it2.hasNext( ); )
						{
							// Get herbivorous
							AnimalState huntedAnimal = it2.next( );

							// If found herbivorous
							if( carnivorous.getRange( ).contains( huntedAnimal.getRange( ).getPosition( ) ) )
								carnivorous.hunt( huntedAnimal );
						}
					}
				}
			}
			else
			{
				// Remove aim
				this.aimedPosition.removeAim( );

				// Remove aim of carnivorous on herbivorous
				for( Iterator<AnimalState> it = super.getAnimalState( ).iterator( ); it.hasNext( ); )
				{
					// Get animal
					CarnivorousState animal = (CarnivorousState)it.next( );

					// Stop moving
					animal.stopMoving( );
					
					// Stop hunting
					animal.removeAimHunting( );
				}
				
				// Remove aim for parent
				super.removeAimOnObject( );
				
				// Stop moving
				super.stopMoving( );
				
				// Forget group
				this.aimedGroup = null;
			}
		}
	}
}
