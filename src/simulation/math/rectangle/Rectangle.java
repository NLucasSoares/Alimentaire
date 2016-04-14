package simulation.math.rectangle;

import simulation.math.point.Point;

/**
 * Definition of a rectangle
 * 
 * @author Lucas SOARES
 */
public class Rectangle
{
	/**
	 * The size of the rectangle
	 */
	private Point<Integer> size;
	
	/**
	 * The position of the rectangle
	 */
	private Point<Integer> position;
	
	/**
	 * Construct the Rectangle
	 * 
	 * @param size
	 * 		The size of the rectangle
	 * @param position
	 * 		The position of the rectangle
	 */
	public Rectangle( Point<Integer> size,
		Point<Integer> position )
	{
		// Save
		this.size = size;
		this.position = position;
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
	 */
	public Rectangle( int w,
		int h,
		int x,
		int y )
	{
		this( new Point<Integer>( w,
				h ),
			new Point<Integer>( x,
					y ) );
	}
	
	/**
	 * Construct an empty rectangle
	 */
	public Rectangle( )
	{
		this( new Point<Integer>( 0,
				0 ),
			new Point<Integer>( 0,
				0 ) );
	}
	
	/**
	 * @return the size
	 */
	public Point<Integer> getSize( )
	{
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize( Point<Integer> size )
	{
		this.size = size;
	}

	/**
	 * @return the position
	 */
	public Point<Integer> getPosition( )
	{
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition( Point<Integer> position )
	{
		this.position = position;
	}
	
	/**
	 * To string
	 */
	public String toString( )
	{
		return "Position("
			+ this.position.getX( )
			+ ", "
			+ this.position.getY( )
			+ "), Size("
			+ this.size.getX( )
			+ ", "
			+ this.size.getY( )
			+ ")";
	}
}
