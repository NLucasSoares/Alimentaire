package simulation.gui.configuration;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import simulation.constant.FileConstant;
import simulation.file.parser.FileParser;
import simulation.file.parser.ParsedProperty;
import simulation.math.point.Point;

/**
 * 	Configuration of the GUI interface
 * 
 *	@author
 *		Lucas Soares
 */
public class Configuration
{
	/**
	 * 	The configuration file's name
	 */
	public static final String CONFIGURATION_FILE = "foodies.ini";
	
	/**
	 * The resolution
	 */
	private Point<Integer> resolution = new Point<Integer>( 0,
		0 );
	
	/**
	 * The BGM volume
	 */
	private int bgmVolume;
	
	/**
	 * The SE volume
	 */
	private int seVolume;
	
	/**
	 * 	Create configuration from the ini file
	 *	
	 *		@throws
	 *			java.io.FileN
	 */
	public Configuration( ) throws FileNotFoundException,
		IOException
	{
		// Create file parser
		FileParser fp = new FileParser( ConfigurationProperty.PROPERTIES_NAME,
			FileConstant.CONFIGURATION_FILE_SEPARATOR );
		
		// Parse configuration file
		ParsedProperty[ ] pp = fp.parseFile( Configuration.CONFIGURATION_FILE );
		
		// Save properties
		for( ConfigurationProperty cp : ConfigurationProperty.values( ) )
			switch( cp )
			{
				case CONFIGURATION_PROPERTY_RESOLUTION_WIDTH:
					this.resolution.setX( Integer.parseInt( pp[ cp.ordinal( ) ].getValue( ) ) );
					break;
				case CONFIGURATION_PROPERTY_RESOLUTION_HEIGHT:
					this.resolution.setY( Integer.parseInt( pp[ cp.ordinal( ) ].getValue( ) ) );
					break;
				case CONFIGURATION_PROPERTY_VOLUME_BGM:
					this.bgmVolume = Integer.parseInt( pp[ cp.ordinal( ) ].getValue( ) );
					break;
				case CONFIGURATION_PROPERTY_VOLUME_SE:
					this.seVolume = Integer.parseInt( pp[ cp.ordinal( ) ].getValue( ) );
					break;
					
				default:
					break;
			}
	}
	
	/**
	 *	Create the ini file
	 *
	 *		@throws
	 *			java.io.IOException
	 *
	 *		@return
	 *			The Configuration object with default
	 *	values.
	 */
	public static void createINI( ) throws IOException
	{
		// Open file
		BufferedWriter bw = new BufferedWriter( new FileWriter( Configuration.CONFIGURATION_FILE ) );
		
		// Write properties
			// Header
				bw.write( "[Configuration]" );
				bw.newLine( );
				bw.newLine( );
			// Properties
				for( ConfigurationProperty cp : ConfigurationProperty.values( ) )
				{
					// Property
					bw.write( ConfigurationProperty.PROPERTIES_NAME[ cp.ordinal( ) ]
						+ FileConstant.CONFIGURATION_FILE_SEPARATOR
						+ ConfigurationProperty.DEFAULT_PROPERTIES_VALUES[ cp.ordinal( ) ] );
					
					// New line
					bw.newLine( );
				}
		
		// Close file
		bw.close( );
	}

	
	/**
	 * @return the configuration file
	 */
	public static String getConfigurationFile( )
	{
		return CONFIGURATION_FILE;
	}

	/**
	 * @return the resolution
	 */
	public simulation.math.point.Point<Integer> getResolution( )
	{
		return resolution;
	}

	/**
	 * @return the bgm volume
	 */
	public int getBgmVolume( )
	{
		return bgmVolume;
	}

	/**
	 * @return the se volume
	 */
	public int getSeVolume( )
	{
		return seVolume;
	}

	/**
	 * @return string value of object
	 */
	public String toString( )
	{
		return "Configuration [resolution="
			+ resolution
			+ ", bgmVolume="
			+ bgmVolume
			+ ", seVolume="
			+ seVolume
			+ "]";
	}
	
	
}
