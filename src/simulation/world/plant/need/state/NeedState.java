package simulation.world.plant.need.state;

/**
 * Need State
 * 
 * Define what the plant has already absorbed to determine if it needs more
 * resources to survive.
 * 
 * @author acamps
 */
public class NeedState
{

	/**
	 * The amount of water absorbed by the plant.
	 */
	private int water;

	/**
	 * The amount of carbon absorbed by the plant.
	 */
	private int carbon;

	/**
	 * The amount of nitrogen absorbed by the plant.
	 */
	private int nitrogen;

	/**
	 * The amount of oxygen absorbed by the plant.
	 */
	private int oxygen;

	/**
	 * The amount of sunlight absorbed by the plant.
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
