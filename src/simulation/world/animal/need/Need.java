package simulation.world.animal.need;

/**
 * The needs of a particular species.
 * 
 * @author SOARES Lucas 
 */
public class Need
{
	/**
	 * The protein needed by specie
	 */
	private int protein;
	
	/**
	 * The water needed by specie
	 */
	private int water;

	/**
	 * Construct the need
	 * 
	 * @param protein
	 * 		The protein quantity needed by specie
	 * @param water
	 * 		The water needed by specie
	 */
	public Need( int protein,
		int water )
	{
		this.protein = protein;
		this.water = water;
	}

	/**
	 * @return the protein needed
	 */
	public int getProtein( )
	{
		return protein;
	}

	/**
	 * @return the water needed
	 */
	public int getWater( )
	{
		return water;
	}
}
