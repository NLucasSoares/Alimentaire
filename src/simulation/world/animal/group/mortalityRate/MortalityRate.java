package simulation.world.animal.group.mortalityRate;

/**
 * The gesture for death rate
 * 
 * @author SOARES Lucas 
 */
public class MortalityRate
{
	/**
	 * The last count of animals
	 */
	private int[ ] lastAnimalCount;
	
	public MortalityRate( )
	{
		// Init
		this.lastAnimalCount = new int [ simulation.constant.SimulationConstant.LAST_TURNS ];
	}
	
	/**
	 * @return the historical of animal count
	 */
	public int[ ] getLastAnimalCount( )
	{
		return lastAnimalCount;
	}
}
