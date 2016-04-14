package simulation.world.environment.biome.resource.state;

import simulation.world.environment.biome.resource.ResourceBasis;

/**
 * The resource state for one map
 * 
 * @author SOARES Lucas
 */
public class ResourceState extends ResourceBasis
{
	/**
	 * Construct the resource state
	 * 
	 * @param nitrogenQuantity
	 * 		The initial nitrogen quantity
	 * @param proteinQuantity
	 * 		The initial protein quantity
	 */
	public ResourceState( int nitrogenQuantity,
		int proteinQuantity )
	{
		super( nitrogenQuantity,
			proteinQuantity );
	}
}
