package simulation.constant;

import java.awt.Color;

/**
 * The constants used by the simulation
 * 
 * @author SOARES Lucas
 */
public class SimulationConstant
{
	// VALUES
	/**
	 * Historical depth
	 */
	public final static int LAST_TURNS = 5;
	
	// Animal
	/**
	 * The maximum health point of an animal
	 */
	public static final int MAXIMUM_ANIMAL_HEALTH_POINT = 100;
	
	/**
	 * Turn count before losing life due to low feeding
	 */
	public static final int TURN_BEFORE_LOSING_HEALTH = 5;
	
	/**
	 * Turn taken for plant feeding
	 * (this is a basis, which is modulate
	 * by animal agilty through
	 * calculateRoundTakenForLeafEating( )
	 * method from Herbivorous.
	 */
	public static final int TURN_TAKEN_HERBIVOROUS_PLANT_FEEDING = 0;
	
	// Plant group
	/**
	 * The number of leaves for one stage in plant group
	 */
	public static final int LEAVES_BY_STAGE = 20;
	
	/**
	 * Maximum stage for one plant group
	 */
	public static final int MAXIMUM_STAGES = 50;
	
	/**
	 * Minimum leaves growing in one turn (simulate the different leaf size, which
	 * require more or less resource)
	 */
	public static final int MINIMUM_LEAF_GROWING_ONE_TURN = 1;
	
	/**
	 * Maximum leaves growing in one turn (simulate the different leaf size, which
	 * require more or less resource)
	 */
	public static final int MAXIMUM_LEAF_GROWING_ONE_TURN = 3;
	
	/**
	 * Protein given by one leaf
	 */
	public static final int PROTEIN_BY_LEAF = 10;
	
	/**
	 * Maximum diameter for plant group
	 */
	public static final int MAXIMUM_PLANT_GROUP_DIAMETER = 30;
	
	// Decomposer
	/**
	 * Decomposer efficacity factor
	 */
	public static final double DECOMPOSER_EFFICACITY_FACTOR = 2.0d;
	
	// Simulation
	/**
	 * Default delay between frames
	 */
	public static final int DEFAULT_DELAY_BETWEEN_FRAMES = 500;
	
	/**
	 * Minimum group count on a map at start
	 */
	public final static int MINIMUM_GROUP_COUNT_MAP_START = 20;
	
	/**
	 * Maximum group count on a map at start
	 */
	public final static int MAXIMUM_GROUP_COUNT_MAP_START = 20;
	
	/**
	 * Initial minimum animals by group at world creation
	 */
	public final static int INITIAL_SPAWN_ANIMALS_BY_GROUP_MINIMUM = 2;
	
	/**
	 * Initial maximum animals by group at world creation
	 */
	public final static int INITIAL_SPAWN_ANIMALS_BY_GROUP_MAXIMUM = 5;
	
	// Group
	/**
	 * The division factor for the animal group range calculation
	 */
	public final static int GROUP_RANGE_BY_FACTOR = 1200;
	
	/**
	 * The precision for position aiming
	 * (more = less precision)
	 */
	public final static int POSITION_AIMING_PRECISION = 2;
	
	// COLOR
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
	
	// PROBABILITIES
	/**
	 * Probability for Plant Spawn
	 */
	public final static int[ ] PLANT_SPAWN_PROBABILITY = {
		1, // Probability on each turn to get plant spawn
		500 };
	
	/**
	 * Herbivorous group random move probability
	 */
	public final static int[ ] HERBIVOROUS_GROUP_RANDOM_MOVE_PROBABILITY = {
		1, // Probility to get a move
		80
	};
	
	/**
	 * Individual herbivorous random move probability
	 */
	public final static int[ ] HERBIVOROUS_INDIVIDUAL_RANDOM_MOVE_PROBABILITY = {
		1, // Probability on each inactive turn for herbivorous to get moving
		130
	};
	
	/**
	 * Probability for an herbivorous to stop moving when in an
	 * eating area
	 */
	public final static int[ ] HERBIVOROUS_STOP_MOVING_EATING_AREA_PROBABILITY = {
		1,
		100 };
}
