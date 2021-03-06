package simulation.math.hexagon;

import simulation.math.point.Point;
import simulation.math.point.PointDouble;

/**
 * A hexagon representation, based on the page
 * http://blog.rusland.com/2011/02/hexagonal-grid-math.html
 * Here, we're working with a flat-top hexagon
 * 
 * @author Lucas SOARES
 */
public class Hexagon
{
	/**
	 * The radius of the hexagon
	 */
	private double verticalDiameter;
	
	/**
	 * The height of the hexagon
	 */
	private double heightConstant;
	
	/**
	 * The width of the hexagon
	 */
	private double widthConstant;
	
	/**
	 * The side of the hexagon
	 * (from the bottom to the
	 * middle-bottom)
	 */
	private double sideConstant;
	
	/**
	 * The position of the near hexagons
	 * depending of the edge
	 */
	private PointDouble[ ] nearHexagonPositionDifference = new PointDouble[ HexagonEdge.values( ).length ];
	
	/**
	 * Hexagon edge points coordinates
	 */
	private PointDouble[ ] cornerPointCoordinates = new PointDouble[ HexagonCorner.values( ).length ];
	
	/**
	 * Build the hexagon
	 * 
	 * @param radius
	 * 		The radius of the hexagon
	 */
	public Hexagon( double verticalDiameter )
	{
		// Save
		this.verticalDiameter = verticalDiameter;

		// Calculate the constants
		this.calculateConstants( );
	}
	
	/**
	 * Find random position in hexagon
	 * 
	 * @return the random position
	 */
	public static Point<Double> getRandomPosition( double size )
	{
		// Calculate size
		double w = Hexagon.calculateWidth( size );
		double h = Hexagon.calculateHeight( size );
		
		// Create hexagon copy
		simulation.gui.object.Hexagon hexagonCopy = new simulation.gui.object.Hexagon( size );
		
		// Determine random position
		Point<Double> position;
		do
		{
			position = new Point<Double>( ( simulation.math.probability.Operation.random( 1,
					w ) ),
				( simulation.math.probability.Operation.random( 1,
					h ) ) );
		} while( !hexagonCopy.isContaining( position.getX( ).intValue( ),
			position.getY( ).intValue( ) ) );
		
		// Return position
		return position;
	}
	
	/**
	 * Calculate height for a given vertical diameter
	 * 
	 * @param verticalDiameter
	 * 		The vertical diameter
	 * 
	 * @return the height
	 */
	public static double calculateHeight( double verticalDiameter )
	{
		return verticalDiameter;
	}
	
	/**
	 * Calculate width for a given vertical diameter
	 * (2*R*sqrt(3)/2)
	 * 
	 * @param verticalDiameter
	 * 		The vertical diameter
	 * 
	 * @return the width
	 */
	public static double calculateWidth( double verticalDiameter )
	{
		return Math.sqrt( 3.0d ) * ( verticalDiameter / 2.0d );
	}
	
	/**
	 * Calculate side for a given vertical diameter
	 * 
	 * @param verticalDiameter
	 * 		The vertical diameter
	 * 
	 * @return the side
	 */
	public static double calculateSide( double verticalDiameter )
	{
		return (3.0d / 2.0d) * ( verticalDiameter / 2.0d );
	}
	
	/**
	 * Calculate the constants
	 */
	private void calculateConstants( )
	{
		// Height
		this.heightConstant = Hexagon.calculateHeight( this.verticalDiameter );

		// Width (2*R*sqrt(3)/2)
		this.widthConstant = Hexagon.calculateWidth( this.verticalDiameter );
		
		// Side
		this.sideConstant = Hexagon.calculateSide( this.verticalDiameter );
		
		// Hexagons on the edges
		for( HexagonEdge he : HexagonEdge.values( ) )
			this.nearHexagonPositionDifference[ he.ordinal( ) ] = this.calculatePositionDifference( he );
		
		// Corner
		for( HexagonCorner hc : HexagonCorner.values( ) )
			this.cornerPointCoordinates[ hc.ordinal( ) ] = this.calculateCornerPointCoordinate( hc );
	}
	
