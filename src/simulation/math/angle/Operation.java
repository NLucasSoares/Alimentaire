package simulation.math.angle;

import simulation.math.point.Point;

/**
 * Operations on the angles
 * 
 * @author SOARES Lucas
 */
public class Operation
{
	/**
	 * Find the angle between two point, using the first point
	 * as center of carthesian plan
	 * 
	 * @param x1
	 * 		The first point x coordinate
	 * @param y1
	 * 		The first point y coordinate
	 * @param x2
	 * 		The second point x coordinate
	 * @param y2
	 * 		The second point y coordinate
	 */
	public static double find( int x1,
		int y1,
		int x2,
		int y2 )
	{
		return Operation.find( (double)x1,
			(double)y1,
			(double)x2,
			(double)y2 );
	}
	
	/**
	 * Find the angle between two point, using the first point
	 * as center of carthesian plan
	 * 
	 * @param x1
	 * 		The first point x coordinate
	 * @param y1
	 * 		The first point y coordinate
	 * @param x2
	 * 		The second point x coordinate
	 * @param y2
	 * 		The second point y coordinate
	 */
	public static double find( double x1,
		double y1,
		double x2,
		double y2 )
	{
		// The angle
		double angle = 0;
		
		// The position of the first point
		Point<Double> firstPoint = new Point<Double>( (double)x1,
			(double)y1 );
				
		// The position of the second point
		Point<Double> secondPoint = new Point<Double>( x2,
			y2 );

		// Pythagore
		double adjacent = Math.abs( firstPoint.getX( ) - (double)secondPoint.getX( ) );
		double oppose = Math.abs( firstPoint.getY( ) - (double)secondPoint.getY( ) );

		// Find angle
		angle = Math.atan( oppose / adjacent );

		// Convert to degrees
		angle = ( ( angle * 180.0d ) / Math.PI );

		// Etude des cadrans
		if( secondPoint.getX( ) > firstPoint.getX( ) )
		{
			if( secondPoint.getY( ) < firstPoint.getY( ) )
				angle = 360.0d - angle;

			angle = ( (int)( angle - 270.0d ) ) % 360;

			angle = 360.0d - angle;
		}
		else
		{
			if( secondPoint.getY( ) < firstPoint.getY( ) )
			{
				angle = 360 - angle;

				angle = ( (int)( angle - 270 ) ) % 360;
			}
			else
				angle += 90;
		}

		// Modulate
		angle = (double)( (int)angle % 360 );
		
		// Correct inversion
		angle = (int)( ( 360.0d - angle ) ) % 360;

		// OK
		return angle;
	}
}
