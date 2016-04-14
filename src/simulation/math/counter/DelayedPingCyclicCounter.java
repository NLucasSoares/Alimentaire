package simulation.math.counter;

import simulation.time.Delayed;
import simulation.time.Time;

public class DelayedPingCyclicCounter extends PingCyclicCounter implements Delayed
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
	 * Construct the delayed ping cyclic counter
	 * 
	 * @param initialCount
	 * 		The initial count
	 * @param limit
	 * 		The limit of the counter
	 * @param initialDirection
	 * 		The initial direction of the counter
	 * @param delay
	 * 		The delay (ms) between two updates
	 */
	public DelayedPingCyclicCounter( int initialCount,
		int leftLimit,
		int rightLimit,
		simulation.constant.Direction initialDirection,
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
		// Check for time
		if( Time.getTicks( ) - this.lastUpdate < this.delay )
			return;
		
		// Update
		super.update( );
		
		// Save last update time
		this.lastUpdate = Time.getTicks( );
	}

}
