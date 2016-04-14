package simulation.gui.configuration;

/**
 * 	The different properties defined in
 * 	the ini file.
 * 
 *	@author
 *		Lucas Soares
 */
public enum ConfigurationProperty
{
	/**
	 * The different properties
	 */
	CONFIGURATION_PROPERTY_RESOLUTION_WIDTH,
	CONFIGURATION_PROPERTY_RESOLUTION_HEIGHT,
	CONFIGURATION_PROPERTY_VOLUME_BGM,
	CONFIGURATION_PROPERTY_VOLUME_SE;
	
	/**
	 * The properties names in the
	 * ini file
	 */
	public static final String[ ] PROPERTIES_NAME = 
	{
			"width",
			"height",
			"bgm",
			"se"
	};
	
	/**
	 * The default values of properties
	 */
	public static final String[ ] DEFAULT_PROPERTIES_VALUES =
	{
		"800",
		"600",
		"80",
		"100"
	};
}


