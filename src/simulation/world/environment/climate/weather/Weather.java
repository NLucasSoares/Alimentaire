package simulation.world.environment.climate.weather;

/**
 * A weather definition
 * 
 * @author Lucas SOARES
 */
public class Weather
{
	/**
	 * Weather name
	 */
	private String name;
	
	/**
	 * Water quantity produced by weather
	 */
	private int waterQuantity;
	
	/**
	 * Temperature change
	 */
	private int temperatureChange;
	
	/**
	 * Sun quantity
	 */
	private int sunQuantity;


	/**
	 * Construct the Weather
	 * 
	 * @param name
	 * 		The name of the weather
	 * @param waterQuantity
	 * 		The water given by this weather
	 * @param temperatureChange
	 * 		The temperature for this weather
	 * @param sunQuantity
	 * 		The quantity of sun
	 */
	public Weather( String name,
		int waterQuantity,
		int temperatureChange,
		int sunQuantity )
	{
		this.name = name;
		this.waterQuantity = waterQuantity;
		this.temperatureChange = temperatureChange;
		this.sunQuantity = sunQuantity;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName( )
	{
		return name;
	}

	/**
	 * @return the waterQuantity
	 */
	public int getWaterQuantity( )
	{
		return waterQuantity;
	}

	/**
	 * @return the temperatureChange
	 */
	public int getTemperatureChange( )
	{
		return temperatureChange;
	}

	/**
	 * @return the sunQuantity
	 */
	public int getSunQuantity( )
	{
		return sunQuantity;
	}
}

