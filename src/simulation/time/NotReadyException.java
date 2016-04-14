package simulation.time;

/**
 * 	An object isn't ready because
 * 	delay time didn't passed
 */
public class NotReadyException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7393559887831167991L;
	
	/**
	 * Construct the exception
	 */
	public NotReadyException( String message )
	{
		super( message );
	}
}
