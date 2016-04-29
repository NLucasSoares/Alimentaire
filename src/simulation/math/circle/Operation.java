package simulation.math.circle;

import java.awt.Shape;

import simulation.math.point.Point;

/**
 * Operation on circle shape
 * 
 * Circle intersects/contains are from
 * http://stackoverflow.com/questions/8367512/algorithm-to-detect-if-a-circles-intersect-with-any-other-circle-in-the-same-pla
 * ("XNA / C# solution")
 * 
 * @author SOARES Lucas
 */
public class Operation
{
	/**
	 * Intersect between a circle and a rectangle?
	 * 
	 * @param c
	 * 		The circle
	 * @param rectanglePosition
	 * 		The rectangle position
	 * @param rectangleSize
	 * 		The rectangle size
	 */
	public static boolean intersects( Shape c,
		Point<Integer> rectanglePosition,
		Point<Integer> rectangleSize )
	{
		return c.intersects( rectanglePosition.getX( ),
			rectanglePosition.getY( ),
			rectangleSize.getX( ),
			rectangleSize.getY( ) );
	}
	
	/**
	 * Evaluate if circle 1 and circle 2
	 * intersects
	 * 
	 * @param p1
	 * 		The first circle position
	 * @param r1
	 * 		The first circle radius
	 * @param p2
	 * 		The second circle position
	 * @param r2
	 * 		The second circle radius
	 * 
	 * @return if circle 1 and circle 2
	 * intersects
	 */
	public static boolean intersects( Point<Double> p1,
		double r1,
		Point<Double> p2,
		double r2 )
	{
		// Calculate X/Y distances
		double distanceX = p1.getX( ) - p2.getX( );
		double distanceY = p1.getY( ) - p2.getY( );
 
	    // Calculate radius sum
		double radiusSum = r1 + r2;
	     
	    // Evaluate
	    return ( ( ( distanceX * distanceX )
    		+ ( distanceY * distanceY ) ) <= radiusSum * radiusSum );
	}
	 
	/**
	 * Evaluate if circle contains point
	 * 
	 * @param circlePosition
	 * 		The circle position
	 * @param circleRadius
	 * 		The circle radius
	 * @param point
	 * 		The point
	 * 
	 * @return if circle 1 contains circle 2
	 */
	public static boolean contains( Point<Double> circlePosition,
    	double circleRadius,
    	Point<Double> point )
     {
		// Position difference
		double dX = point.getX( ) - circlePosition.getX( );
		double dY = point.getY( ) - circlePosition.getY( );
				
		// R^2 = ( x - xA )^2 + ( y - yA )^2
		return ( ( dX * dX + dY * dY ) < circleRadius * circleRadius );
     }
}
