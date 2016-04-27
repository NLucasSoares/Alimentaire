package simulation.math.angle;

import simulation.math.point.Point;
import simulation.time.Time;

public class AngleMovement
{	
	/**
	 * The last update time
	 */
	private long lastUpdate;
	
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
	 * Is time dependent?
	 */
	private boolean isTimeDependent;
	
	/**
	 * Construct the angle movement
	 * 
	 * @param speed
	 * 		The speed for movement
	 */
	public AngleMovement( double speed,
		boolean isTimeDependent )
	{
		this( speed,
			new Point<Double>( 0.0d,
				0.0d ),
			isTimeDependent );
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
		Point<Double> position,
		boolean isTimeDependent )
	{
		// Save
		this.speed = speed;
		this.isTimeDependent = isTimeDependent;
		
		// Init
		this.angle = 0;
		this.lastUpdate = 0;
		this.position = position;
	}
	
	/**
	 * Set the time dependence
	 * 
	 * @param isTimeDependent
	 *		The new time dependent state
	 */
	public void setIsTimeDependent( boolean isTimeDependent )
	{
		this.isTimeDependent = isTimeDependent;
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
	 * Update the position according to specifications
	 */	
	public void update( )
	{		
		// Si le temps n'a pas encore été initialisé
		if( this.lastUpdate == 0 )
			this.lastUpdate = Time.getTicks( );

		// Current time
		long currentTime = Time.getTicks( );

		// Time factor
		double timeFactor = ( this.isTimeDependent ? ( (double)currentTime - (double)this.lastUpdate ) : 1.0d );
		
		// New position
		Point<Double> newPosition = new Point<Double>( this.position.getX( ) - Math.sin( ( this.angle * Math.PI ) / 180.0d ) * this.speed * timeFactor,
			this.position.getY( ) - Math.cos( ( this.angle * Math.PI ) / 180.0d ) * this.speed * timeFactor );
		
		// Save
		this.lastUpdate = currentTime;
		this.position = newPosition;
	}
	
}
