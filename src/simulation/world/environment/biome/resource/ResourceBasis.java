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
	private double nitrogenQuantity;
	
	/**
	 * A quantity of protein
	 */
	private double proteinQuantity;
	
	/**
	 * Construct the resource
	 * 
	 * @param nitrogenQuantity
	 * 		The quantity of nitrogen at start
	 * @param proteinQuantity
	 * 		The quantity of protein at start
	 */
	public ResourceBasis( double nitrogenQuantity,
		double proteinQuantity )
	{
		// Save
		this.nitrogenQuantity = nitrogenQuantity;
		this.proteinQuantity = proteinQuantity;
	}

	/**
	 * @return the nitrogen quantity
	 */
	public double getNitrogenQuantity( )
	{
		return nitrogenQuantity;
	}
	
	/**
	 * @return the protein quantity
	 */
	public double getProteinQuantity( )
	{
		return proteinQuantity;
	}

	/**
	 * Add nitrogen
	 * 
	 * @param nitrogen
	 * 		the nitrogen to add
	 */
	public void addNitrogen( double nitrogen )
	{
		this.nitrogenQuantity += nitrogen;
	}
	
	/**
	 * Consume nitrogen
	 * 
	 * @param nitrogen
	 * 		The nitrogen to consume
	 */
	public void consumeNitrogen( double nitrogen ) throws NoMoreResourceException
	{
		if( this.nitrogenQuantity - nitrogen < 0 )
		{
			// To zero
			this.nitrogenQuantity = 0;
			
			// Exception
			throw new NoMoreResourceException( );
		}
		else
			this.nitrogenQuantity -= nitrogen;
	}
	
	/**
	 * Add protein
	 * 
	 * @param protein
	 * 		the protein to add
	 */
	public void addProtein( double protein )
	{
		this.proteinQuantity += protein;
	}
	
	/**
	 * Consume protein
	 * 
	 * @param protein
	 * 		The protein to consume
	 */
	public void consumeProtein( double protein ) throws NoMoreResourceException
	{
		if( this.proteinQuantity - protein < 0 )
		{
			// To zero
			this.proteinQuantity = 0;
			
			// Exception
			throw new NoMoreResourceException( );
		}
		else
			this.proteinQuantity -= protein;
	}
}
