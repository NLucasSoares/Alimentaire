package simulation.gui.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import simulation.math.point.Point;

/**
 * A printable Circle
 * 
 * @author SOARES Lucas
 */
public class Circle extends simulation.math.circle.Circle
{
	/**
	 * The java Shape
	 */
	private Shape javaCircle;
	
	/**
	 * Color
	 */
	private Color color;
	
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
		// Parent constructor
		super( position,
			radius );
		
		// Set default color
		this.color = new Color( 0x00000000 );
		
		// Create shape
		this.updateShape( );
	}
	
	/**
	 * Set the position
	 * 
	 * @param position
	 * 		The position to set
	 */
	@Override
	public void setPosition( Point<Double> position )
	{
		// Parent job
		super.setPosition( position );
		
		// Update the shape
		this.updateShape( );
	}
	
	/**
	 * Set the radius
	 * 
	 * @param radius
	 * 		The radius to set
	 */
	@Override
	public void setRadius( double radius )
	{
		// Parent job
		super.setRadius( radius );
		
		// Update the shape
		this.updateShape( );
	}
	
	/**
	 * Set the color
	 * 
	 * @param color
	 * 		The color to set
	 */
	public void setColor( Color color )
	{
		this.color = color;
	}
	
	/**
	 * Update the shape
	 */
	public void updateShape( )
	{
		// Create the shape (the center of the shape is 0;0)
		this.javaCircle = new Ellipse2D.Double( super.getPosition( ).getX( ) - super.getRadius( ),
			super.getPosition( ).getY( ) - super.getRadius( ),
			super.getRadius( ) * 2.0d,
			super.getRadius( ) * 2.0d );
	}
	
	/**
	 * Draw the shape
	 * 
	 * @param g
	 * 		The graphics surface to draw onto
	 */
	public void draw( Graphics2D g )
	{
		// Set the color
		g.setColor( this.color );
		
		// Draw
		g.draw( this.javaCircle );
	}
}
