package simulation.file.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;

/**
 *	This class allow to parse file:
 *	giving her a file line, she'll
 *	return the index of the proper
 *	ty found.
 *
 *	@author
 *		Lucas SOARES
 */
public class FileParser
{
	/**
	 * The different properties known by the parser
	 */
	private String[ ] propertiesName;
	
	/**
	 * The separator used in the configuration file
	 */
	private char separator;
	
	/**
	 * Construct the FileParser
	 * 
	 * 	@param properties
	 * 		The properties to be known by
	 * 	the parser
	 * 
	 * 	@throws java.security.InvalidParameterException
	 * 		Parameter error
	 */
	public FileParser( String[ ] propertiesName,
		char separator ) throws InvalidParameterException
	{
		// Check
		if( propertiesName.length == 0 )
			throw new InvalidParameterException( );
			
		// Save parameters
		this.propertiesName = propertiesName;
		this.separator = separator;
	}
	
	/**
	 * Parse a file
	 */
	public ParsedProperty[ ] parseFile( String file ) throws java.io.FileNotFoundException,
		IOException
	{
		// Try open file
		BufferedReader br = new java.io.BufferedReader( new FileReader( file ) );
		
		// Get results
		ParsedProperty[ ] pp = this.parseFile( br );
		
		// Close file
		br.close( );
		
		// OK
		return pp;
	}
	
	public ParsedProperty[ ] parseFile( java.io.BufferedReader br ) throws IOException
	{
		// Line
		String line;
		
		// Output
		ParsedProperty[ ] output = new ParsedProperty[ this.propertiesName.length ];
		
		// Read file
		while( ( line = br.readLine( ) ) != null )
		{
			try
			{
				// Get property data
				ParsedProperty pp = parseLine( line );
				
				// Reference property
				output[ pp.getPropertyIndex( ) ] = pp;
			}
			catch( UnknownPropertyException upe )
			{
				continue;
			}
			
		}
		
		// OK
		return output;
	}
	
	/**
	 * Parse a line
	 */
	private ParsedProperty parseLine( String line ) throws UnknownPropertyException
	{
		// Iterator
		int i = 0;
		
		// Search for property
		for( String property : this.propertiesName )
		{
			// Check if line starts with...
			if( line.startsWith( property
				+ this.separator ) )
			{
				// Cut value
					// Separator
						String value = line.substring( property.length( ) + 1 /* separator */,
							line.length( ) );
					// Space(s)
						while( value.charAt( 0 ) == ' '
							&& value.length( ) > 0 )
						{
							value = value.substring( 1 );
						}
						
				// Create the property
				ParsedProperty pp = new ParsedProperty( i,
					value );
				
				// Found
				return pp;
			}
			
			// Next one
			i++;
		}
		
		// Unknown property
		throw new UnknownPropertyException( "The line \""
				+ line
				+ "\" can't be understand.\n" );
	}
	
	
	/**
	 * To string
	 */
	public String toString( )
	{
		// String buffer
		StringBuffer sb = new StringBuffer( );

		// Separator
		sb.append( "Separator=[" );
		sb.append( this.separator );
		sb.append( "]\n" );

		// Properties
		sb.append( "Known properties=" );
		for( String p : this.propertiesName )
		{
			sb.append( "[" );
			sb.append( p );
			sb.append( "] " );
		}
		
		// OK
		return sb.toString( );
	}
}

