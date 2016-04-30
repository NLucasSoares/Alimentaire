package simulation.math.probability;

import simulation.math.Operation;

/**
 * A random experience, based on an array of probabilities
 * for each event. When experience done, it returns the
 * event id which was randomly picked up.
 * 
 * @author Lucas SOARES
 */
public class Experience
{
	/**
	 * The probability of each event
	 */
	private int[ ] probability;
	
	/**
	 * Total of probabilities
	 */
	public int total;
	
	/**
	 * Event id
	 */
	private int eventID;

	/**
	 * Construct the experience
	 * 
	 * @param probability
	 * 		The probability for each event
	 */
	public Experience( int[ ] probability )
	{
		// Save the probability
		this.probability = probability;
		
		// Calculate total
		this.total = Operation.sumArray( probability );
		
		// Init
		this.eventID = 0;
	}
	
	/**
	 * Do the experience
	 */
	public void doExperience( )
	{
		// Pick-up random value
		int randValue = (int)( Math.random( ) * (double)this.total );
		
		// Current value
		double currentValue = 0;
		
		// Select the result
		int output = 0;
		for( ; output < probability.length; output++ )
			if( currentValue > randValue )
				break;
			else
				currentValue += this.probability[ output ];

		// Found event
		this.eventID = output - 1;
	}
	
	/**
	 * @return the event id
	 */
	public int getEventID( )
	{
		return this.eventID;
	}
	
	/**
	 * @return the total
	 */
	public int getTotal( )
	{
		return this.total;
	}
}
