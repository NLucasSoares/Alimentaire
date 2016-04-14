package simulation.world;

import simulation.world.environment.biome.Biome;
import simulation.world.speed.SimulationSpeed;

public class Configuration
{
	/**
	 * Size of the world (the number of maps
	 * horizontally (radius))
	 */
	private int size;
	
	/**
	 * Is seasons?
	 */
	private boolean isSeasonCycle;
	
	/**
	 * Maximum density by species
	 */
	private int maximumDensityBySpecie;
	
	/**
	 * Central biome requested
	 */
	private Biome centralBiome;
	
	/**
	 * Simulation speed
	 */
	private SimulationSpeed simulationSpeed;

	/**
	 * Construct the configuration
	 */
	public Configuration( int size,
		boolean isSeasonCycle,
		int maximumDensityBySpecie,
		Biome centralBiome )
	{
		// Save
		this.centralBiome = centralBiome;
		this.isSeasonCycle = isSeasonCycle;
		this.size = size;
		this.maximumDensityBySpecie = maximumDensityBySpecie;
		
		// Init
		this.simulationSpeed = SimulationSpeed.SIMULATION_SPEED_NORMAL;
	}
	
	/**
	 * @return the size
	 */
	public int getSize( )
	{
		return size;
	}

	/**
	 * @return the isSeasonCycle
	 */
	public boolean isSeasonCycle( )
	{
		return isSeasonCycle;
	}

	/**
	 * @return the maximumDensityBySpecie
	 */
	public int getMaximumDensityBySpecie( )
	{
		return maximumDensityBySpecie;
	}

	/**
	 * @return the centralBiome
	 */
	public Biome getCentralBiome( )
	{
		return centralBiome;
	}

	/**
	 * @return the simulationSpeed
	 */
	public SimulationSpeed getSimulationSpeed( )
	{
		return simulationSpeed;
	}

	/**
	 * @param simulationSpeed the simulationSpeed to set
	 */
	public void setSimulationSpeed( SimulationSpeed simulationSpeed )
	{
		this.simulationSpeed = simulationSpeed;
	}
}
