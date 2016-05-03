package simulation.world.animal.species.state;

import simulation.constant.SimulationConstant;
import simulation.math.point.Point;
import simulation.math.probability.Experience;
import simulation.world.animal.group.Group;
import simulation.world.animal.group.HerbivorousGroup;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.animal.species.Herbivorous;

public class HerbivorousState extends AnimalState
{
	/**
	 * Feeding controller counter
	 */
	private int feedingControllerCounter;
	
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
		
		// Init
		this.feedingControllerCounter = 0;
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
				// Random movement stop
					// Experience
						Experience e = new Experience( SimulationConstant.HERBIVOROUS_STOP_MOVING_EATING_AREA_PROBABILITY );
					// Do the experience
						e.doExperience( );
					// He stops moving
						if( e.getEventID( ) == 0 )
							super.stopMoving( );
				
				// Check leaves count
				if( ((HerbivorousGroup)super.getGroup( )).getAimedPlantGroup( ).getLeaves( ) > 0 )
				{
					// Modulate eating speed
					if( this.feedingControllerCounter >= ((Herbivorous)super.getAnimal( )).calculateRoundTakenForLeafEating( ) )
					{
						// Eat
						((HerbivorousGroup)super.getGroup( )).getAimedPlantGroup( ).beEaten( 1 );
							
						// Increase protein stock
						super.getHealthState( ).eat( 1 );
						
						// Reset controller
						this.feedingControllerCounter = 0;
					}
					else
						this.feedingControllerCounter++;
				}
				else
					this.feedingControllerCounter = 0;
			}
			else
				this.feedingControllerCounter = 0;
	}
}
