package simulation.file.parser;

/**
 * 	The line's property which was given to
 * 	the parser is unknown
 * 
 * 	@author
 * 		Lucas Soares
 */
public class UnknownPropertyException extends java.lang.Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9013591117398343510L;

	/**
	 * Construct the exception
	 * 
	 * 	@param message
	 * 		The error message
	 */
	public UnknownPropertyException( String message )
	{
		super( message );
	}
}


