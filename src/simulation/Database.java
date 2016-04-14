package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;

import simulation.properties.animal.LoadingAnimal;
import simulation.properties.biomes.LoadingBiomes;
import simulation.properties.climates.LoadingClimates;
import simulation.properties.diets.LoadingDiets;
import simulation.properties.resources.LoadingResources;
import simulation.properties.weathers.LoadingWeather;
import simulation.world.animal.diet.Diet;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.environment.biome.Biome;
import simulation.world.environment.biome.resource.field.FieldResource;
import simulation.world.environment.climate.Climate;
import simulation.world.environment.climate.weather.Weather;
import simulation.world.environment.disaster.Disaster;

/**
 * The database of the simulation, which
 * contains all the informations needed
 * to simulate world
 * 
 * @author SOARES Lucas
 */
public class Database {
	/**
	 * Animals
	 */
	private AbstractAnimal[ ] animals;
	
	/**
	 * Animals diet
	 */
	private Diet[ ] diets;
	
	/**
	 * Resources
	 */
	private FieldResource[ ] resources;

	/**
	 * Biomes
	 */
	private Biome[ ] biomes;
	
	/**
	 * Climates
	 */
	private Climate[ ] climates;
	
	/**
	 * Weathers
	 */
	private Weather[ ] weathers;
	
	/**
	 * Disasters
	 */
	private Disaster[ ] disasters;
	
	/**
	 * Construct the database
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Database( ) throws FileNotFoundException,
		IOException
	{
		// Load resources
		this.resources = LoadingResources.load( this );
		
		// Load diets
		this.diets = LoadingDiets.load( this );
		
		// Load animals
		this.animals = LoadingAnimal.load( this );
		
		// Load weather
		this.weathers = LoadingWeather.load( this );
		
		// Load climates
		this.climates = LoadingClimates.load( this );
		
		// Load biomes
		this.biomes = LoadingBiomes.load( this );
	}
	
	/**
	 * Get diet ID from diet name
	 * 
	 * @param name
	 * 		The name of the diet
	 * 
	 * @return the id of the diet
	 */
	private int getDietIDFromName( String name ) throws InvalidParameterException
	{
		// Iterator
		int i = 0;
		
		// Look for name
		for( Diet d : this.diets )
		{
			// Check
			if( d.getName( ).equals( name ) )
				return i;
			
			// Iterate
			i++;
		}
		
		// Unfound
		throw new InvalidParameterException( );
	}
	
	/**
	 * Get diet from name
	 * 
	 * @param name
	 * 		The name of the diet
	 * 
	 * @return the diet
	 */
	public Diet getDiet( String name )
	{
		return getDiet( this.getDietIDFromName( name ) );
	}
	
	/**
	 * Get diet from id
	 * 
	 * @param id
	 * 		The id of the diet
	 * 
	 * @return diet
	 */
	public Diet getDiet( int id )
	{
		return this.diets[ id ];
	}
	
	/**
	 * Get climate from name
	 * 
	 * @param name
	 * 		The climate's name
	 * 
	 * @return the climate
	 */
	public Climate getClimateFromName( String name ) throws InvalidParameterException
	{
		// Look for
		for( Climate climate : this.climates )
			if( climate.getName( ).equals( name ) )
				return climate;
		
		// Can't find
		throw new InvalidParameterException( );
	}
	
	/**
	 * Get the biomes
	 * 
	 * @return the biomes
	 */
	public Biome[ ] getBiomes( )
	{
		return this.biomes;
	}
	
	/**
	 * Get biome from name
	 * 
	 * @param name
	 * 		The biome's name
	 * 
	 * @return the requested biome
	 */
	public Biome getBiomeFromName( String name ) throws InvalidParameterException
	{
		// Look for
		for( Biome biome : this.biomes )
			if( biome.getName( ).equals( name ) )
				return biome;
		
		// Can't find
		throw new InvalidParameterException( );
	}
	
	/**
	 * Get the biome from id
	 * 
	 * @param id
	 * 		The biome's id
	 * 
	 * @return the requested biome
	 */
	public Biome getBiomeFromID( int id )
	{
		return this.biomes[ id ];
	}
	
	/**
	 * Get weather from name
	 * 
	 * @param name
	 * 		The weather's name
	 * 
	 * @return the requested weather
	 */
	public Weather getWeatherFromName( String name ) throws InvalidParameterException
	{
		// Look for
		for( Weather weather : this.weathers )
			if( weather.getName( ).equals( name ) )
				return weather;
		
		// Can't find
		return null;//throw new InvalidParameterException( );
	}
}
