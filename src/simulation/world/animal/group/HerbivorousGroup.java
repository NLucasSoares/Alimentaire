package simulation.world.animal.group;

import java.util.Iterator;

import simulation.constant.SimulationConstant;
import simulation.gui.object.Hexagon;
import simulation.math.angle.AngleMovement;
import simulation.math.point.Point;
import simulation.math.probability.Experience;
import simulation.world.animal.move.AimedPosition;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.animal.species.state.AnimalState;
import simulation.world.environment.Map;
import simulation.world.environment.MapState;
import simulation.world.environment.plant.PlantGroup;

public class HerbivorousGroup extends Group
{
	/**
	 * Currently aimed plant group
	 */
	private PlantGroup currentlyAimedPlantGroup;
	
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
	public HerbivorousGroup( AbstractAnimal animal,
		int initialFellowCount )
	{
		// Parent constructor
		super( animal,
			initialFellowCount );
		
		// Init
		this.currentlyAimedPlantGroup = null;
		this.aimedPosition = new AimedPosition( );
	}
	
	public void update( MapState state )
	{
		// Parent update
		super.update( state );
		
		// Check the random move
		if( this.aimedPosition.isAimingSomething( ) )
			// Check for goal
			if( this.aimedPosition.isReachedPosition( this.getPosition( ) ) )
			{
				// Stop movement
				super.stopMoving( );
				
				// Remove aimed positon
				this.aimedPosition.removeAim( );
			}
		
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
					
					// Leave search
					break;
				}
			}

		// Go to the found source
		if( this.currentlyAimedPlantGroup != null )
		{
			// Aim
			this.aimPlantGroup( this.currentlyAimedPlantGroup );
			
			// Stop if arrived
			if( this.getGroupRange( ).contains( this.currentlyAimedPlantGroup.getGroupRange( ).getPosition( ) ) )
				this.stopMoving( );
			// Make group move
			else
				this.startMoving( );
				
		}
		// If nothing was found, random move
		else
		{
			// If no random move is happening
			if( !this.aimedPosition.isAimingSomething( ) )
			{
				// Experience
				Experience experience = new Experience( SimulationConstant.HERBIVOROUS_GROUP_RANDOM_MOVE_PROBABILITY );
				
				// Do the experience
				experience.doExperience( );
				
				// Get result
				if( experience.getEventID( ) == 0 )
				{
					// The aimed position
					Point<Double> position;
					
					// Hexagon
					Hexagon h = new Hexagon( Map.SIZE_PIXEL_BY_SIZE_UNIT );
					
					// Pick a random position
					do
					{
						// Random creation
						position = new Point<Double>( simulation.math.probability.Operation.random( 0.0d,
								(double)Map.SIZE_PIXEL_BY_SIZE_UNIT ),
							simulation.math.probability.Operation.random( 0.0d,
								(double)Map.SIZE_PIXEL_BY_SIZE_UNIT ) );
					} while( !h.isContaining( new Point<Integer>( position.getX( ).intValue( ),
						position.getY( ).intValue( ) ) ) );
					
					// Aim the point
						// Set for later check up
							this.aimedPosition.setNewAimedPoint( position );
						// Aim
							super.aimPosition( position );
						// Start moving
							super.startMoving( );
				}
			}
		}
		
		// Animals move randomly
			// Experience
				Experience experience = new Experience( SimulationConstant.HERBIVOROUS_INDIVIDUAL_RANDOM_MOVE_PROBABILITY );
			// Move if experience succeeds
				for( Iterator<AnimalState> it = super.getAnimalState( ).iterator( ); it.hasNext( ); )
				{
					// Get animal
					AnimalState animal = it.next( );
					
					// Do experience
					experience.doExperience( );
					
					// Get result
					if( experience.getEventID( ) == 0 )
						animal.activateRandomMove( );
				}
	}

}
