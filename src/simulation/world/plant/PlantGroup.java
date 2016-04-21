package simulation.world.plant;

import simulation.math.point.Point;
import simulation.world.environment.biome.resource.NoMoreResourceException;
import simulation.world.environment.biome.resource.state.ResourceState;
import simulation.world.plant.need.Need;
import simulation.world.plant.need.state.NeedState;

/**
 * Plant Group
 * 
 * @author CAMPS Aurèle
 */
public class PlantGroup
{
	/**
	 * The number of leaves for one stage in plant group
	 */
	private static final int LEAVES_BY_STAGE = 5;
	
	/**
	 * Maximum stage for one plant group
	 */
	private static final int MAXIMUM_STAGES = 30;

	/**
	 * The amount of leaves of a plant group
	 */
	private int leaves;

	/**
	 * The needs state of a plant group
	 */
	private NeedState needsState;

	/**
	 * The needs definition of a plant group
	 */
	private Need needsDefinition;

	/**
	 * The diameter of the plant group
	 */
	private int diameter;
	
	/**
	 * Position
	 */
	private Point<Integer> position;

	/**
	 * Construct the plant group
	 */
	public PlantGroup( Point<Integer> position,
		Need needsDefinition )
	{
		// Init
		this.leaves = 1;
		this.needsState = new NeedState( );
		
		// Save
		this.position = position;
		this.needsDefinition = needsDefinition;
		
		// Calculate
			// Diameter
				this.diameter = this.calculateDiameter( );
	}
	
	/**
	 * Calculate the diameter
	 * 
	 * @return the diameter
	 */
	private int calculateDiameter( )
	{
		// Result
		int result = ( this.leaves / LEAVES_BY_STAGE ) + 1;
		
		// Return
		return ( result >= MAXIMUM_STAGES ) ? MAXIMUM_STAGES : result;
	}

	/**
	 * @return the leaves
	 */
	public int getLeaves( )
	{
		return leaves;
	}

	/**
	 * @return the needsState
	 */
	public NeedState getNeedsState( )
	{
		return needsState;
	}

	/**
	 * @return the diameter
	 */
	public int getDiameter( )
	{
		return diameter;
	}
	
	/**
	 * @return the position
	 */
	public Point<Integer> getPosition( )
	{
		return position;
	}
	
	/**
	 * Update the plant group
	 * 
	 * @param resourceState
	 * 		The resources state
	 */
	public void update( ResourceState resourceState )
	{
		// What to be eaten (nitrogen)
		double nitrogenToBeConsume = this.needsDefinition.getNitrogen( ) / ( ( (double)( MAXIMUM_STAGES * LEAVES_BY_STAGE ) - (double)this.leaves ) + 1.0d );
		
		// Consume
		try
		{
			// Consume
			resourceState.consumeNitrogen( nitrogenToBeConsume );

			// One more leaf
			if( this.leaves < MAXIMUM_STAGES * LEAVES_BY_STAGE )
				this.leaves++;
		}
		catch( NoMoreResourceException e )
		{
			this.leaves--;
		}
		
		// Calculate diameter
		this.diameter = this.calculateDiameter( );
	}
}