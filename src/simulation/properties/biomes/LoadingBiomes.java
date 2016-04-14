package simulation.properties.biomes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import simulation.Database;
import simulation.constant.FileConstant;
import simulation.file.parser.FileParser;
import simulation.file.parser.ParsedProperty;
import simulation.properties.LoadingProperties;
import simulation.world.environment.biome.Biome;
import simulation.world.environment.biome.nearBiome.NearBiomes;

/**
 *	The class use to load biomes properties
 * 
 *	@author
 * 		Aurele Camps
 */

public class LoadingBiomes
{
	/**
	 * The biomes definition position
	 */
	private static final String BIOME_DEF_POSITION = "/simulation/properties/biomes/";
	private static final String BIOMES_DEF_NAME = "biomes";
	
	/**
	 * The header of file, to get biomes count
	 */
	private static final String FILE_COUNT_HEADER = "nb=";
	
	/**
	 * Load the biomes
	 * @param database 
	 */
	public static Biome[ ] load( Database database ) throws FileNotFoundException,
		java.io.IOException
	{
		// The loaded biomes
		Biome[ ] biomes = null;
		
		/**
		 * Read Biome def file
		 */
		try
		{
			// Open file
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											LoadingBiomes.class.getResourceAsStream(
												LoadingBiomes.BIOME_DEF_POSITION
													+ LoadingBiomes.BIOMES_DEF_NAME
													+ LoadingProperties.PROPERTIES_FILES_EXTENSION ) ) );
			
			// Read first line (biome count)
			String line = br.readLine( );
			
			// Cut the String to get the end and parse number to int
			int biomeCount = Integer.parseInt( line.substring( LoadingBiomes.FILE_COUNT_HEADER.length( ),
				line.length( ) ) );

			// Allocate memory
			biomes = new Biome[ biomeCount ];
			
			// Load biomes
			for( int biomeID = 0; biomeID < biomeCount; biomeID++ )
			{
				// Get the name
				String biomeName = br.readLine();
				
				// Add folder/extension
				String biomeLink = LoadingBiomes.BIOME_DEF_POSITION
					+ biomeName
					+ LoadingProperties.PROPERTIES_FILES_EXTENSION;
					
				// Create file parser
				FileParser fp = new FileParser( BiomeProperty.PROPERTIES_NAME,
					FileConstant.FILE_DEF_SEPARATOR );
				
				// Parse configuration file
				ParsedProperty[ ] pp = fp.parseFile(
											new BufferedReader(
												new InputStreamReader(
													LoadingBiomes.class.getResourceAsStream( biomeLink ) ) ) );
				
				// The biome properties to be read
				String nameProperty = "";
				String tilesetProperty = "";
				String climateProperty = "";
				String[ ] aroundAllowedBiomes = null;
				String[ ] probabilityBiomes = null;
				
				// Get properties
				for( BiomeProperty lb : BiomeProperty.values( ) )
				{
					switch( lb )
					{
						case BIOME_PROPERTY_NAME:
							nameProperty = pp[ lb.ordinal( ) ].getValue( ) ;
							break;
						case BIOME_PROPERTY_TILESET:
							tilesetProperty = pp[ lb.ordinal( ) ].getValue( );
							break;
						case BIOME_PROPERTY_CLIMATE:
							climateProperty = pp[ lb.ordinal( ) ].getValue( );
							break;
						case BIOME_PROPERTY_AROUND_ALLOWED_BIOMES:
							// Allowed biomes
							String aroundAllowedBiomesTemp = pp[ lb.ordinal( ) ].getValue( );
							
							// Remove '{' '}'
							aroundAllowedBiomesTemp = simulation.util.StringUtil.remove( aroundAllowedBiomesTemp,
								'{' );
							aroundAllowedBiomesTemp = simulation.util.StringUtil.remove( aroundAllowedBiomesTemp,
								'}' );
							
							// Split
							aroundAllowedBiomes = aroundAllowedBiomesTemp.split( "," );
							break;
						case BIOME_PROPERTY_AROUND_BIOME_PROBABILITY:
							// Allowed biomes
							String aroundProbabilityTemp = pp[ lb.ordinal( ) ].getValue( );
							
							// Remove '{' '}'
							aroundProbabilityTemp = simulation.util.StringUtil.remove( aroundProbabilityTemp,
								'{' );
							aroundProbabilityTemp = simulation.util.StringUtil.remove( aroundProbabilityTemp,
								'}' );
							
							// Split
							probabilityBiomes = aroundProbabilityTemp.split( "," );
							break;
							
						default:
							break;
					}
				}

				// Construct biome
				biomes[ biomeID ] = new Biome( nameProperty,
					database.getClimateFromName( climateProperty ),
					tilesetProperty,
					biomeID,
					new NearBiomes( aroundAllowedBiomes,
						probabilityBiomes ) );
				
				// Set the current biome
				biomes[ biomeID ].getNearAllowedBiomes( ).setCurrentBiome( biomes[ biomeID ] );
			}
			
			// Close file
			br.close( );
		}		
		catch( java.io.IOException e )
		{
			System.err.println( e.toString( ) );
			System.exit( 1 );
		}
		
		// OK
		return biomes;
	}
}
