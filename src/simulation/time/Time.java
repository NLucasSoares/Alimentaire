package simulation.time;

/**
 *	Time gesture
 *
 *	@author
 *		Lucas Soares
 */
public class Time
{
	/**
	 * The system's ticks at the program start
	 */
	private static final long START_TICKS = System.currentTimeMillis( );
	
	/**
	 * Get the current ticks of the simulation
	 */
	public static long getTicks( )
	{
		return System.currentTimeMillis( ) - Time.START_TICKS;
	}
}

