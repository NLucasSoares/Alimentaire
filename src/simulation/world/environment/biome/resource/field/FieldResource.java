package simulation.world.environment.biome.resource.field;

import simulation.world.environment.biome.resource.ResourceBasis;

/**
 * The field resources are determined by water, protein, nitrogen,
 * 	carbon and more..
 * 
 * @author CAMPS Aurèle
 */

public class FieldResource extends ResourceBasis
{
	/**
	 * The name of the resource
	 */
	private String name;

	/**
	 * Construct the field resource
	 * 
	 * @param name
	 * @param protein
	 * @param nitrogen
	 */
	public FieldResource( String name,
		int proteinQuantity,
		int nitrogen )
	{
		// Parent constructor
		super( nitrogen,
			proteinQuantity );
		
		// Save
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName( )
	{
		return name;
	}
}
