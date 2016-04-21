package simulation.world.environment.decomposer;

import java.util.ArrayList;
import java.util.Iterator;

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
	 */
	public int work( ArrayList<FieldResource> fieldResource )
	{
		// Total nitrogen eaten
		int proteinEaten = 0;
		
		// Eat
		for( Iterator<FieldResource> it = fieldResource.iterator( ); it.hasNext( ); )
		{
			// Get resource
			FieldResource fr = it.next( );
			
			// Consume
			if( fr.getProteinQuantity( ) - PROTEIN_EATEN <= 0 )
				it.remove( );
			else
			{
				// Eat
				try
				{
					fr.consumeProtein( PROTEIN_EATEN );
				}
				catch( NoMoreResourceException e )
				{
					continue;
				}
				
				// Increase total
				proteinEaten += PROTEIN_EATEN;
			}
		}
		
		// Calculate generated nitrogen
		return proteinEaten / 2;
	}
}
