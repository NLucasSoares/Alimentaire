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
	 */
	public ResourceState( int nitrogenQuantity,
		int proteinQuantity )
	{
		super( nitrogenQuantity,
			proteinQuantity );
	}
}
