package simulation.constant;

/**
 * The constants used by the simulation
 * 
 * @author SOARES Lucas
 */
public class SimulationConstant
{
	/**
	 * Historical depth
	 */
	public final static int LAST_TURNS = 5;
	
	/**
	 * Probability for Plant Spawn
	 */
	public final static int[ ] PLANT_SPAWN_PROBABILITY = {
		1, // Probability on each turn to get plant spawn
		99 };
	
	/**
	 * Minimum group count on a map at start
	 */
	public final static int MINIMUM_GROUP_COUNT_MAP_START = 2;
	
	/**
	 * Maximum group count on a map at start
	 */
	public final static int MAXIMUM_GROUP_COUNT_MAP_START = 10;
}
