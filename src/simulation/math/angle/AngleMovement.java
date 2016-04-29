package simulation.math.angle;

import simulation.math.point.Point;

public class AngleMovement
{		
	/**
	 * The current position
	 */
	private Point<Double> position;
	
	/**
	 * Current angle for movement
	 */
	private double angle;
	
	/**
	 * Speed for movement
	 */
	private double speed;
	
	/**
	 * Is moving?
	 */
	private boolean isMoving;
	
	/**
	 * Construct the angle movement
	 * 
	 * @param speed
	 * 		The speed for movement
	 */
	public AngleMovement( double speed )
	{
		this( speed,
			new Point<Double>( 0.0d,
				0.0d ) );
	}
	
	/**
	 * Construct the angle movement
	 * 
	 * @param speed
	 * 		The speed for movement
	 * @param position
	 * 		The initial position
	 */
	public AngleMovement( double speed,
		Point<Double> position )
	{
		// Save
		this.speed = speed;
		
		// Init
		this.angle = 0;
		this.position = position;
		this.isMoving = false;
	}
	
	/**
	 * Set the angle
	 * 
	 * @param angle
	 * 		The angle to set
	 */
	public void setAngle( double angle )
	{
		this.angle = angle;
	}
	
	/**
	 * Set the position
	 * 
	 * @param position
	 * 		The position to set
	 */
	public void setPosition( Point<Double> position )
	{
		this.position = position;
	}
	
	/**
	 * @return the position
	 */
	public Point<Double> getPosition( )
	{
		return this.position;
	}
	
	/**
	 * @return the angle of movement
	 */
	public double getAngle( )
	{
		return this.angle;
	}
	
	/**
	 * Aim a position
	 * 
	 * @param x
	 * 		The x coordinate to aim
	 * @param y
	 * 		The y coordinate to aim
	 */
	public void aimPosition( double x,
		double y )
	{
		this.aimPosition( new Point<Double>( x,
			y ) );
	}
	
	/**
	 * Aim a position
	 * 
	 * @param position
	 * 		The position to aim
	 */
	public void aimPosition( Point<Double> position )
	{
		// Aim
		this.angle = Operation.find( this.position.getX( ),
			this.position.getY( ),
			position.getX( ),
			position.getY( ) );
	}
	
	/**
	 * Start moving
	 */
	public void startMoving(  )
	{
		// Is now moving
		this.isMoving = true;
	}
	
	/**
	 * Stop moving
	 */
	public void stopMoving( )
	{
		// Stop movement
		this.isMoving = false;
	}
	
	/**
	 * Update the position according to specifications
	 */	
	public void update( )
	{
		// Is it moving?
		if( this.isMoving )
			// Save
			this.position = this.calculateFuturePosition( );
	}
	
	/**
	 * Get future position
	 */
	public Point<Double> calculateFuturePosition( )
	{
		return new Point<Double>( this.position.getX( ) + Math.sin( ( this.angle * Math.PI ) / 180.0d ) * this.speed,
			this.position.getY( ) - Math.cos( ( this.angle * Math.PI ) / 180.0d ) * this.speed );
	}
}
