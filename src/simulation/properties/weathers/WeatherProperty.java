package simulation.properties.weathers;

/**
 * The different properties defined in weather.def
 * 
 * @author CAMPS Aur�le
 */

public enum WeatherProperty
{
	WEATHER_PROPERTY_NAME,
	WEATHER_PROPERTY_TEMPERATURE,
	WEATHER_PROPERTY_WATERQUANTITY,
	WEATHER_PROPERTY_SUNQUANTITY;
	
	
	public static final String[] PROPERTIES_NAME = 
	{
			"weather.name",
			"weather.temperature",
			"weather.waterQuantity",
			"weather.sunQuantity"
			
	};
}
