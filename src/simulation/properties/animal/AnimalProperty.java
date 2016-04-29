package simulation.properties.animal;

/**
 *  The different properties defined in animal.def
 * 
 * @author CAMPS Aurèle
 */

public enum AnimalProperty
{
	ANIMAL_PROPERTY_NAME,
	ANIMAL_PROPERTY_WEIGHT,
	ANIMAL_PROPERTY_SIZE,
	ANIMAL_PROPERTY_AGILITY,
	ANIMAL_PROPERTY_DIET,
	ANIMAL_PROPERTY_MAXIMUM_DENSITY,
	ANIMAL_PROPERTY_NEED_PROTEIN;
	
	public static final String[ ] PROPERTIES_NAME =
	{
		"animal.name",
		"animal.weight",
		"animal.size",
		"animal.agility",
		"animal.diet",
		"animal.maximumDensity",
		"animal.need.protein"
	};
}