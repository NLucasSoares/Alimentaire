package simulation.math.circle;

import simulation.math.point.Point;

/**
 * A circle definition
 * 
 * To conform to java definition, the position
 * of the circle is considered center
 * 
 * @author SOARES Lucas
 */
public class Circle
{
	/**
	 * The position
	 */
	private Point<Double> position;
	
	/**
	 * Radius
	 */
	private double radius;
	
	/**
	 * Construct the circle
	 * 
	 * @param x
	 * 		The x coordinates
	 * @param y
	 * 		The y coordinates
	 * @param radius
	 * 		The radius of the circle
	 */
	public Circle( double x,
		double y,
		double radius )
	{
		this( new Point<Double>( x,
				y ),
			radius );
	}
	
	/**
	 * Construct the circle
	 * 
	 * @param position
	 * 		The position
	 * @param radius
	 * 		The radius
	 */
	public Circle( Point<Double> position,
		double radius )
	{
		// Save
		this.position = position;
		this.radius = radius;
	}
	
	/**
	 * @return the position
	 */
	public Point<Double> getPosition( )
	{
		return this.position;
	}
	
	/**
	 * @return the radius
	 */
	public double getRadius( )
	{
		return this.radius;
	}
	
	/**
	 * Set the position
	 * 
	 * @param position
	 * 		The new position
	 */
	public void setPosition( Point<Double> position )
	{
		// Save
		this.position = position;
	}
	
	/**
	 * Set the radius
	 * 
	 * @param radius
	 * 		The new radius
	 */
	public void setRadius( double radius )
	{
		// Save
		this.radius = radius;
	}
	
	/**
	 * Intersects with another circle?
	 * 
	 * @param circle
	 * 		The circle to be evaluated
	 */
	public boolean intersects( Circle circle )
	{
		return simulation.math.circle.Operation.intersects( new Point<Double>( circle.getPosition( ).getX( ),
				circle.getPosition( ).getY( )  ),
			circle.getRadius( ),
			new Point<Double>( this.position.getX( ),
					this.position.getY( ) ),
			this.radius );
	}
	
	/**
	 * Contains a point?
	 * 
	 * @param point
	 * 		The point to be evaluated
	 */
	public boolean contains( Point<Double> point )
	{
		return simulation.math.circle.Operation.contains( this.position,
			this.radius,
			point );
	}
}
