package simulation.math;

/**
 * Arithmetic operation
 * 
 * @author Lucas SOARES
 */
public class Operation
{
	/**
	 * Sum a float array
	 * 
	 * @param a
	 * 		The array to sum
	 * 
	 * @return the sum of each element
	 * of the array
	 */
	public static float sumArray( float[ ] a )
	{
		// Output
		float output = 0.0f;
		
		// Sum the elements
		for( float element : a )
			output += element;
		
		// OK
		return output;
	}
	
	/**
	 * Sum a double array
	 * 
	 * @param a
	 * 		The array to sum
	 * 
	 * @return the sum of each element
	 * of the array
	 */
	public static double sumArray( double[ ] a )
	{
		// Output
		double output = 0.0f;
		
		// Sum the elements
		for( double element : a )
			output += element;
		
		// OK
		return output;
	}
	
	/**
	 * Sum a int array
	 * 
	 * @param a
	 * 		The array to sum
	 * 
	 * @return the sum of each element
	 * of the array
	 */
	public static int sumArray( int[ ] a )
	{
		// Output
		int output = 0;
		
		// Sum the elements
		for( int element : a )
			output += element;
		
		// OK
		return output;
	}
}
