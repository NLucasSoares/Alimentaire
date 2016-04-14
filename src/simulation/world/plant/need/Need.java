package simulation.world.plant.need;

/**
 * Need
 * 
 * Define the amount of resources needed by a plant need to survive.
 * 
 * @author acamps
 */
public class Need
{
	/**
	 * The amount of water needed.
	 */
	private int water;

	/**
	 * The amount of carbon needed.
	 */
	private int carbon;

	/**
	 * The amount of nitrogen needed.
	 */
	private int nitrogen;

	/**
	 * The amount of oxygen needed.
	 */
	private int oxygen;

	/**
	 * The amount of sunlight needed.
	 */
	private int sun;

	/**
	 * @return the water
	 */
	public int getWater()
	{
		return water;
	}

	/**
	 * @return the carbon
	 */
	public int getCarbon()
	{
		return carbon;
	}

	/**
	 * @return the nitrogen
	 */
	public int getNitrogen()
	{
		return nitrogen;
	}

	/**
	 * @return the oxygen
	 */
	public int getOxygen()
	{
		return oxygen;
	}

	/**
	 * @return the sun
	 */
	public int getSun()
	{
		return sun;
	}
}
