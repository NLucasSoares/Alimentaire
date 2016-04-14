package simulation.properties.diets;

/**
 * The different property defined in a diet
 * 
 * @author CAMPS Aurèle
 */

public enum DietProperty
{
	DIET_PROPERTY_NAME,
	DIET_PROPERTY_NUMBER_OF_TOLERATED_RESOURCE,
	DIET_PROPERTY_TOLERATED_RESOURCE,
	DIET_PROPERTY_TYPE;

	public static final String[] PROPERTIES_NAME = 
	{
			"diet.name",
			"diet.numberOfToleratedResource",
			"diet.toleratedResources",
			"diet.type"
				
	};
}
