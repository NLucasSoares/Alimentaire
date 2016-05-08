package simulation.world.animal.species.state;

import simulation.constant.SimulationConstant;
import simulation.world.animal.need.Need;

/**
 * How health of an animal actually is
 * 
 * @author SOARES Lucas 
 */
public class HealthState
{
	/**
	 * Callback to the defined needs of the animal
	 */
	private Need needDefinition;
	
	/**
	 * Current amount in protein
	 */
	private double protein;
	
	/**
	 * Good health round count
	 */
	private int goodHealthRoundCount;
	
	/**
	 * Health point
	 */
	private int healthPoint;
	
	/**
	 * Decrease health controller
	 */
	private int healthDecreaseController;
	
	/**
	 * Protein consumtion
	 */
	private int proteinConsumption;
	
	/**
	 * Is dead
	 */
	private boolean isDead;
	

	/**
	 * Construct the state of needs
	 * 
	 * @param need
	 * 		Callback to defined needs of animal
	 */
	public HealthState( Need needDefinition )
	{
		// Save
		this.needDefinition = needDefinition;
		
		// Init
		this.protein = this.needDefinition.getProtein( ) / simulation.math.probability.Operation.random( 1.5d,
			2.5d );
		this.healthPoint = SimulationConstant.MAXIMUM_ANIMAL_HEALTH_POINT;
		this.proteinConsumption = 0;
		this.goodHealthRoundCount = 0;
		this.isDead = false;
	}

	/**
	 * @return the protein eaten
	 */
	public double getProtein( )
	{
		return protein;
	}
	
	/**
	 * @return health point
	 */
	public int getHealthPoint( )
	{
		return this.healthPoint;
	}
	
	/**
	 * @return is alive?
	 */
	public boolean isAlive( )
	{
		return !this.isDead;
	}
	
	/**
	 * Add consumtion of protein
	 * 
	 * @param protein
	 * 		Protein consumed by an action
	 */
	public void addProteinConsumption( int protein )
	{
		this.proteinConsumption += protein;
	}
	
	/**
	 * Kill
	 */
	public void kill( )
	{
		this.isDead = true;
	}
	
	/**
	 * Eat
	 * 
	 * @param leavesCount
	 * 		The count of leaves eaten
	 */
	public void eat( int leavesCount )
	{
		// Increase protein quantity
		this.protein += SimulationConstant.PROTEIN_BY_LEAF * leavesCount;
	}
	
	/**
	 * Update the state
	 */
	public void update( )
	{
		// Reduce protein quantity
		this.protein -= this.proteinConsumption;
		
		// Reset the protein consumption gesture
		this.proteinConsumption = 0;
		
		// If needs are bad, loss of life
		if( this.protein <= 0 )
		{
			// Set to zero
			this.protein = 0;
			
			// Reduce health point
			if( this.healthDecreaseController < SimulationConstant.TURN_BEFORE_LOSING_HEALTH )
				this.healthDecreaseController++;
			else
			{
				// Health point decrease
				this.healthPoint--;
				
				// Controller reset
				this.healthDecreaseController = 0;
			}
		}
		// Good nutrition
		else
		{
			// Reset controller
			this.healthDecreaseController = 0;
			
			// Increase good health round counter
			if( this.goodHealthRoundCount < SimulationConstant.ROUND_GOOD_SHAPE_BEFORE_GAINING_HEALTH_POINT_ANIMAL )
				this.goodHealthRoundCount++;
			else
			{
				// Reset counter
				this.goodHealthRoundCount = 0;
				
				// Increase health point
				if( this.healthPoint < SimulationConstant.MAXIMUM_ANIMAL_HEALTH_POINT )
					this.healthPoint++;
			}
		}
		
		// Check if dead
		if( this.healthPoint <= 0 )
			this.isDead = true;
	}
}