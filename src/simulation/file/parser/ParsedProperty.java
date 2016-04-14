package simulation.file.parser;

/**
 *	The result given by the FileParser
 *	class.
 *
 *	@author
 *		Lucas SOARES
 */
public class ParsedProperty
{
	/**
	 * The property index
	 */
	private int propertyIndex;
	
	/**
	 * The property value
	 */
	private String value;
	
	/**
	 * Construct property
	 * 
	 * 	@param propertyIndex
	 * 		The index of the property found
	 * 	@param value
	 * 		The value of the property found
	 */
	public ParsedProperty( int propertyIndex,
		String value )
	{
		// Save property informations
		this.propertyIndex = propertyIndex;
		this.value = value;
	}

	/**
	 * @return the propertyIndex
	 */
	public int getPropertyIndex( )
	{
		return propertyIndex;
	}

	/**
	 * @return the value
	 */
	public String getValue( )
	{
		return value;
	}

	/**
	 *	@return the to string object's informations
	 */
	public String toString( )
	{
		return "ParsedProperty [propertyIndex="
			+ propertyIndex
			+ ", value="
			+ value
			+ "]";
	}	
}
