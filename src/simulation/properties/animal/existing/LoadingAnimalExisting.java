package simulation.properties.animal.existing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import simulation.Database;
import simulation.constant.FileConstant;
import simulation.file.parser.FileParser;
import simulation.file.parser.ParsedProperty;
import simulation.properties.LoadingProperties;
import simulation.properties.animal.AnimalProperty;
import simulation.world.animal.need.Need;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.animal.species.Carnivorous;

/**
 * The class used to load animal existing properties  
 * 
 * @author Aurele Camps
 *
 */

public class LoadingAnimalExisting 
{
	/**
	 * The animal existing definition position
	 */
	private static final String ANIMAL_EXISTING_DEF_POSITION = "/simulation/properties/animal/existing/";
	private static final String ANIMAL_EXISTING_DEF_NAME = "animalExisting";
	
	/**
	 * The header of file, to get animal count
	 */
	private static final String FILE_COUNT_HEADER = "nb=";
	
	/**
	 * Load animal
	 * @param database 
	 */
	public static AbstractAnimal [ ] load ( Database database ) throws FileNotFoundException,
		IOException
	{
		// The loaded animal
		simulation.world.animal.species.AbstractAnimal [ ] animal = null;
				
		// Read animalexisting def file
				
		try
		{
			// Open file
			BufferedReader br = new BufferedReader(
									new InputStreamReader(
											LoadingAnimalExisting.class.getResourceAsStream(
												LoadingAnimalExisting.ANIMAL_EXISTING_DEF_POSITION
													+ LoadingAnimalExisting.ANIMAL_EXISTING_DEF_NAME
														+ LoadingProperties.PROPERTIES_FILES_EXTENSION ) ) );
						
			// Read first line (animal count)
			String line = br.readLine( );
			
			// Cut the String to get the end and parse number to int
			int animalCount = Integer.parseInt( line.substring( LoadingAnimalExisting.FILE_COUNT_HEADER.length( ),
				line.length( ) ) );
			
			// Allocate memory
			animal = new AbstractAnimal [ animalCount ];
			
			// Load climate
			for( int animalID = 0; animalID < animalCount; animalID++ )
			{
				// Get the name
				String animalName = br.readLine();
				
				// Add folder/extension
				String animalLink = LoadingAnimalExisting.ANIMAL_EXISTING_DEF_POSITION
					+ animalName
					+ LoadingProperties.PROPERTIES_FILES_EXTENSION;

				// Create file parser
				FileParser fp = new FileParser( AnimalProperty.PROPERTIES_NAME,
					FileConstant.FILE_DEF_SEPARATOR );
				
				// Parse configuration file
				ParsedProperty[ ] pp = fp.parseFile( new BufferedReader(
														new InputStreamReader(
															LoadingAnimalExisting.class.getResourceAsStream( animalLink ) ) ) );
				
				// Animal properties to be read
				String nameProperty = "";
				int weightProperty = 0;
				int sizeProperty = 0;
				int agilityProperty = 0;
				String dietProperty = "";
				int needProteinProperty = 0;
				int needWaterProperty = 0;
				int needCaloriesProperty = 0;
				
				// Get properties
				for( AnimalProperty ap : AnimalProperty.values( ) )
				{
					switch ( ap )
					{
						case ANIMAL_PROPERTY_NAME:
							nameProperty = pp[ ap.ordinal( ) ].getValue();
							break;
						
						case ANIMAL_PROPERTY_WEIGHT:
							weightProperty = Integer.parseInt( pp[ ap.ordinal( ) ].getValue( ) );
							break;
							
						case ANIMAL_PROPERTY_SIZE:
							sizeProperty = Integer.parseInt( pp[ ap.ordinal( ) ].getValue( ) );
							break;
							
						case ANIMAL_PROPERTY_AGILITY:
							agilityProperty = Integer.parseInt( pp[ ap.ordinal( ) ].getValue( ) );
						
						case ANIMAL_PROPERTY_DIET:
							dietProperty = pp[ ap.ordinal( ) ].getValue();
							break;
							
						case ANIMAL_PROPERTY_NEED_PROTEIN:
							needProteinProperty = Integer.parseInt( pp[ ap.ordinal( ) ].getValue( ) );
							break;
						case ANIMAL_PROPERTY_NEED_WATER:
							needWaterProperty = Integer.parseInt( pp[ ap.ordinal( ) ].getValue( ) );
							break;
						case ANIMAL_PROPERTY_NEED_CALORIES:
							needCaloriesProperty = Integer.parseInt( pp[ ap.ordinal( ) ].getValue( ) );
							break;
							
						default:
							break;
						
							
					}
				}
				
				// Create diet
				switch( database.getDiet( dietProperty ).getType( ) )
				{
					case DIET_TYPE_CARNIVOROUS:
						animal[ animalID ] = new Carnivorous( nameProperty,
							weightProperty,
							sizeProperty,
							agilityProperty,
							database.getDiet( dietProperty ),
							new Need( needProteinProperty,
								needWaterProperty ) );
							
						break;
					case DIET_TYPE_HERBIVOROUS:
						break;
						
					default:
						break;
				}
			}
				
		
	
	
			
	
		}
		catch( java.io.IOException e )
		{
			System.err.println( e.toString( ) );
		}
		
		return animal;
	}
}
