package simulation.math.probability;

public class ParameterizedExperience<T> extends Experience
{
	/**
	 * The event parameters
	 */
	private T[ ] eventParameters;
	
	/**
	 * Construct the parameterized experience
	 * 
	 * @param probability
	 * 		The probability of each event
	 * @param eventParameters
	 * 		The parameters of an event
	 */
	public ParameterizedExperience( int[ ] probability,
		T[ ] eventParameters )
	{
		// Parent constructor
		super( probability );
		
		// Save
		this.eventParameters = eventParameters;
	}
	
	/**
	 * @return the event's parameter
	 */
	public T getEventParameter( )
	{
		return this.eventParameters[ super.getEventID( ) ];
	}		
}
