package simulation.util.letter;

/**
 * The character isn't a letter
 * 
 * @author SOARES Lucas
 */
public class NotALetterException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6180834703351780298L;
	
	/**
	 * Constructor
	 * 
	 * @param message
	 * 		The message for exception
	 */
	public NotALetterException( String message )
	{
		super( message );
	}	
}
