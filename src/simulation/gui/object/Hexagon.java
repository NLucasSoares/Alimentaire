package simulation.gui.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import simulation.math.hexagon.HexagonCorner;
import simulation.math.point.Point;

public class Hexagon extends simulation.math.hexagon.Hexagon
{
	/**
	 * The polygon
	 */
	private Polygon polygon;
	
	/**
	 * Color of the hexagon
	 */
	private Color fillColor;
	private Color borderColor;
	
	/**
	 * The position
	 */
	private Point<Double> position;
	
	/**
	 * Construct the hexagon
	 */
	public Hexagon( double verticalDiameter )
	{
		// Parent constructor
		super( verticalDiameter );
		
		// Create position
		this.position = new Point<Double>( 0.0d,
			0.0d );
		
		// Default color
		this.fillColor = Color.BLACK;
		this.borderColor = Color.BLACK;
		
		// Create the polygon
		this.createPolygon( );
	}

	/**
	 * Set the position
	 * 
	 * @param position
	 * 		The position to set
	 */
	public void setPosition( Point<Double> position )
	{
		// Set position
		this.position = position;
		
		// Update the polygon
		this.createPolygon( );
	}
	
	/**
	 * Set the position
	 * 
	 * @param x
	 * 		The x to set
	 * @param y
	 * 		The y to set
	 */
	public void setPosition( double x,
		double y )
	{
		// Set the position
		this.setPosition( new Point<Double>( x,
			y ) );
	}
	
	/**
	 * Translate position
	 * 
	 * @param position
	 * 		The translate vector
	 */
	public void translate( Point<Double> position )
	{
		this.translate( position.getX( ),
			position.getY( ) );
	}
	
	/**
	 * Translate position
	 * 
	 * @param x
	 * 		The x vector translate coordinate
	 * @param y
	 * 		The y vector translate coordinate
	 */
	public void translate( double x,
		double y )
	{
		// Translate
		this.position.setX( this.position.getX( ) + x );
		this.position.setY( this.position.getY( ) + y );
		
		// Recreate the polygon
		this.createPolygon( );
	}
	
	/**
	 * Set the color
	 * 
	 * @param color
	 * 		The color to set
	 */
	public void setFillColor( Color color )
	{
		this.fillColor = color;
	}
	
	public void setBorderColor( Color color )
	{
		this.borderColor = color;
	}
	
	/**
	 * @return the position
	 */
	public Point<Double> getPosition( )
	{
		return this.position;
	}
	
	/**
	 * @return the fill color
	 */
	public Color getFillColor( )
	{
		return this.fillColor;
	}
	
	/**
	 * @return the border color
	 */
	public Color getBorderColor( )
	{
		return this.borderColor;
	}
	
	/**
	 * Paint the hexagon
	 */
	public void paint( Graphics g )
	{
		// Fill polygon
		g.setColor( this.fillColor );
		g.fillPolygon( this.polygon );
		
		// Draw polygon border
		g.setColor( this.borderColor );
		g.drawPolygon( this.polygon );
	}
	
	/**
	 * Create the polygon
	 */
	private void createPolygon( )
	{
		// Create coordinates array
		int[ ] xCoordinates = new int[ HexagonCorner.values( ).length ];
		int[ ] yCoordinates = new int[ HexagonCorner.values( ).length ];
		
		// Fill the array
		for( HexagonCorner hc : HexagonCorner.values( ) )
		{
			xCoordinates[ hc.ordinal( ) ] = (int)( this.getCornerPointCoordinates( hc ).getX( ) + this.position.getX( ) );
			yCoordinates[ hc.ordinal( ) ] = (int)( this.getCornerPointCoordinates( hc ).getY( ) + this.position.getY( ) );
		}

		// Create the polygon
		this.polygon = new Polygon( xCoordinates,
			yCoordinates,
			HexagonCorner.values( ).length );
	}
	
	/**
	 * Is position inside polygon?
	 * 
	 * @param x
	 * 		The x coordinate
	 * @param y
	 * 		The y coordinate
	 * 
	 * @return true if inside, false instead
	 */
	public boolean isContaining( int x,
		int y )
	{
		return this.polygon.contains( x,
			y );
	}
	
	/**
	 * Is position inside polygon?
	 * 
	 * @param position
	 * 		The position to consider
	 * 
	 * @return true if inside, false instead
	 */
	public boolean isContaining( Point<Integer> position )
	{
		return this.isContaining( position.getX( ),
			position.getY( ) );
	}
}
