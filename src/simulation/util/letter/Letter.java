package simulation.util.letter;

import simulation.util.CharUtil;

/**
 * Processing the letters
 *
 * @author SOARES Lucas
 */
public class Letter
{
	/**
	 * Consonants
	 */
	public static char[ ] CONSONANTS =
	{
		'b',
		'c',
		'd',
		'f',
		'g',
		'h',
		'j',
		'k',
		'l',
		'm',
		'n',
		'p',
		'q',
		'r',
		's',
		't',
		'v',
		'w',
		'x',
		'z'
	};
	
	/**
	 * Vowels
	 */
	public static char[ ] VOWELS =
	{
		'a',
		'e',
		'i',
		'o',
		'u',
		'y'
	};
	
	/**
	 * Get the letter type
	 * 
	 * @param letter
	 * 		The letter
	 *
	 * @return the letter type
	 */
	public static LetterType getLetterType( char letter ) throws NotALetterException
	{
		// To lower
		letter = CharUtil.toLower( letter );
		
		// Parse
		switch( letter )
		{
			// Vowel
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
			case 'y':
				return LetterType.LETTER_TYPE_VOWEL;
			
			case 'b':
			case 'c':
			case 'd':
			case 'f':
			case 'g':
			case 'h':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'v':
			case 'w':
			case 'x':
			case 'z':
				return LetterType.LETTER_TYPE_CONSONANT;
				
			// Other characters
			default:
				throw new NotALetterException( letter + " is not a letter" );
		}
	}
}
