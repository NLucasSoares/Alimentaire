package simulation.world.environment.biome.nearBiome;

import simulation.Database;
import simulation.math.Operation;
import simulation.math.probability.ParameterizedExperience;
import simulation.world.environment.biome.Biome;

/**
 * The properties of a near allowed biome
 * for another
 * 
 * @author Lucas SOARES
 */
public class NearBiomes
{
	/**
	 * Biomes names
	 */
	private String[ ] biomesName;
	
	/**
	 * Current biome
	 */
	private Biome currentBiome;
	
	/**
	 * Biomes probability
	 */
	private int[ ] biomesProbability;
	
	/**
	 * Construct near biomes
	 */
	public NearBiomes( String[ ] biomesName,
		String[ ] biomesProbability )
	{
		// Init
		this.currentBiome = null;
		
		// Save
		this.biomesName = biomesName;
		
		// Allocate memory
		this.biomesProbability = new int[ biomesProbability.length ];
		
		// Convert to integer
		int iterator = 0;
		for( String tmp : biomesProbability )
			this.biomesProbability[ iterator++ ] = Integer.parseInt( tmp );
	}
	
	/**
	 * Set the current biome
	 * 
	 * @param biome
	 * 		The biome to set
	 */
	public void setCurrentBiome( Biome biome )
	{
		this.currentBiome = biome;
	}
	
	/**
	 * Pick up a random biome
	 * 
	 * @return the random biome
	 */
	public Biome pickRandomBiome( Database db )
	{
		// Compilate biomes
			// Allocate
				Biome[ ] biomes = new Biome[ this.biomesName.length + 1 /* The current one */ ];
			// Add
				// From database
					for( int i = 0; i < this.biomesName.length; i++ )
						biomes[ i ] = db.getBiomeFromName( this.biomesName[ i ] );
				// Current
					biomes[ biomes.length - 1 ] = this.currentBiome;
					
		// Compile probabilities
			// Allocate
				int[ ] probability = new int[ this.biomesProbability.length + 1 ];
			// Set the last one to 0
				probability[ probability.length - 1 ] = 0;
			// Add
				// Other
					for( int i = 0; i < this.biomesProbability.length; i++ )
						probability[ i ] = this.biomesProbability[ i ] / 2;
				// Sum probability
					int probabilitySum = Operation.sumArray( probability );
				// Last property, the rest
					probability[ probability.length - 1 ] = 100 - probabilitySum;
					
		// Create the experience
		ParameterizedExperience<Biome> exp = new ParameterizedExperience<Biome>( probability,
			biomes );
		
		// Do the random pick-up
		exp.doExperience( );
		
		// Return result
		return exp.getEventParameter( );
	}
}
