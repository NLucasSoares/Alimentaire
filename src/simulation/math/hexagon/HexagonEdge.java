package simulation.math.hexagon;

import java.security.InvalidParameterException;

import simulation.math.point.Point;

/**
 * The differents edges of a
 * hexagone
 * 
 * @author Lucas SOARES
 */
public enum HexagonEdge
{
	HEXAGONE_EDGE_UP_LEFT,
	HEXAGONE_EDGE_UP_RIGHT,
	HEXAGONE_EDGE_RIGHT,
	HEXAGONE_EDGE_DOWN_RIGHT,
	HEXAGONE_EDGE_DOWN_LEFT,
	HEXAGONE_EDGE_LEFT;
	
	public static final int[ ][ ] EDGE_DIRECTION_COORDINATE =
	{
		{ 0,	-1 },
		{ 1,	-1 },
		{ 1,	0 },
		{ 0,	1 },
		{ -1,	1 },
		{ -1,	0 }
	};
	
	/**
	 * Determine the edge from two
	 * map axial coordinates
	 * 
	 * @param currentPosition
	 * 		The current position
	 * @param lastPosition
	 * 		The last position
	 * 
	 * 
	 * @return the edge which allows
	 * this transfer
	 */
	public static HexagonEdge calculateLastEdge( Point<Integer> currentPosition,
		Point<Integer> lastPosition ) throws InvalidParameterException
	{
		// Delta
		Point<Integer> delta = new Point<Integer>( lastPosition.getX( ) - currentPosition.getX( ),
				lastPosition.getY( ) - currentPosition.getY( ) );
		
		// Look for value
		for( HexagonEdge he : HexagonEdge.values( ) )
			if( delta.getX( ) == HexagonEdge.EDGE_DIRECTION_COORDINATE[ he.ordinal( ) ][ 0 ]
				&& delta.getY( ) == HexagonEdge.EDGE_DIRECTION_COORDINATE[ he.ordinal( ) ][ 1 ] )
				return he;
		
		// Unfound
		throw new InvalidParameterException( );
	}
}
