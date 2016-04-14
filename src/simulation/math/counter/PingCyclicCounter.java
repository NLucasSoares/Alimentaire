package simulation.math.counter;

import simulation.constant.Direction;

/**
 * This counter permit to go in other
 * way automatically when limit is
 * reached
 * 
 * @author
 * 		Lucas SOARES
 */
public class PingCyclicCounter extends CyclicCounter
{
	/**
	 * Construct the counter
	 * 
	 * @param initialCount
	 * 		The initial count
	 * @param leftLimit
	 * 		The left limit of the counter
	 * @param rightLimit
	 * 		The right limit of the counter
	 * @param initialDirection
	 * 		The initial direction of the counter
	 */
	public PingCyclicCounter( int initialCount,
		int leftLimit,
		int rightLimit,
		Direction initialDirection )
	{
		super( initialCount,
			leftLimit,
			rightLimit,
			initialDirection );
	}
	
	/**
	 * Update the counter
	 */
	public void update( )
	{
		// Check for limit
		if( this.isAtLimit( ) )
			switch( this.getDirection( ) )
			{
				case DIRECTION_UP:
				case DIRECTION_RIGHT:
					this.setDirection( Direction.DIRECTION_LEFT );
					break;
					
				case DIRECTION_LEFT:
				case DIRECTION_DOWN:
					this.setDirection( Direction.DIRECTION_RIGHT );
					break;
					
				default:
					break;
			}
		
		// Update
		super.update( );
	}
}
