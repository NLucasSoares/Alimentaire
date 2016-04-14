package simulation.world.environment.biome.resource;

/**
 * The basis resource used in simulation
 *
 * @author SOARES Lucas
 */
public class ResourceBasis
{
	/**
	 * A quantity of nitrogen
	 */
	private int nitrogenQuantity;
	
	/**
	 * A quantity of protein
	 */
	private int proteinQuantity;
	
	/**
	 * Construct the resource
	 * 
	 * @param nitrogenQuantity
	 * 		The quantity of nitrogen at start
	 * @param proteinQuantity
	 * 		The quantity of protein at start
	 */
	public ResourceBasis( int nitrogenQuantity,
		int proteinQuantity )
	{
		// Save
		this.nitrogenQuantity = nitrogenQuantity;
		this.proteinQuantity = proteinQuantity;
	}

	/**
	 * @return the nitrogen quantity
	 */
	public int getNitrogenQuantity( )
	{
		return nitrogenQuantity;
	}
	
	/**
	 * @return the protein quantity
	 */
	public int getProteinQuantity( )
	{
		return proteinQuantity;
	}

	/**
	 * Add nitrogen
	 * 
	 * @param nitrogen
	 * 		the nitrogen to add
	 */
	public void addNitrogen( int nitrogen )
	{
		this.nitrogenQuantity += nitrogen;
	}
	
	/**
	 * Consume nitrogen
	 * 
	 * @param nitrogen
	 * 		The nitrogen to consume
	 */
	public void consumeNitrogen( int nitrogen )
	{
		this.nitrogenQuantity -= nitrogen;
	}
	
	/**
	 * Add protein
	 * 
	 * @param protein
	 * 		the protein to add
	 */
	public void addProtein( int protein )
	{
		this.proteinQuantity += protein;
	}
	
	/**
	 * Consume protein
	 * 
	 * @param protein
	 * 		The protein to consume
	 */
	public void consumeProtein( int protein )
	{
		this.proteinQuantity -= protein;
	}
}
