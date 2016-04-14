package simulation.world.animal.need.state;

import simulation.constant.SimulationConstant;
import simulation.world.animal.need.Need;

/**
 * How needs of an animal actually are
 * 
 * @author SOARES Lucas 
 */
public class NeedState
{
	/**
	 * Callback to the defined needs of the animal
	 */
	private Need need;
	
	/**
	 * Current amount in protein
	 */
	private int protein;
	
	/**
	 * Current amount in water
	 */
	private int water;

	/**
	 * Construct the state of needs
	 * 
	 * @param need
	 * 		Callback to defined needs of animal
	 */
	public NeedState( Need need )
	{
		// Save
		this.need = need;
		
		// Init
		this.protein = 0;
		this.water = 0;
	}

	/**
	 * @return the protein eaten
	 */
	public int getProtein( )
	{
		return protein;
	}

	/**
	 * @return the water drunk
	 */
	public int getWater( )
	{
		return water;
	}
}