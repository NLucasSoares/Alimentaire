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
	
	// Field resource
	public static final int FIELD_RESOURCE_SIZE = 2;
	
	// Animal
	/**
	 * The maximum health point of an animal
	 */
	public static final int MAXIMUM_ANIMAL_HEALTH_POINT = 100;
	
	/**
	 * Maximum group on one map
	 */
	public static final int MAXIMUM_GROUP_BY_MAP = 50;
	
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
	
	/**
	 * Round count in good shape before regaining
	 * health points
	 */
	public static final int ROUND_GOOD_SHAPE_BEFORE_GAINING_HEALTH_POINT_ANIMAL = 15;
	
	/**
	 * Life time reduction factor min
	 */
	public static final double ANIMAL_LIFE_TIME_REDUCTION_FACTOR_MIN = 0.9d;
	
	/**
	 * Life time reduction factor max
	 */
	public static final double ANIMAL_LIFE_TIME_REDUCTION_FACTOR_MAX = 1.2d;
	
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
	 * Minimum leaves dying when not enough food
	 */
	public static final int MINIMUM_LEAF_DYING_NOT_FOOD = 1;
	
	/**
	 * Maximum leaves dying when not enough food
	 */
	public static final int MAXIMUM_LEAF_DYING_NOT_FOOD = 5;
	
	/**
	 * Decomposable mass in one leaf
	 */
	public static final int LEAF_DECOMPOSABLE_MASS = 20;
	
	/**
	 * Maximum plant group on one single map
	 */
	public static final int MAXIMUM_PLANT_GROUP_COUNT_MAP = 3;
	
	/**
	 * Minimum plant group count at start
	 */
	public static final int MINIMUM_PLANT_GROUP_COUNT_MAP_START = 1;
	
	/**
	 * Maximum plant group count at start
	 */
	public static final int MAXIMUM_PLANT_GROUP_COUNT_MAP_START = MAXIMUM_PLANT_GROUP_COUNT_MAP;
	
	/**
	 * Protein given by one leaf
	 */
	public static final int PROTEIN_BY_LEAF = 10;
	
	/**
	 * Maximum diameter for plant group
	 */
	public static final int MAXIMUM_PLANT_GROUP_DIAMETER = 5;
	
	// Decomposer
	/**
	 * Decomposer efficacity factor
	 */
	public static final double DECOMPOSER_EFFICACITY_FACTOR = 2.0d;
	
	/**
	 * Minimum nitrogen generated on each turn
	 */
	public static final double MINIMUM_NITROGEN_ADD_EACH_TURN = 0.01d;
	
	// Simulation
	/**
	 * Default delay between frames
	 */
	public static final int DEFAULT_DELAY_BETWEEN_FRAMES = 500;
	
	/**
	 * Minimum animal group count on a map at start
	 */
	public final static int MINIMUM_ANIMAL_GROUP_COUNT_MAP_START = 2;
	
	/**
	 * Maximum animal group count on a map at start
	 */
	public final static int MAXIMUM_ANIMAL_GROUP_COUNT_MAP_START = 8;
	
	/**
	 * Minimum group count for each type on one map
	 */
	public final static int MINIMUM_GROUP_COUNT_MAP = 3;
	
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
	public final static int POSITION_AIMING_PRECISION = 10;
	
	/**
	 * The stock capacity which much be reached to reproduce (%)
	 */
	public final static int FLOOR_FOR_PROTEIN_STOCK_REPRODUCING = 70;
	
	/**
	 * The stock capacity which set the end of search when
	 * not found mate to reproduce (%)
	 */
	public final static int FLOOR_FOR_PROTEIN_STOCK_STOP_REPRODUCING = 60;
	
	// COLOR
	/**
	 * Plant color
	 */
	public final static Color PLANT_COLOR = new Color( 0x2F00FF00,
		true );
	
	/**
	 * Field resource color
	 */
	public final static Color FIELD_RESOURCE_COLOR = new Color( 0xFFFF0000,
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
	
	/**
	 * Probability for the herbivorous group to stop on a
	 * plant group center
	 */
	public final static int[ ] HERBIVOROUS_STOP_MOVING_PLANT_CENTER_PROBABILITY = {
		1,
		80
	};
	
	/**
	 * Probability for carnivorous to start hunting herbivorous
	 */
	public final static int[ ] CARNIVOROUS_START_HUNTING_HERBIVOROUS_PROBABILITY = {
		1,
		30
	};
}
