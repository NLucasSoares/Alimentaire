package simulation.math.counter;

import simulation.constant.Direction;
import simulation.time.Delayed;
import simulation.time.Time;

public class DelayedCyclicCounter extends CyclicCounter implements Delayed
{
	/**
	 * Delay between updates
	 */
	private int delay;
	
	/**
	 * Last update
	 */
	private long lastUpdate;
	
	/**
	 * Construct the cyclic counter
	 * 
	 * @param initialCount
	 * 		The initial count
	 * @param leftLimit
	 * 		The left limit of the counter
	 * @param rightLimit
	 * 		The right limit of the counter
	 * @param initialDirection
	 * 		The initial direction of the counter
	 * @param delay
	 * 		The delay (ms) between two updates
	 */
	public DelayedCyclicCounter( int initialCount,
		int leftLimit,
		int rightLimit,
		Direction initialDirection,
		int delay )
	{
		// Parent constructor
		super( initialCount,
			leftLimit,
			rightLimit,
			initialDirection );
		
		// Save
		this.delay = delay;
		
		// Init
		this.lastUpdate = Time.getTicks( );
	}
	
	/**
	 * Update
	 */
	public void update( )
	{
		// Check delay
		if( Time.getTicks( ) - this.lastUpdate < this.delay )
			return;
		
		// Update
		super.update( );
		
		// Start delay...
		this.lastUpdate = Time.getTicks( );
	}

}
