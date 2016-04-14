package simulation.properties.biomes;

/**
 * 	The different properties defined in biomes.def
 * 
 * 	@author CAMPS Aurèle
 */
public enum BiomeProperty
{
	BIOME_PROPERTY_NAME,
	BIOME_PROPERTY_TILESET,
	BIOME_PROPERTY_CLIMATE,
	BIOME_PROPERTY_AROUND_ALLOWED_BIOMES, // the around allowed biomes
	BIOME_PROPERTY_AROUND_BIOME_PROBABILITY; // from 1 to 100, the probability of being choosen

	public static final String[] PROPERTIES_NAME =
	{ 		
		"biome.name",	
		"biome.tileset", 
		"biome.climate",
		"biome.aroundAllowedBiomes",
		"biome.aroundBiomesProbability"
	};
}
