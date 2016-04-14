package simulation.world.animal.need;

/**
 * The needs of a particular species. Represented by integers to state how much
 * of each resource an animal of this species need.
 * 
 * @author Clément Thévin
 * 
 */
public class Need {

	private int protein;
	private int water;
	private int calories;

	public Need( int protein, int water, int calories )
	{
		this.protein = protein;
		this.water = water;
		this.calories = calories;
	}

	public int getProtein()
	{
		return protein;
	}

	public int getWater()
	{
		return water;
	}

	public int getCalories()
	{
		return calories;
	}

	public int getNeedAttribute(int index)
	{
		int ret;
		switch (index) {
		case 0:
			ret = protein;
			break;
		case 1:
			ret = water;
			break;
		case 2:
			ret = calories;
			break;
		default:
			ret = -1;
		}
		return ret;
	}

	public int getNeedAttribute(String att)
	{
		int ret;
		switch (att) {
		case "protein":
			ret = protein;
			break;
		case "water":
			ret = water;
			break;
		case "calories":
			ret = calories;
			break;
		default:
			ret = -1;
		}
		return ret;
	}
}
