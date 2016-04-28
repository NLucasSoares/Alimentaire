package simulation.constant;

import java.awt.Color;

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
	 * Default delay between frames
	 */
	public static final int DEFAULT_DELAY_BETWEEN_FRAMES = 500;
	
	/**
	 * Minimum group count on a map at start
	 */
	public final static int MINIMUM_GROUP_COUNT_MAP_START = 2;
	
	/**
	 * Maximum group count on a map at start
	 */
	public final static int MAXIMUM_GROUP_COUNT_MAP_START = 10;
	
	/**
	 * Initial minimum animals by group at world creation
	 */
	public final static int INITIAL_SPAWN_ANIMALS_BY_GROUP_MINIMUM = 2;
	
	/**
	 * Initial maximum animals by group at world creation
	 */
	public final static int INITIAL_SPAWN_ANIMALS_BY_GROUP_MAXIMUM = 5;
	
	/**
	 * The division factor for the animal group range calculation
	 */
	public final static int GROUP_RANGE_BY_FACTOR = 1500;
	/**
	 * Plant color
	 */
	public final static Color PLANT_COLOR = new Color( 0x2F00FF00,
		true );
	
	/**
	 * Herbivirous animal color
	 */
	public final static Color HERBIVOROUS_ANIMAL_COLOR = new Color( 0x2F00FFFF,
		true );
	
	/**
	 * Carnivorous animal color
	 */
	public final static Color CARNIVOROUS_ANIMAL_COLOR = new Color( 0x2FFF0000,
		true );
}
