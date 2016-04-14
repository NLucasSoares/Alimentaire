package simulation.properties.animal;

import java.io.FileNotFoundException;
import java.io.IOException;

import simulation.Database;
import simulation.properties.animal.existing.LoadingAnimalExisting;
import simulation.world.animal.species.AbstractAnimal;

/**
 * Load the sum of existing and procedural
 * animals
 * 
 * @author Lucas SOARES
 */
public class LoadingAnimal
{
	/**
	 * Load animals (existing+procedural)
	 * @param database 
	 * 
	 * @return the loaded animals
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static AbstractAnimal[ ] load( Database database ) throws FileNotFoundException,
		IOException
	{
		// Load existing animals
		AbstractAnimal[ ] existingAnimals = LoadingAnimalExisting.load( database );
		
		// Load prodecural animals
		
		// Do the sum
		
		// OK
		return existingAnimals;
	}
}

