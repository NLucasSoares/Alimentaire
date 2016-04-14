package simulation.world.animal.need.state;

import simulation.constant.SimulationConstant;
import simulation.world.animal.need.Need;

/**
 * The current needs of a particular animal compared to what they already
 * absorbed.
 * 
 * @author Clément Thévin
 * 
 */
public class NeedState {

	/**
	 * Too low an amount of any of these will have a dramatic influence on the
	 * animal's chances to survive. I.e : it'll most surely die.
	 */
	private Need need;
	private int[] protein;
	private int[] water;
	private int[] calories;

	public NeedState(int protein[], int water[], int calories[], Need need) {
		this.protein = protein;
		this.water = water;
		this.calories = calories;
		this.need = need;
	}

	public int[] getProtein() {
		return protein;
	}

	public void setProtein(int[] protein) {
		this.protein = protein;
	}

	public int[] getWater() {
		return water;
	}

	public void setWater(int[] water) {
		this.water = water;
	}

	public int[] getCalories() {
		return calories;
	}

	public void setCalories(int[] calories) {
		this.calories = calories;
	}

	public Need getNeed() {
		return need;
	}

	public void setNeed(Need need) {
		this.need = need;
	}

	public int[] getAttributesNeedState(int index) {
		switch (index) {
		case 0:
			return protein;
		case 1:
			return water;
		case 2:
			return calories;
		default:
			return null;
		}
	}

	public void setAttributesNeedState(int protein, int water, int calories, int oxygen) {
		int[] values = { protein, water, calories, oxygen };
		setAttributesNeedState(values);
	}

	public void setAttributesNeedState(int[] values) {
		protein[0] = values[0];
		water[0] = values[1];
		calories[0] = values[2];
	}

	public void refreshTableOnTurn() {
		for (int i = SimulationConstant.LAST_TURNS - 1; i > 0; i--) {
			protein[i] = protein[i - 1];
			water[i] = water[i - 1];
			calories[i] = calories[i - 1];
		}
	}

	public void setAttributesNeedStateOnTurn(int[] values) {
		refreshTableOnTurn();
		setAttributesNeedState(values);
	}

}