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
	 * Health point
	 */
	private int healthPoint;
	

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
		this.protein = this.needDefinition.getProtein( ) / 2.0d;
		this.healthPoint = SimulationConstant.MAXIMUM_ANIMAL_HEALTH_POINT;
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
		return ( this.healthPoint > 0 );
	}
	
	/**
	 * Update the state
	 */
	public void update( )
	{
		// If needs are bad, loss of life
		if( this.protein == 0 )
			this.healthPoint--;
	}
}