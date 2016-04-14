package simulation.util;

/**
 * Char util
 * 
 * @author SOARES Lucas
 */
public class CharUtil
{
	/**
	 * Convert to lower
	 * 
	 * @param c
	 * 		Character
	 * 
	 * @return lower character
	 */
	public static char toLower( char c )
	{
		return (char)( ( c >= 'A'
			&& c <= 'Z' ) ? ( c + ( 'a' - 'A' ) ) : c );
	}
	
	/**
	 * Convert to upper
	 * 
	 * @param c
	 * 		Character
	 * 
	 * @return upper character
	 */
	public static char toUpper( char c )
	{
		return (char)( ( c >= 'a'
				&& c <= 'z' ) ? ( c - ( 'a' - 'A' ) ) : c );
	}
}
