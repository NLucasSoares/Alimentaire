package simulation.world.environment.climate;

import simulation.world.environment.climate.weather.Weather;

/**
 * 		Climate
 * 
 * 		The climate is determined by a name, a temperature and
 * 		a default weather. It will be different depending on 
 * 		which biome it will be used.
 * 
 * 		@author
 * 			 Aurèle Camps
 */

public class Climate
{
	/**
	 * Name of the climate
	 */
	private String name;
	
	/**
	 * Temperature of the climate
	 */
	private int temperature;
	
	/**
	 * Default weather of the climate
	 */
	private Weather defaultWeather;
	
	/**
	 * Construct the climate
	 * @param name
	 * @param temperatureProperty
	 * @param defaultWeather
	 */
	public Climate ( String name,
			int temperatureProperty,
			Weather defaultWeather)
	{
		this.name = name;
		this.temperature = temperatureProperty;
		this.defaultWeather = defaultWeather;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the temperature
	 */
	public int getTemperature()
	{
		return temperature;
	}

	/**
	 * @return the defaultWeather
	 */
	public Weather getDefaultWeather()
	{
		return defaultWeather;
	}
}
