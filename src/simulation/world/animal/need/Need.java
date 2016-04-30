package simulation.world.animal.need;

/**
 * The needs of a particular species.
 * 
 * @author SOARES Lucas 
 */
public class Need
{
	/**
	 * The maximum protein a specie can stock
	 */
	private double protein;

	/**
	 * Construct the need
	 * 
	 * @param protein
	 * 		The maximum protein quantity a specie can stock
	 */
	public Need( double protein )
	{
		this.protein = protein;
	}

	/**
	 * @return the protein needed
	 */
	public double getProtein( )
	{
		return protein;
	}
}
