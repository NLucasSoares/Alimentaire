package simulation.world.environment.nameGenerator;

import simulation.util.CharUtil;
import simulation.util.letter.Letter;
import simulation.util.letter.NotALetterException;

/**
 * Used for map name generation
 * 
 * @author SOARES Lucas
 */
public class MapNameGenerator
{
	/**
	 * Min length
	 */
	private static int MAP_NAME_GENERATOR_MIN_LENGTH = 5;
	
	/**
	 * Max length
	 */
	private static int MAP_NAME_GENERATOR_MAX_LENGTH = 12;
	
	/**
	 * @return a random name
	 */
	public static String generate( )
	{
		// Buffer
		StringBuffer buffer = new StringBuffer( );
		
		// Choose random max size
		int maxSize = MAP_NAME_GENERATOR_MIN_LENGTH + (int)( Math.random( ) * ( (double)MAP_NAME_GENERATOR_MAX_LENGTH - (double)MAP_NAME_GENERATOR_MIN_LENGTH ) );
	
		// Generate
		do
		{
			if( buffer.length( ) == 0 )
				buffer.append( CharUtil.toUpper( generateChar( ) ) );
			else
				buffer.append( generateChar( buffer.charAt( buffer.length( ) - 1 ) ) );
		} while( buffer.length( ) < maxSize );

		// OK
		return buffer.toString( );
	}
	
	/**
	 * Automate rules
	 * 
	 * @param previus
	 * 		The previus letter
	 * 
	 * @return a random char based on previus character
	 */
	private static char generateChar( char previous )
	{
		try
		{
			switch( Letter.getLetterType( previous ) )
			{
				case LETTER_TYPE_CONSONANT:
					// Random
					return Letter.VOWELS[ (int)( Math.random( ) * (double)Letter.VOWELS.length ) ];
					
				case LETTER_TYPE_VOWEL:
					// Random
					return Letter.CONSONANTS[ (int)( Math.random( ) * (double)Letter.CONSONANTS.length ) ];
					
				default:
					return generateChar( );
			}
		}
		catch( NotALetterException e )
		{
			return generateChar( );
		}
	}
	
	/**
	 * @return a random char
	 */
	private static char generateChar( )
	{
		return (char)( 'a' + ( Math.random( ) * ( 'z' - 'a' ) ) );
	}
}
