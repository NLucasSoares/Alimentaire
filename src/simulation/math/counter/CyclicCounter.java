package simulation.math.counter;

import simulation.constant.Direction;

/**
 * A way to count
 * 
 * @author
 * 		Lucas SOARES
 */
public class CyclicCounter
{
	/**
	 * The actual state of the counter
	 */
	private int count;
	
	/**
	 * The limits of the counter
	 */
	private int leftLimit;
	private int rightLimit;
	
	/**
	 * The direction of the counter
	 * (from l to r or r to l?)
	 */
	private Direction direction;
	
	/**
	 * Construct the counter
	 * 
	 * @param initialCount
	 * 		The initial count
	 * @param limit
	 * 		The limit of the counter
	 * @param initialDirection
	 * 		The initial direction of the counter
	 */
	public CyclicCounter( int initialCount,
		int leftLimit,
		int rightLimit,
		Direction initialDirection )
	{
		// Save
		this.count = initialCount;
		this.leftLimit = leftLimit;
		this.rightLimit = rightLimit;
		this.direction = initialDirection;
	}
	
	/**
	 * @return is at the limit
	 */
	public boolean isAtLimit( )
	{
		switch( this.direction )
		{
			case DIRECTION_UP:
			case DIRECTION_RIGHT:
				return this.count >= this.rightLimit;
			
			case DIRECTION_DOWN:
			case DIRECTION_LEFT:
				return this.count <= this.leftLimit;
				
			default:
				return true;
		}
	}
	
	/**
	 * @return the left limit
	 */
	public int getLeftLimit( )
	{
		return this.leftLimit;
	}

	/**
	 * @param limit the left limit to set
	 */
	public void setLeftLimit( int limit )
	{
		this.leftLimit = limit;
	}
	
	/**
	 * @return the right limit
	 */
	public int getRightLimit( )
	{
		return this.rightLimit;
	}

	/**
	 * @param limit the right limit to set
	 */
	public void setRightLimit( int limit )
	{
		this.rightLimit = limit;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection( )
	{
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection( Direction direction )
	{
		this.direction = direction;
	}

	/**
	 * @return the count
	 */
	public int getCount( )
	{
		return count;
	}

	/**
	 * Update the counter
	 */
	public void update( )
	{
		// Check state
		if( this.isAtLimit( ) )
			return;
		
		// Update
		switch( this.direction )
		{
			case DIRECTION_UP:
			case DIRECTION_RIGHT:
				this.count++;
				break;
				
			case DIRECTION_DOWN:
			case DIRECTION_LEFT:
				this.count--;
				break;
				
			default:
				break;
		}
	}
}