	/**
	 * Calculate the position difference of next
	 * hexagon on a given edge
	 * 
	 * @param edge
	 * 		The edge to calculate
	 * 
	 * @return the position
	 */
	private PointDouble calculatePositionDifference( HexagonEdge he )
	{
		switch( he )
		{
			case HEXAGONE_EDGE_RIGHT:
				return new PointDouble( this.widthConstant,
					0.0d );
			case HEXAGONE_EDGE_DOWN_RIGHT:
				return new PointDouble( this.widthConstant / 2.0d,
					this.heightConstant / 2.0d + ( this.heightConstant - this.sideConstant ) );
			case HEXAGONE_EDGE_DOWN_LEFT:
				return new PointDouble( -this.widthConstant / 2.0d,
					this.heightConstant / 2.0d + ( this.heightConstant - this.sideConstant ) );
			case HEXAGONE_EDGE_LEFT:
				return new PointDouble( -this.widthConstant,
					0.0d );
			case HEXAGONE_EDGE_UP_LEFT:
				return new PointDouble( -this.widthConstant / 2.0d,
					-( this.heightConstant - this.sideConstant ) - this.heightConstant / 2.0d );
			case HEXAGONE_EDGE_UP_RIGHT:
				return new PointDouble( this.widthConstant / 2.0d,
						-( this.heightConstant - this.sideConstant ) - this.heightConstant / 2.0d );

			default:
				return new PointDouble( 0.0d,
					0.0d );
		}
	}
	
	/**
	 * Get the point coordinates of the hexagon
	 * 
	 * @param edge
	 * 		The edge point to calculate
	 * 
	 * @return the point
	 */
	private PointDouble calculateCornerPointCoordinate( HexagonCorner corner )
	{
		switch( corner )
		{
			case HEXAGON_CORNER_UP:
				return new PointDouble( this.widthConstant / 2.0d,
					0.0d );
				
			case HEXAGON_CORNER_UP_RIGHT:
				return new PointDouble( this.widthConstant,
						this.heightConstant - this.sideConstant );
				
			case HEXAGON_CORNER_DOWN_RIGHT:
				return new PointDouble( this.widthConstant,
						this.sideConstant );
				
			case HEXAGON_CORNER_DOWN:
				return new PointDouble( this.widthConstant / 2.0d,
					this.heightConstant );
				
			case HEXAGON_CORNER_DOWN_LEFT:
				return new PointDouble( 0.0d,
						this.sideConstant );
				
			case HEXAGON_CORNER_UP_LEFT:
				return new PointDouble( 0.0d,
						this.heightConstant - this.sideConstant );

			default:
				return new PointDouble( 0.0d,
					0.0d );
		}
	}

	/**
	 * @return the radius
	 */
	public double getVerticalDiameter( )
	{
		return verticalDiameter;
	}

	/**
	 * @return the heightConstant
	 */
	public double getHeightConstant( )
	{
		return heightConstant;
	}

	/**
	 * @return the widthConstant
	 */
	public double getWidthConstant( )
	{
		return widthConstant;
	}

	/**
	 * @return the sideConstant
	 */
	public double getSideConstant( )
	{
		return sideConstant;
	}

	/**
	 * Get the near hexagon position difference for
	 * the requested edge.
	 * 
	 * @param edge
	 * 		The requested hexagon edge
	 * 
	 * @return the nearHexagonPositionDifference
	 */
	public PointDouble getNearHexagonPositionDifference( HexagonEdge edge )
	{
		return nearHexagonPositionDifference[ edge.ordinal( ) ];
	}

	/**
	 * Get the corner point coordinates for the
	 * requested corner
	 * 
	 * @param corner
	 * 		The requested corner
	 * 
	 * @return the cornerPointCoordinates
	 */
	public PointDouble getCornerPointCoordinates( HexagonCorner corner )
	{
		return cornerPointCoordinates[ corner.ordinal( ) ];
	}
	
	
}
