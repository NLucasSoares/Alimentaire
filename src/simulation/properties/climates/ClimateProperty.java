package simulation.properties.climates;

/**
 * The different properties defined in climate.def
 * 
 * @author Aurèle Camps
 */
public enum ClimateProperty
{
	CLIMATE_PROPERTY_NAME,
	CLIMATE_PROPERTY_TEMPERATURE,
	CLIMATE_PROPERTY_DEFAULTWEATHER;

	public static final String[] PROPERTIES_NAME = 
	{
			"climate.name",
			"climate.temperature",
			"climate.defaultWeather"	
	};
}