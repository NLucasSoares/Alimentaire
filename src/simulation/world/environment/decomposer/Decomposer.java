package simulation.world.environment.decomposer;

import java.util.Iterator;

import simulation.constant.SimulationConstant;
import simulation.world.environment.MapState;
import simulation.world.environment.biome.resource.NoMoreResourceException;
import simulation.world.environment.biome.resource.field.FieldResource;

/**
 * The decomposer on one map
 * 
 * @author SOARES Lucas
 */
public class Decomposer
{	
	/**
	 * Quantity of protein eaten each turn
	 * on field resources
	 */
	private static final int PROTEIN_EATEN = 100;
	
	/**
	 * Work
	 * 
	 * @param mapState
	 * 		The state of the map
	 */
	public double work( MapState mapState )
	{
		// Total nitrogen eaten
		double proteinEaten = 0;
		
		// Eat
		for( Iterator<FieldResource> it = mapState.getFieldResource( ); it.hasNext( ); )
		{
			// Get resource
			FieldResource fr = it.next( );
			
			// Try to eat
			try
			{
				fr.consumeProtein( Decomposer.PROTEIN_EATEN );
			}
			// Field resource is empty
			catch( NoMoreResourceException e )
			{
				// Remove field resource
				it.remove( );
				
				// Go to the next one
				continue;
			}
			
			// Increase total
			proteinEaten += Decomposer.PROTEIN_EATEN;
		}
		
		// Calculate generated nitrogen
		return proteinEaten / SimulationConstant.DECOMPOSER_EFFICACITY_FACTOR;
	}
}
