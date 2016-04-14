package simulation.properties.resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import simulation.Database;
import simulation.constant.FileConstant;
import simulation.file.parser.FileParser;
import simulation.file.parser.ParsedProperty;
import simulation.properties.LoadingProperties;
import simulation.properties.biomes.LoadingBiomes;
import simulation.world.environment.biome.resource.field.FieldResource;

/**
 * The class use to load resources properties
 * 
 * @author Aurele Camps
 */

public class LoadingResources 
{
	/**
	 * The resources definition position
	 */
	
	private static final String RESOURCE_DEF_POSITION = "/simulation/properties/resources/";
	private static final String RESOURCE_DEF_NAME ="resources";
	
	/**
	 * The header of file, to get resources count
	 */
	private static final String FILE_COUNT_HEADER = "nb=";
	
	/**
	 * Load the resource
	 * @param database 
	 */
	public static FieldResource[ ] load( Database database ) throws FileNotFoundException,
		IOException
	{
			// The loaded resources
		FieldResource[ ] resources = null;
		
		/**
		 * Read Resource def file
		 */
		try
		{
			//Open file
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											LoadingResources.class.getResourceAsStream(
													LoadingResources.RESOURCE_DEF_POSITION
														+ LoadingResources.RESOURCE_DEF_NAME
														+ LoadingProperties.PROPERTIES_FILES_EXTENSION ) ) );
			
			// Read first line (resource count)
			String line = br.readLine( );
			
			// Cut the String to get the end and parse number to int
			int resourceCount = Integer.parseInt( line.substring( LoadingResources.FILE_COUNT_HEADER.length( ),
				line.length( ) ) );

			// Allocate memory
			resources = new FieldResource[ resourceCount ];
			
			// Load resource
			for( int resourceID = 0; resourceID < resourceCount; resourceID++ )
			{
				// Get the name
				String resourceName = br.readLine();
				
				// Add folder/extension
				String resourceLink = LoadingResources.RESOURCE_DEF_POSITION
					+ resourceName
					+ LoadingProperties.PROPERTIES_FILES_EXTENSION;
					
				// Create file parser
				FileParser fp = new simulation.file.parser.FileParser( ResourceProperty.PROPERTIES_NAME,
					FileConstant.FILE_DEF_SEPARATOR );
				
				// Parse configuration file
				ParsedProperty[ ] pp = fp.parseFile( new BufferedReader(
														new InputStreamReader(
															LoadingBiomes.class.getResourceAsStream( resourceLink ) ) ) );

				// The resource properties to be read
				String nameProperty = "";
				int proteinProperty = 0;
				int waterProperty = 0;
				int nitrogenProperty = 0;
				int sugarProperty = 0;
				int carbonProperty = 0;

				
				// Get properties
				for( ResourceProperty rp : ResourceProperty.values( ) )
				{
					switch( rp )
					{
						case RESOURCE_PROPERTY_NAME:
							nameProperty = pp[ rp.ordinal( ) ].getValue( ) ;
							break;
						case RESOURCE_PROPERTY_PROTEIN:
							proteinProperty = Integer.parseInt(pp[ rp.ordinal( ) ].getValue( ));
							break;
						case RESOURCE_PROPERTY_WATER:
							waterProperty = Integer.parseInt(pp[ rp.ordinal( ) ].getValue( ));
							break;
						case RESOURCE_PROPERTY_NITROGEN:
							nitrogenProperty = Integer.parseInt(pp[ rp.ordinal( ) ].getValue( ));
							break;
						case RESOURCE_PROPERTY_SUGAR:
							sugarProperty = Integer.parseInt(pp[ rp.ordinal( ) ].getValue( ));
							break;
						case RESOURCE_PROPERTY_CARBON:
							carbonProperty = Integer.parseInt(pp[ rp.ordinal( ) ].getValue( ));
							break;
						
						default:
							break;
					}
				}

				// Construct resource
				resources[ resourceID ] = new FieldResource( nameProperty,
					proteinProperty,
					nitrogenProperty);
			}
			
			// Close file
			br.close();
		}		
		catch( IOException e )
		{
			System.err.println( e.toString( ) );
		}
		
		// OK
		return resources;
	}
}

	


