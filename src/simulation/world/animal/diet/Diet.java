package simulation.world.animal.diet;

/**
 * The likes and dislikes of a species, so to say. Rather, what they can and
 * cannot eat : a rabbit won't attack a lion for food. You have to admit this
 * idea is brilliant in itself.
 * 
 * @author Clément Thévin
 * 
 */
public class Diet {
	/**
	 * Name of this diet.
	 */
	private String name;
	/**
	 * Table storing the cans and cannots related to this diet.
	 */
	private String[] toleratedResourceNames;
	
	private DietType type;

	public Diet(String name, String[] toleratedResourceNames, DietType type) {
		this.name = name;
		this.toleratedResourceNames = toleratedResourceNames;
		this.type = type;
	}

	public String getName() {
		return name;
	}


	public String[] getToleratedResourceNames() {
		return toleratedResourceNames;
	}



	/**
	 * @return the type
	 */
	public DietType getType()
	{
		return type;
	}

	public String getToleratedResourceNames(int index) {
		return toleratedResourceNames[index];
	}
}