package simulation.properties.weathers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import simulation.Database;
import simulation.constant.FileConstant;
import simulation.file.parser.FileParser;
import simulation.file.parser.ParsedProperty;
import simulation.properties.LoadingProperties;
import simulation.world.environment.climate.weather.Weather;

/**
 * The class use to load weathers properties
 * 
 * @author Aurele Camps
 */

public class LoadingWeather 
{
	/**
	 * The weather definition position
	 */
	private static final String WEATHER_DEF_POSITION = "/simulation/properties/weathers/";
	private static final String WEATHER_DEF_NAME = "weather";
	
	/**
	 * The header of file, to get weathers count
	 */
	private static final String FILE_COUNT_HEADER = "nb=";
	
	/**
	 * Load the weather
	 * @param database 
	 */
	public static Weather[ ] load( Database database ) throws FileNotFoundException,
		IOException
	{
		// The loaded weather
		Weather[ ] weather = null;
		
		/**
		 * Read Weather def file
		 */
		try
		{
			// Open file
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
										LoadingWeather.class.getResourceAsStream(
											LoadingWeather.WEATHER_DEF_POSITION
												+ LoadingWeather.WEATHER_DEF_NAME
												+ LoadingProperties.PROPERTIES_FILES_EXTENSION ) ) );
			
			// Read first line (weather count)
			String line = br.readLine( );
			
			// Cut the String to get the end and parse number to int
			int weatherCount = Integer.parseInt( line.substring( LoadingWeather.FILE_COUNT_HEADER.length( ),
				line.length( ) ) );
			
			// Allocate memory
						weather = new Weather[ weatherCount ];
						
						// Load weather
						for( int weatherID = 0; weatherID < weatherCount; weatherID++ )
						{
							// Get the name
							String weatherName = br.readLine();
							
							// Add folder/extension
							String weatherLink = LoadingWeather.WEATHER_DEF_POSITION
								+ weatherName
								+ LoadingProperties.PROPERTIES_FILES_EXTENSION;

							// Create file parser
							FileParser fp = new FileParser( WeatherProperty.PROPERTIES_NAME,
								FileConstant.FILE_DEF_SEPARATOR );
							
							// Parse configuration file
							ParsedProperty[ ] pp = fp.parseFile( new java.io.BufferedReader(
									new InputStreamReader(
										LoadingWeather.class.getResourceAsStream( weatherLink ) ) ) );
							
							// The weather properties to be read
							String nameProperty = "";
							int temperatureProperty = 0;
							int waterQuantityProperty = 0;
							int sunQuantityProperty =0;
							
							// Get properties
							for( WeatherProperty wp : WeatherProperty.values( ) )
							{
								switch( wp )
								{
									case WEATHER_PROPERTY_NAME:
										nameProperty = pp[ wp.ordinal( ) ].getValue( ) ;
										break;
									case WEATHER_PROPERTY_TEMPERATURE:
										temperatureProperty = Integer.parseInt( pp[ wp.ordinal( ) ].getValue( ));
										break;
									case WEATHER_PROPERTY_WATERQUANTITY:
										waterQuantityProperty = Integer.parseInt( pp[ wp.ordinal( ) ].getValue( ));
										break;
									case WEATHER_PROPERTY_SUNQUANTITY:
										sunQuantityProperty = Integer.parseInt( pp[wp.ordinal()].getValue());
										
									default:
										break;
								}
							
							}
							
							// Construct weather
							weather[ weatherID ] = new Weather( nameProperty,
									temperatureProperty,
									waterQuantityProperty,
									sunQuantityProperty);
								
						}
				
						// Close file
						br.close();
					}		
					catch( IOException e )
					{
						System.err.println( e.toString( ) );
					}
					
					// OK
					return weather;
				}

}
