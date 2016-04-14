package simulation.gui.object;

import java.awt.Color;
import java.awt.Graphics;

import simulation.math.point.Point;

public class Rectangle extends simulation.math.rectangle.Rectangle
{
	/**
	 * Is the rectangle filled?
	 */
	private boolean filled;
	
	/**
	 * The color of the drawable
	 * rectangle
	 */
	private Color color;
	
	/**
	 * Construct the rectangle
	 * 
	 * @param size
	 * 		The rectangle size
	 * @param position
	 * 		The position of the rectangle
	 * @param filled
	 * 		Rectangle filled?
	 * @param color
	 * 		The color of the rectangle
	 */
	public Rectangle ( Point<Integer> size,
		Point<Integer> position,
		boolean filled,
		Color color )
	{
		// Parent constructor
		super( size,
			position );
		
		// Save
		this.filled = filled;
		this.color = color;
	}
	
	/**
	 * Default constructor for the rectangle
	 */
	public Rectangle( )
	{
		// Default parent constructor
		super( );
		
		// Save
		this.filled = false;
		this.color = Color.WHITE;
	}
	
	/**
	 * Construct the rectangle
	 * 
	 * @param w
	 * 		The width
	 * @param h
	 * 		The height
	 * @param x
	 * 		The x position
	 * @param y
	 * 		The y position
	 * @param filled
	 * 		Is it filled?
	 * @param color
	 * 		The color
	 */
	public Rectangle( int w,
		int h,
		int x,
		int y,
		boolean filled,
		Color color )
	{
		this( new Point<Integer>( w,
				h ),
			new Point<Integer>( x,
				y ),
			filled,
			color );
	}
	
	public void blit( Graphics g )
	{
		// Set the color
		g.setColor( this.color );
		
		// Draw
		g.fill3DRect( super.getPosition( ).getX( ) ,
			super.getPosition( ).getY(),
			super.getSize( ).getX(),
			super.getSize( ).getY(),
			this.filled );
	}

	/**
	 * @return the color
	 */
	public Color getColor( )
	{
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor( Color color )
	{
		this.color = color;
	}

	/**
	 * @return the filled
	 */
	public boolean isFilled( )
	{
		return filled;
	}

	/**
	 * @param filled the filled to set
	 */
	public void setFilled( boolean filled )
	{
		this.filled = filled;
	}
}
