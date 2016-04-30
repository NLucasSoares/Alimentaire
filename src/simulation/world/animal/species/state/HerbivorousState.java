package simulation.world.animal.species.state;

import simulation.constant.SimulationConstant;
import simulation.math.point.Point;
import simulation.math.probability.Experience;
import simulation.world.animal.group.Group;
import simulation.world.animal.group.HerbivorousGroup;
import simulation.world.animal.species.AbstractAnimal;

public class HerbivorousState extends AnimalState
{
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
	public HerbivorousState( Group groupReference,
		Point<Double> initialPosition,
		AbstractAnimal animal,
		int currentTurn )
	{
		// Parent constructor
		super( groupReference,
			initialPosition,
			animal,
			currentTurn );
	}
	
	/**
	 * Update
	 */
	@Override
	public void update( )
	{
		// Parent update
		super.update( );
		
		// If a plant group is aimed
		if( ((HerbivorousGroup)super.getGroup( )).getAimedPlantGroup( ) != null )
			// If the animal is in the plant group range, he'll move more less often
			if( ((HerbivorousGroup)super.getGroup( )).getAimedPlantGroup( ).getGroupRange( ).contains( new Point<Double>( super.getGroup( ).getGroupRange( ).getPosition( ).getX( ) + super.getPosition( ).getX( ),
				super.getGroup( ).getGroupRange( ).getPosition( ).getY( ) + super.getPosition( ).getY( ) ) ) )
			{
				// Experience
				Experience e = new Experience( SimulationConstant.HERBIVOROUS_STOP_MOVING_EATING_AREA_PROBABILITY );
				
				// Do the experience
				e.doExperience( );
				
				// He stops moving
				if( e.getEventID( ) == 0 )
					// Stop movement
					super.stopMoving( );
				
				// Eat
				//super
			}
	}
}
