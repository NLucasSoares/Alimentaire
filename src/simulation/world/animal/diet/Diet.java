package simulation.world.animal.diet;

/**
 * What an animal is able to eat
 * 
 * @author SOARES Lucas
 */
public class Diet
{
	/**
	 * Name of this diet.
	 */
	private String name;
	
	/**
	 * Table storing the cans and cannots related to this diet.
	 */
	private String[ ] toleratedResourceNames;
	
	/**
	 * The diet type
	 */
	private DietType type;

	/**
	 * Construct the Diet
	 * 
	 * @param name
	 * 		The name of the diet
	 * @param toleratedResourceNames
	 * 		The resources the animal can eat
	 * @param type
	 * 		The diet type
	 */
	public Diet( String name,
		String[ ] toleratedResourceNames,
		DietType type )
	{
		// Save
		this.name = name;
		this.toleratedResourceNames = toleratedResourceNames;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName( )
	{
		return name;
	}

	/**
	 * @return the tolerated resource names
	 */
	public String[ ] getToleratedResourceNames( )
	{
		return toleratedResourceNames;
	}

	/**
	 * @return the type
	 */
	public DietType getType( )
	{
		return type;
	}
}