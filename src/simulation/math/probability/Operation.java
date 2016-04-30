package simulation.math.probability;

public class Operation
{
	/**
	 * Random between a and b
	 * 
	 * If cast to int, won't be able to
	 * reach the right limit, like in
	 * real value, it can be ( b - 1 ).98
	 * for example, it has to be taken
	 * in consideration.
	 * 
	 * @return random number between a and b
	 */
	public static double random( double a,
		double b )
	{
		// Check for consistency
		if( b <= a )
			return a;
		
		// Random pick up
		return a + ( Math.random( ) * ( b - a ) );
	}
}
