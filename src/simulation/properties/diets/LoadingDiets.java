package simulation.properties.diets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import simulation.Database;
import simulation.file.parser.FileParser;
import simulation.file.parser.ParsedProperty;
import simulation.properties.LoadingProperties;
import simulation.world.animal.diet.Diet;
import simulation.world.animal.diet.DietType;

/**
 * The class use to load diets properties
 * 
 * @author Aurele Camps
 */

public class LoadingDiets 
{
	/**
	 * The diet definition position
	 */
	private static final String DIET_DEF_POSITION = "/simulation/properties/diets/";
	private static final String DIET_DEF_NAME = "diets";
	
	/**
	 * The header of file, to get diet count
	 */
	private static final String FILE_COUNT_HEADER = "nb=";
	
	/**
	 * Load the diet
	 * @param database 
	 */
	public static simulation.world.animal.diet.Diet[ ] load( Database database ) throws java.io.FileNotFoundException,
		java.io.IOException
	{
		// The loaded diet
		Diet[ ] diet = null;
		
		/**
		 * Read diet def file
		 */
		try
		{
			// Open file
			BufferedReader br = new BufferedReader(
											new InputStreamReader(
													LoadingDiets.class.getResourceAsStream(
														LoadingDiets.DIET_DEF_POSITION
															+ LoadingDiets.DIET_DEF_NAME
															+ LoadingProperties.PROPERTIES_FILES_EXTENSION ) ) );
			
			// Read first line (diet count)
			String line = br.readLine( );
			
			// Cut the String to get the end and parse number to int
			int dietCount = Integer.parseInt( line.substring( LoadingDiets.FILE_COUNT_HEADER.length( ),
				line.length( ) ) );
			
			// Allocate memory
			diet = new Diet[ dietCount ];
			
			// Load diet
			for( int dietID = 0; dietID < dietCount; dietID++ )
			{
				// Get the name
				String dietName = br.readLine();
				
				// Add folder/extension
				String dietLink = LoadingDiets.DIET_DEF_POSITION
					+ dietName
					+ LoadingProperties.PROPERTIES_FILES_EXTENSION;

				// Create file parser
				FileParser fp = new FileParser( DietProperty.PROPERTIES_NAME,
					simulation.constant.FileConstant.FILE_DEF_SEPARATOR );
				
				// Parse configuration file
				ParsedProperty[ ] pp = fp.parseFile( new BufferedReader(
														new InputStreamReader(
															LoadingDiets.class.getResourceAsStream( dietLink ) ) ) );
				// The diet properties to be read
				String nameProperty = "";
				String[ ] toleratedResourcesProperty = null;
				String typeProperty = "";
				
				// Number of tolerated resourcesw
				int numberOfToleratedResources = 0;
				
				// Get properties
				for( DietProperty dp : DietProperty.values( ) )
				{
					switch( dp )
					{
						case DIET_PROPERTY_NAME:
							nameProperty = pp[ dp.ordinal( ) ].getValue( ) ;
							break;
						case DIET_PROPERTY_NUMBER_OF_TOLERATED_RESOURCE:
							// Read number
							numberOfToleratedResources = Integer.parseInt( pp[ dp.ordinal( ) ].getValue( ) );
							break;
						case DIET_PROPERTY_TOLERATED_RESOURCE:
							// Get the value
							String value = pp[ dp.ordinal( ) ].getValue( );
							
							// Remove start/end char
							value = simulation.util.StringUtil.remove( value,
								'{' );
							value = simulation.util.StringUtil.remove( value,
								'}' );

							// Split the String
							toleratedResourcesProperty = value.split( "," );

							// Check read values
							if( toleratedResourcesProperty.length > numberOfToleratedResources )
								throw new IOException( "Incorrect resources number" );
							
						case DIET_PROPERTY_TYPE:
							typeProperty = pp[ dp.ordinal() ].getValue();
							break;
							
						default:
							break;
					}
				}
				
				
				
				// Construct diet
				diet[ dietID ] = new simulation.world.animal.diet.Diet( nameProperty,
					toleratedResourcesProperty,
					DietType.getType( typeProperty ) );					
			}
	
			// Close file
			br.close();
		}		
		catch( IOException e )
		{
			System.err.println( e.toString( ) );
		}
		
		// OK
		return diet;
	}
}