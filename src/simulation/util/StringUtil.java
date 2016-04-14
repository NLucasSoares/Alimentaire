package simulation.util;

/**
 * String util
 * 
 * @author Lucas SOARES
 */
public class StringUtil
{
	/**
	 * Remove character
	 * 
	 * @param str
	 * 		The considered String
	 * @param character
	 * 		The character to remove
	 * 
	 * @return the treated String
	 */
	public static String remove( String str,
		char character )
	{
		// String buffer
		StringBuffer buffer = new StringBuffer( );
		
		// Read characters
		for( int i = 0; i < str.length( ); i++ )
			if( str.charAt( i ) != character )
				buffer.append( str.charAt( i ) );
		
		// OK
		return buffer.toString( );
	}
}
