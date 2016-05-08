package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import simulation.properties.animal.LoadingAnimal;
import simulation.properties.biomes.LoadingBiomes;
import simulation.properties.climates.LoadingClimates;
import simulation.properties.weathers.LoadingWeather;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.animal.species.Carnivorous;
import simulation.world.animal.species.Herbivorous;
import simulation.world.environment.biome.Biome;
import simulation.world.environment.climate.Climate;
import simulation.world.environment.climate.weather.Weather;

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
	//private Disaster[ ] disasters;
	
	/**
	 * Construct the database
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Database( ) throws FileNotFoundException,
		IOException
	{
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
	
	/**
	 * @return the animals definition
	 */
	public AbstractAnimal[ ] getAnimals( )
	{
		return this.animals;
	}
	
	/**
	 * @return a random animal
	 */
	public AbstractAnimal getRandomAnimal( )
	{
		return this.animals[ (int)simulation.math.probability.Operation.random( 0,
			this.animals.length ) ];
	}
	
	/**
	 * @return the herbivirous animals
	 */
	public Herbivorous[ ] getHerbivorousAnimals( )
	{
		// Output buffer
		ArrayList<Herbivorous> outputBuffer = new ArrayList<Herbivorous>( );
		
		// Add herbivorous
		for( AbstractAnimal a : this.animals )
			if( a instanceof Herbivorous )
				outputBuffer.add( (Herbivorous)a );
		
		// Return result
			// Allocate
				Herbivorous output[ ] = new Herbivorous[ outputBuffer.size( ) ];
			// Convert to array
				outputBuffer.toArray( output );
			// Return
				return output;
	}
	
	/**
	 * @return the carnivorous animals
	 */
	public Carnivorous[ ] getCarnivorousAnimals( )
	{
		// Output buffer
		ArrayList<Carnivorous> outputBuffer = new ArrayList<Carnivorous>( );
		
		// Add herbivorous
		for( AbstractAnimal a : this.animals )
			if( a instanceof Carnivorous )
				outputBuffer.add( (Carnivorous)a );
		
		// Return result
			// Allocate
				Carnivorous output[ ] = new Carnivorous[ outputBuffer.size( ) ];
			// Convert to array
				outputBuffer.toArray( output );
			// Return
				return output;
	}
}
