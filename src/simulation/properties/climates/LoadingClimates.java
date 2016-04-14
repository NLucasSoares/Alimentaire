package simulation.properties.climates;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import simulation.Database;
import simulation.constant.FileConstant;
import simulation.file.parser.FileParser;
import simulation.file.parser.ParsedProperty;
import simulation.properties.LoadingProperties;
import simulation.world.environment.climate.Climate;

/**
 * The class use to load climates properties
 * 
 * @author Aurèle Camps
 */
public class LoadingClimates
{
	/**
	 * The climate definition position
	 */
	private static final String CLIMATE_DEF_POSITION = "/simulation/properties/climates/";
	private static final String CLIMATE_DEF_NAME = "climates";
	
	/**
	 * The header of file, to get climates count
	 */
	private static final String FILE_COUNT_HEADER = "nb=";
	
	/**
	 * Load the climate
	 * @param database 
	 */
	public static Climate[ ] load( Database database ) throws FileNotFoundException,
		IOException
	{
		// The loaded climate
		Climate [ ] climate = null;
		
		/**
		 * Read Climate def file
		 */
		try
		{
			// Open file
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											LoadingClimates.class.getResourceAsStream(
												LoadingClimates.CLIMATE_DEF_POSITION
													+ LoadingClimates.CLIMATE_DEF_NAME
													+ LoadingProperties.PROPERTIES_FILES_EXTENSION ) ) );
			
			// Read first line (climate count)
			String line = br.readLine( );
			
			// Cut the String to get the end and parse number to int
			int climateCount = Integer.parseInt( line.substring( LoadingClimates.FILE_COUNT_HEADER.length( ),
				line.length( ) ) );
			
			// Allocate memory
			climate = new Climate[ climateCount ];
			
			// Load climate
			for( int climateID = 0; climateID < climateCount; climateID++ )
			{
				// Get the name
				String climateName = br.readLine();
				
				// Add folder/extension
				String climateLink = LoadingClimates.CLIMATE_DEF_POSITION
					+ climateName
					+ LoadingProperties.PROPERTIES_FILES_EXTENSION;

				// Create file parser
				FileParser fp = new FileParser( ClimateProperty.PROPERTIES_NAME,
					FileConstant.FILE_DEF_SEPARATOR );
				
				// Parse configuration file
				ParsedProperty[ ] pp = fp.parseFile( new BufferedReader(
														new InputStreamReader(
															LoadingClimates.class.getResourceAsStream( climateLink ) ) ) );
				
				// The climate properties to be read
				String nameProperty = "";
				int temperatureProperty = 0;
				String defaultWeatherProperty = "";
				
				// Get properties
				for( ClimateProperty cp : ClimateProperty.values( ) )
				{
					switch( cp )
					{
						case CLIMATE_PROPERTY_NAME:
							nameProperty = pp[ cp.ordinal( ) ].getValue( ) ;
							break;
						case CLIMATE_PROPERTY_TEMPERATURE:
							temperatureProperty = Integer.parseInt(( pp[ cp.ordinal( ) ].getValue( )));
							break;
						case CLIMATE_PROPERTY_DEFAULTWEATHER:
							defaultWeatherProperty = pp[ cp.ordinal( ) ].getValue( );
							break;
							
						default:
							break;
					}
				
				}
				
				// Construct climate
				climate[ climateID ] = new Climate( nameProperty,
						temperatureProperty,
						database.getWeatherFromName( defaultWeatherProperty ) );
					
			}
	
			// Close file
			br.close();
		}		
		catch( IOException e )
		{
			System.err.println( e.toString( ) );
		}
		
		// OK
		return climate;
	}
}


