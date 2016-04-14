package simulation.math.probability;

import java.security.InvalidParameterException;

import simulation.math.Operation;

/**
 * A random experience
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
	 * Event id
	 */
	private int eventID;

	/**
	 * Construct the experience
	 * 
	 * @param probability
	 * 		The probability for each event
	 */
	public Experience( int[ ] probability ) throws InvalidParameterException
	{
		// Check for validity
		if( Operation.sumArray( probability ) != 100 )
			throw new InvalidParameterException( );
		
		// Save the probability
		this.probability = probability;
		
		// Init
		this.eventID = 0;
	}
	
	/**
	 * Do the experience
	 */
	public void doExperience( )
	{
		// Pick-up random value
		int randValue = (int)( Math.random( ) * 100.0d );
		
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
	 * Get the event id
	 */
	public int getEventID( )
	{
		return this.eventID;
	}
}
