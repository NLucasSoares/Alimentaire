package simulation.world.animal.group;

import java.util.Iterator;

import simulation.math.angle.AngleMovement;
import simulation.math.point.Point;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.animal.species.state.AnimalState;
import simulation.world.environment.MapState;
import simulation.world.environment.plant.PlantGroup;

public class HerbivorousGroup extends Group
{
	/**
	 * Currently aimed plant group
	 */
	public PlantGroup currentlyAimedPlantGroup;
	
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
	public HerbivorousGroup( AbstractAnimal animal,
		int initialFellowCount,
		AngleMovement initialPosition )
	{
		// Parent constructor
		super( animal,
			initialFellowCount,
			initialPosition );
		
		// Init
		this.currentlyAimedPlantGroup = null;
	}
	
	/**
	 * Construct the group
	 * 
	 * @param animal
	 * 		The animal which group is composed of
	 * @param initialFellowCount
	 * 		The initial count of ground fellows
	 */
	public HerbivorousGroup( AbstractAnimal animal,
		int initialFellowCount )
	{
		// Parent constructor
		super( animal,
			initialFellowCount );
		
		// Init
		this.currentlyAimedPlantGroup = null;
	}
	
	public void update( MapState state )
	{
		// Parent update
		super.update( state );
		
		// New source?
		boolean isNewSource = false;
		
		// Check for current food source existence
		if( this.currentlyAimedPlantGroup != null )
			if( this.currentlyAimedPlantGroup.getLeaves( ) == 0 )
			{
				// Cut link
				this.currentlyAimedPlantGroup = null;
				
				// Stop movement
				super.stopMoving( );
			}
		
		// If need to find new source
		if( this.currentlyAimedPlantGroup == null )
			for( Iterator<PlantGroup> it = state.getPlantGroup( ); it.hasNext( ); )
			{
				// Get the group
				PlantGroup p = it.next( );
				
				// Check for intersect
				if( this.isInteresect( p ) )
				{
					// Found the new source
					this.currentlyAimedPlantGroup = p;
					
					// This is a new source
					isNewSource = true;
					
					// Leave search
					break;
				}
			}
		
		// If nothing was found, the search is expensive (range++ while not found)
		
		// Go to the found source
		if( this.currentlyAimedPlantGroup != null )
		{
			// Make group move
				// Aim
					this.aimPlantGroup( this.currentlyAimedPlantGroup );
				// Go
					this.startMoving( );
			
			
		}
	}

}
