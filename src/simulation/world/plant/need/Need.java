package simulation.world.plant.need;

/**
 * Define the amount of resources needed by a plant need to survive.
 * 
 * @author CAMPS Aurèle
 */
public class Need
{
	/**
	 * The amount of water needed.
	 */
	private double water;

	/**
	 * The amount of nitrogen needed.
	 */
	private double nitrogen;

	/**
	 * The amount of sunlight needed.
	 */
	private double sun;

	/**
	 * Construct the need
	 * 
	 * @param water
	 * 		The water needed
	 * @param nitrogen
	 * 		The nitrogen needed
	 * @param sun
	 * 		The sun needed
	 */
	public Need( double water,
		double nitrogen,
		double sun )
	{
		// Save
		this.nitrogen = nitrogen;
		this.water = water;
		this.sun = sun;
	}
	
	/**
	 * @return the water
	 */
	public double getWater()
	{
		return water;
	}

	/**
	 * @return the nitrogen
	 */
	public double getNitrogen()
	{
		return nitrogen;
	}

	/**
	 * @return the sun
	 */
	public double getSun()
	{
		return sun;
	}
}
