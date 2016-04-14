/**
 * 
 */
package simulation.properties.resources;

/**
 * The different properties defined in resources.def
 * 
 * @author Aurèle Camps
 */
public enum ResourceProperty 
{
	RESOURCE_PROPERTY_NAME,
	RESOURCE_PROPERTY_PROTEIN,
	RESOURCE_PROPERTY_WATER,
	RESOURCE_PROPERTY_NITROGEN,
	RESOURCE_PROPERTY_SUGAR,
	RESOURCE_PROPERTY_CARBON;
		
	
	public static final String[ ] PROPERTIES_NAME =
	{
		"resource.name",
		"resource.protein",
		"resource.water",
		"resource.nitrogen",
		"resource.sugar",
		"resource.carbon"
	};
}
