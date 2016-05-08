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
	 * Decomposable mass eaten on each turn
	 */
	private static final int DECOMPOSABLE_MASS_EATEN = 5;
	
	/**
	 * Plant mass to decompose
	 */
	private double plantMassToDecompose;
	
	/**
	 * Decomposer health
	 */
	private double health;
	
	/**
	 * Construct decomposer
	 */
	public Decomposer( )
	{
		// Init
		this.plantMassToDecompose = 0;
		this.health = 100;
	}
	
	/**
	 * Add plant mass
	 * 
	 * @param plantMass
	 *		The plant mass to decompose
	 */
	public void addPlantMass( int leafCount )
	{
		this.plantMassToDecompose += leafCount * SimulationConstant.LEAF_DECOMPOSABLE_MASS;
	}
	
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
			
			// Protein eaten
			double proteinToEat = Decomposer.PROTEIN_EATEN * ( this.health / 100.0d );
			
			// Try to eat
			try
			{
				fr.consumeProtein( proteinToEat );
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
			proteinEaten += proteinToEat;
		}
		
		// Nitrogen produced
		double nitrogenProduced = ( proteinEaten / SimulationConstant.DECOMPOSER_EFFICACITY_FACTOR ); // TODO Depends of the health
		
		// No more plant mass
		if( this.plantMassToDecompose - Decomposer.DECOMPOSABLE_MASS_EATEN < 0 )
			this.plantMassToDecompose = 0;
		// Eat plant mass
		else
		{
			// Reduce
			this.plantMassToDecompose -= Decomposer.DECOMPOSABLE_MASS_EATEN;
			
			// Add nitrogen			
			nitrogenProduced += (double)Decomposer.DECOMPOSABLE_MASS_EATEN / (double)SimulationConstant.DECOMPOSER_EFFICACITY_FACTOR;
		}
		
		// Calculate generated nitrogen (with a constant minimum gain, to be sure it stay stable)
		return nitrogenProduced <= SimulationConstant.MINIMUM_NITROGEN_ADD_EACH_TURN ?
			SimulationConstant.MINIMUM_NITROGEN_ADD_EACH_TURN
			: nitrogenProduced;
	}
}
